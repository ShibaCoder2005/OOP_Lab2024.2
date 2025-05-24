package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

	private JTextField titleField;
	private JTextField categoryField;
	private JTextField costField;
	private JTextField authorsField;

	public AddBookToStoreScreen(Store store) {
		super(store);

		Container cp = getContentPane();
		cp.add(buildInputForm(), BorderLayout.CENTER);

		setTitle("Add New Book");
		setSize(420, 260);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	protected JPanel createCenter() {
		return buildInputForm();
	}

	private JPanel buildInputForm() {
		JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

		formPanel.add(new JLabel("Book Title:"));
		titleField = new JTextField();
		formPanel.add(titleField);

		formPanel.add(new JLabel("Category:"));
		categoryField = new JTextField();
		formPanel.add(categoryField);

		formPanel.add(new JLabel("Price:"));
		costField = new JTextField();
		formPanel.add(costField);

		formPanel.add(new JLabel("Authors (comma-separated):"));
		authorsField = new JTextField();
		formPanel.add(authorsField);

		JButton confirmBtn = new JButton("Add to Store");
		confirmBtn.addActionListener(new AddBookListener());

		formPanel.add(new JLabel()); // spacing
		formPanel.add(confirmBtn);

		return formPanel;
	}

	private class AddBookListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String title = titleField.getText().trim();
				String category = categoryField.getText().trim();
				String costStr = costField.getText().trim();
				String authorList = authorsField.getText().trim();

				if (title.isEmpty() || category.isEmpty() || costStr.isEmpty()) {
					throw new IllegalArgumentException("All fields except authors are required.");
				}

				float price = Float.parseFloat(costStr);
				Book book = new Book(title, category, price);

				if (!authorList.isEmpty()) {
					for (String author : authorList.split(",")) {
						book.addWriter(author.trim());
					}
				}

				store.addMedia(book);
				JOptionPane.showMessageDialog(null, "✅ Book added to inventory: " + book.getTitle());

				new StoreManagerScreen(store);
				dispose();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Cost must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
	}
}
