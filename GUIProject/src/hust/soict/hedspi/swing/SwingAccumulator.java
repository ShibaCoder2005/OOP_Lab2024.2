package hust.soict.hedspi.swing;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SwingAccumulator extends JFrame {
	private JTextField tfInput;
	private JTextField tfOutput;
	private int sum = 0;

	public SwingAccumulator() {
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(2, 2, 5, 5));

		cp.add(new JLabel("Enter an Integer:"));

		tfInput = new JTextField(10);
		cp.add(tfInput);
		tfInput.addActionListener(new TFInputListener());

		cp.add(new JLabel("The Accumulated Sum is:"));

		tfOutput = new JTextField(10);
		tfOutput.setEditable(false);
		cp.add(tfOutput);

		setTitle("Swing Accumulator");
		setSize(350, 120);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);  // Căn giữa màn hình
		setVisible(true);
	}

	public static void main(String[] args) {
		new SwingAccumulator();
	}

	private class TFInputListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			try {
				int numberIn = Integer.parseInt(tfInput.getText().trim());
				sum += numberIn;
				tfInput.setText("");
				tfOutput.setText(String.valueOf(sum));
			} catch (NumberFormatException e) {
				tfInput.setText("");
				tfOutput.setText("Invalid input");
			}
		}
	}
}
