package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {

	private JTextField tfTitle;
	private JTextField tfCategory;
	private JTextField tfArtist;
	private JTextField tfCost;
	private JTextArea taTracks;

	private final List<Track> tracks = new ArrayList<>();

	public AddCompactDiscToStoreScreen(Store store) {
		super(store);

		Container cp = getContentPane();
		cp.add(createCenter(), BorderLayout.CENTER);

		setTitle("Add Compact Disc to Store");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	protected JPanel createCenter() {
		JPanel center = new JPanel(new BorderLayout());

		// --- Form fields ---
		JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
		tfTitle = new JTextField();
		tfCategory = new JTextField();
		tfArtist = new JTextField();
		tfCost = new JTextField();

		formPanel.add(new JLabel("Title:"));
		formPanel.add(tfTitle);

		formPanel.add(new JLabel("Category:"));
		formPanel.add(tfCategory);

		formPanel.add(new JLabel("Artist:"));
		formPanel.add(tfArtist);

		formPanel.add(new JLabel("Cost:"));
		formPanel.add(tfCost);

		center.add(formPanel, BorderLayout.NORTH);

		// --- Track entry area ---
		JPanel tracksPanel = new JPanel(new BorderLayout());
		tracksPanel.setBorder(BorderFactory.createTitledBorder("Tracks"));

		taTracks = new JTextArea(5, 20);
		taTracks.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(taTracks);
		tracksPanel.add(scrollPane, BorderLayout.CENTER);

		JTextArea instructions = new JTextArea("Enter tracks in format: Title:Length (one per line)\nExample:\n  Song 1:180\n  Song 2:240");
		instructions.setEditable(false);
		instructions.setOpaque(false);
		instructions.setFont(new Font("SansSerif", Font.ITALIC, 12));
		tracksPanel.add(instructions, BorderLayout.NORTH);

		center.add(tracksPanel, BorderLayout.CENTER);

		// --- Button panel ---
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton btnAdd = new JButton("Add CD");
		btnAdd.addActionListener(new ButtonListener());
		buttonPanel.add(btnAdd);

		center.add(buttonPanel, BorderLayout.SOUTH);

		return center;
	}

	private void parseTracksFromTextArea() throws IllegalArgumentException {
		tracks.clear();
		String[] lines = taTracks.getText().split("\\n");

		for (String line : lines) {
			String[] parts = line.split(":");
			if (parts.length != 2) {
				throw new IllegalArgumentException("Invalid format in line: " + line);
			}
			String title = parts[0].trim();
			int length;
			try {
				length = Integer.parseInt(parts[1].trim());
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Invalid number format in line: " + line);
			}

			tracks.add(new Track(title, length));
		}

		if (tracks.isEmpty()) {
			throw new IllegalArgumentException("Please add at least one valid track.");
		}
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				String title = tfTitle.getText().trim();
				String category = tfCategory.getText().trim();
				String artist = tfArtist.getText().trim();
				String costStr = tfCost.getText().trim();

				if (title.isEmpty() || category.isEmpty() || artist.isEmpty() || costStr.isEmpty()) {
					throw new IllegalArgumentException("Please fill all fields.");
				}

				float cost = Float.parseFloat(costStr);
				parseTracksFromTextArea();

				CompactDisc cd = new CompactDisc(title, category, artist, cost);
				for (Track track : tracks) {
					cd.appendTrack(track);
				}

				store.addMedia(cd);
				JOptionPane.showMessageDialog(null, "CD added successfully with " + tracks.size() + " tracks!", "Success", JOptionPane.INFORMATION_MESSAGE);

				new StoreManagerScreen(store);
				dispose();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Cost must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
	}
}
