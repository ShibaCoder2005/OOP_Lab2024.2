package hust.soict.hedspi.swing;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NumberGrid extends JFrame {
	private JButton[] btnNumbers = new JButton[10];
	private JButton btnDelete, btnReset;
	private JTextField tfDisplay;

	public NumberGrid() {
		tfDisplay = new JTextField();
		tfDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		JPanel panelButtons = new JPanel(new GridLayout(4, 3));
		addButtons(panelButtons);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(tfDisplay, BorderLayout.NORTH);
		cp.add(panelButtons, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Number Grid");
		setSize(200, 200);
		setVisible(true);
	}

	public static void main(String[] args) {
		new NumberGrid();
	}

	void addButtons(JPanel panelButtons) {
		ButtonListener btnListener = new ButtonListener();

		// Thêm nút số từ 1 đến 9
		for (int i = 1; i <= 9; i++) {
			btnNumbers[i] = new JButton("" + i);
			panelButtons.add(btnNumbers[i]);
			btnNumbers[i].addActionListener(btnListener);
		}

		// Nút DEL
		btnDelete = new JButton("DEL");
		panelButtons.add(btnDelete);
		btnDelete.addActionListener(btnListener);

		// Nút 0
		btnNumbers[0] = new JButton("0");
		panelButtons.add(btnNumbers[0]);
		btnNumbers[0].addActionListener(btnListener);

		// Nút Reset (C)
		btnReset = new JButton("C");
		panelButtons.add(btnReset);
		btnReset.addActionListener(btnListener);
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String button = e.getActionCommand();
			if (button.matches("\\d")) {
				// Nếu là số
				tfDisplay.setText(tfDisplay.getText() + button);
			} else if (button.equals("DEL")) {
				// Xóa ký tự cuối
				String currentText = tfDisplay.getText();
				if (!currentText.isEmpty()) {
					tfDisplay.setText(currentText.substring(0, currentText.length() - 1));
				}
			} else if (button.equals("C")) {
				// Xóa toàn bộ
				tfDisplay.setText("");
			}
		}
	}
}
