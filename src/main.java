import javax.swing.*;
import View.View;

public class main {
    public static void main(String args[]) {
        JFrame frame = new View("Polynomial Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
