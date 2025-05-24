package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {

	private JTextField tfTitle;
	private JTextField tfCategory;
	private JTextField tfDirector;
	private JTextField tfLength;
	private JTextField tfCost;

	public AddDigitalVideoDiscToStoreScreen(Store store) {
		super(store);

		Container cp = getContentPane();
		cp.add(createCenter(), BorderLayout.CENTER);

		setTitle("Add DVD to Store");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	protected JPanel createCenter() {
		JPanel center = new JPanel(new GridLayout(6, 2, 5, 5));

		tfTitle = new JTextField();
		tfCategory = new JTextField();
		tfDirector = new JTextField();
		tfLength = new JTextField();
		tfCost = new JTextField();

		center.add(new JLabel("Title:"));
		center.add(tfTitle);

		center.add(new JLabel("Category:"));
		center.add(tfCategory);

		center.add(new JLabel("Director:"));
		center.add(tfDirector);

		center.add(new JLabel("Length (in seconds):"));
		center.add(tfLength);

		center.add(new JLabel("Cost:"));
		center.add(tfCost);

		JButton btnAdd = new JButton("Add DVD");
		btnAdd.addActionListener(new ButtonListener());
		center.add(new JLabel());  // filler
		center.add(btnAdd);

		return center;
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				String title = tfTitle.getText().trim();
				String category = tfCategory.getText().trim();
				String director = tfDirector.getText().trim();
				String lengthStr = tfLength.getText().trim();
				String costStr = tfCost.getText().trim();

				if (title.isEmpty() || category.isEmpty() || director.isEmpty() || lengthStr.isEmpty() || costStr.isEmpty()) {
					throw new IllegalArgumentException("Please fill in all fields.");
				}

				int length = Integer.parseInt(lengthStr);
				float cost = Float.parseFloat(costStr);

				DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
				store.addMedia(dvd);

				JOptionPane.showMessageDialog(null, "DVD added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				new StoreManagerScreen(store);
				dispose();
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Invalid number format for Length or Cost.", "Input Error", JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
	}
}
