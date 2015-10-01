import javax.swing.*;

/**
 * Created by brian on 28/09/2015.
 * Thread Safe Gui Implementation.
 */
public class Main {
    public static void main (String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI.initialiseElements();
                GUI.drawGUI();
            }
        });

    }
}
