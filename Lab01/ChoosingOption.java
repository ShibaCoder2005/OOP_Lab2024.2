import javax.swing.JOptionPane;

public class ChoosingOption {
    public static void main(String[] args) {
        String[] option = {"Yes", "No"};
        int choice = JOptionPane.showOptionDialog(null, "Do you agree?", "Custom Option",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

        JOptionPane.showMessageDialog(null, "You've chosen: " 
            + (choice == JOptionPane.YES_OPTION ? "Yes" : "No"));

        System.exit(0);
    }
}
