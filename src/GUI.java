import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * Created by brian on 28/09/2015.
 * now testing git commits
 */
public abstract class GUI implements ActionListener{
    private static JFrame mainWindow;
    private static JPanel loadPanel;
    private static JPanel reversePanel;
    private static JPanel revWordPanel;
    private static JPanel countPanel;
    private static JButton loadButton;
    private static JButton revSentenceButton;
    private static JButton revWordButton;
    private static JButton countButton;
    private static JTextArea loadTextArea;
    private static JTextArea reverseTextArea;
    private static JTextArea reverseWordTextArea;
    private static JTextArea countArea;

    public static File file;

    public static void initialiseElements() {
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        mainWindow = new JFrame("Streams Assignment");
        mainWindow.setSize(810, 800);
        mainWindow.setLayout(new GridLayout(1, 4));

        loadPanel = new JPanel();
        reversePanel = new JPanel();
        revWordPanel = new JPanel();
        countPanel = new JPanel();

        loadTextArea = new JTextArea();
        loadTextArea.setSize(198, 600);
        //loadTextArea
        loadTextArea.setVisible(true);
        loadTextArea.setLineWrap(true);
        loadTextArea.setWrapStyleWord(true);
        loadTextArea.setBorder(border);

        reverseTextArea = new JTextArea();
        reverseTextArea.setSize(198, 600);
        reverseTextArea.setVisible(true);
        reverseTextArea.setLineWrap(true);
        reverseTextArea.setWrapStyleWord(true);
        reverseTextArea.setBorder(border);

        reverseWordTextArea = new JTextArea();
        reverseWordTextArea.setSize(198, 600);
        reverseWordTextArea.setVisible(true);
        reverseWordTextArea.setLineWrap(true);
        reverseWordTextArea.setWrapStyleWord(true);
        reverseWordTextArea.setBorder(border);

        countArea = new JTextArea();
        countArea.setSize(198, 600);
        countArea.setVisible(true);
        countArea.setLineWrap(true);
        countArea.setBorder(border);

        loadButton = new JButton("Load");
        revSentenceButton = new JButton("Reverse Sentence");
        revWordButton = new JButton("Reverse Word Pairs");
        countButton = new JButton("Count");

        mainWindow.add(loadPanel);
        loadPanel.add(loadButton);
        loadPanel.add(loadTextArea);
        loadPanel.setVisible(true);

        mainWindow.add(reversePanel);
        reversePanel.add(revSentenceButton);
        reversePanel.add(reverseTextArea);

        mainWindow.add(revWordPanel);
        revWordPanel.add(revWordButton);
        revWordPanel.add(reverseWordTextArea);

        mainWindow.add(countPanel);
        countPanel.add(countButton);
        countPanel.add(countArea);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent loadData) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                file = fileChooser.getSelectedFile();
                try {
                    SwingWorker<String, Void> loadFileData = new SwingWorker<String, Void>() {
                        @Override
                        protected String doInBackground() throws Exception {
                            BufferedReader br = new BufferedReader(new FileReader(file));
                            String currentLine = br.readLine();
                            String file = "";

                            while (currentLine != null) {
                                file = file + currentLine;
                                currentLine = br.readLine();

                            }
                            return file;
                        }

                        protected void done() {
                            String data;
                            try {
                                loadTextArea.setText("");
                                data = get();
                                loadTextArea.append(data);
                            } catch (InterruptedException ie) {
                                ie.printStackTrace();
                            } catch (ExecutionException exe) {
                                exe.printStackTrace();
                            }
                        }
                    };
                    loadFileData.execute();
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        });
        revSentenceButton.addActionListener(new ActionListener(){
                                                @Override
                                                public void actionPerformed(ActionEvent reverseSentence){
                                                    try
                                                    {
                                                        SwingWorker<String, Void> loadFileData = new SwingWorker<String, Void>() {
                                                            @Override
                                                            protected String doInBackground() throws Exception {
                                                                BufferedReader br = new BufferedReader(new FileReader(file));
                                                                String currentLine = br.readLine();
                                                                String fileInfo = "";
                                                                while (currentLine != null)
                                                                {
                                                                    String[] lines = currentLine.split("\\.");
                                                                    for(int x = 0; x < lines.length; x++)
                                                                    {
                                                                        String[] words = lines[x].split(" ");
                                                                        for (int i = words.length -1; i >= 0; i--)
                                                                            if(i == 0)
                                                                                fileInfo=fileInfo+words[i]+".";
                                                                            else
                                                                                fileInfo = fileInfo+words[i]+" ";

                                                                    }
                                                                    if(currentLine.compareTo("")==0)
                                                                        fileInfo = fileInfo + "\r\n";
                                                                    currentLine = br.readLine();
                                                                }
                                                                return fileInfo;
                                                            }
                                                            protected void done(){
                                                                try {
                                                                    reverseTextArea.setText("");
                                                                    String data;
                                                                    data = get();
                                                                    reverseTextArea.append(data);
                                                                }
                                                                catch(InterruptedException ie){
                                                                    ie.printStackTrace();
                                                                }catch(ExecutionException exe){
                                                                    exe.printStackTrace();
                                                                }
                                                            }
                                                        };
                                                        loadFileData.execute();
                                                    }
                                                    catch(Exception ee){
                                                        ee.printStackTrace();
                                                    }
                                                }
                                            }
        );

        revWordButton.addActionListener(new ActionListener(){
                                            @Override
                                            public void actionPerformed(ActionEvent reverseSentence){
                                                try
                                                {
                                                    SwingWorker<String, Void> loadFileData = new SwingWorker<String, Void>() {
                                                        @Override
                                                        protected String doInBackground() throws Exception {
                                                            BufferedReader br = new BufferedReader(new FileReader(file));
                                                            String currentLine = br.readLine();
                                                            String file = "";
                                                            while (currentLine != null) {
                                                                String[] lines = currentLine.split("\\.");
                                                                for (int x = 0; x < lines.length; x++) {
                                                                    String[] words = lines[x].split(" ");
                                                                    for (int i = 0; i < words.length; i++) {
                                                                        if (i < words.length - 1 && i % 2 == 0)
                                                                            file = file + words[i + 1] + " ";
                                                                        file = file + words[i] + " ";
                                                                        i++;
                                                                    }
                                                                }
                                                                if (currentLine.compareTo("") == 0)
                                                                    file = file +"\r\n";
                                                                currentLine = br.readLine();
                                                            }
                                                            return file;
                                                        }
                                                        protected void done(){
                                                            reverseWordTextArea.setText("");
                                                            try {
                                                                String data = get();

                                                                reverseWordTextArea.append(data);
                                                            }
                                                            catch(InterruptedException ioe) {
                                                                ioe.printStackTrace();
                                                            }catch(ExecutionException ee){
                                                                ee.printStackTrace();
                                                            }
                                                        }
                                                    };
                                                    loadFileData.execute();
                                                }
                                                catch(Exception ee)
                                                {
                                                    ee.printStackTrace();
                                                }
                                            }
                                        }
        );

        countButton.addActionListener(new ActionListener(){
                                          @Override
                                          public void actionPerformed(ActionEvent reverseSentence){
                                              try
                                              {
                                                  SwingWorker<Map<String, Integer>, Void> loadFileData = new SwingWorker<Map<String, Integer>, Void>() {
                                                      @Override
                                                      protected Map doInBackground() throws Exception {
                                                          Map<String, Integer> frequencyOfWords = new HashMap<String, Integer>();
                                                          BufferedReader br = new BufferedReader(new FileReader(file));
                                                          String currentLine = br.readLine();
                                                          while (currentLine != null) {
                                                              currentLine = currentLine.replaceAll("[^a-z/A-Z ]", "").toLowerCase();
                                                              String[] words = currentLine.split(" ");
                                                              for (int i = 0; i < words.length; i++) {
                                                                  if (frequencyOfWords.containsKey(words[i]))
                                                                      frequencyOfWords.put(words[i], frequencyOfWords.get(words[i]) + 1);
                                                                  else
                                                                      frequencyOfWords.put(words[i], 1);
                                                              }
                                                              currentLine = br.readLine();
                                                          }
                                                          return frequencyOfWords;
                                                      }
                                                      protected void done(){
                                                          try{
                                                              countArea.setText("");
                                                              Map<String, Integer> frequencyOfWords = doInBackground();
                                                              for(Map.Entry<String, Integer> entry: frequencyOfWords.entrySet())
                                                              {
                                                                  String key = entry.getKey().toString();
                                                                  Integer value = entry.getValue();
                                                                  countArea.append(key + " " + value + "\n");
                                                              }
                                                          }
                                                          catch(Exception ee){ee.printStackTrace();}
                                                      }
                                                  };
                                                  loadFileData.execute();
                                              }
                                              catch(Exception ioe)
                                              {
                                                  ioe.printStackTrace();
                                              }
                                          }
                                      }
        );
    }
    public static void drawGUI()
    {
        mainWindow.setVisible(true);
    }
}
