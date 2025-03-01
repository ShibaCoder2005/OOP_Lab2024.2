import javax.swing.JOptionPane;

public class CalculateOperations {
    public static void main(String[] arg) {
        String number1, number2;

        number1 = JOptionPane.showInputDialog("Enter the first number: ");
        double num1 = Double.parseDouble(number1);

        number2 = JOptionPane.showInputDialog("Enter the second number: ");
        double num2 = Double.parseDouble(number2);

        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;
        double quotient = num2 != 0 ? num1 / num2 : Double.NaN;

        JOptionPane.showMessageDialog(null, "Sum :" + sum + "\nDifference: " + difference + "\nProduct: " + product + "\nQuotient: " + quotient);
    } 
}
