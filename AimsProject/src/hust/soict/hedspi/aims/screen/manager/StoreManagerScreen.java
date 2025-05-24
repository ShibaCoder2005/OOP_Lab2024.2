package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

public class StoreManagerScreen extends JFrame {
	private final Store store;

	public StoreManagerScreen(Store store) {
		this.store = store;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("AIMS - Store Manager");
		setSize(1024, 768);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(createCenter(), BorderLayout.CENTER);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	JPanel createNorth() {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}

	JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Options");

		JMenuItem viewStore = new JMenuItem("View Store");
		viewStore.addActionListener(e -> refreshStoreDisplay());
		menu.add(viewStore);

		JMenu updateStoreMenu = new JMenu("Update Store");

		JMenuItem addBook = new JMenuItem("Add Book");
		addBook.addActionListener(e -> {
			new AddBookToStoreScreen(store);
			dispose();
		});
		updateStoreMenu.add(addBook);

		JMenuItem addCD = new JMenuItem("Add CD");
		addCD.addActionListener(e -> {
			new AddCompactDiscToStoreScreen(store);
			dispose();
		});
		updateStoreMenu.add(addCD);

		JMenuItem addDVD = new JMenuItem("Add DVD");
		addDVD.addActionListener(e -> {
			new AddDigitalVideoDiscToStoreScreen(store);
			dispose();
		});
		updateStoreMenu.add(addDVD);

		menu.add(updateStoreMenu);
		menuBar.add(menu);
		return menuBar;
	}

	JPanel createHeader() {
		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

		JLabel title = new JLabel("AIMS - Store Management");
		title.setFont(new Font(title.getFont().getName(), Font.BOLD, 36));
		title.setForeground(Color.BLUE);

		header.add(Box.createRigidArea(new Dimension(10, 10)));
		header.add(title);
		header.add(Box.createHorizontalGlue());

		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(e -> refreshStoreDisplay());
		header.add(refreshButton);
		header.add(Box.createRigidArea(new Dimension(10, 10)));

		return header;
	}

	JPanel createCenter() {
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(0, 3, 10, 10));
		center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		ArrayList<Media> mediaInStore = store.getItemsInStore();
		for (Media media : mediaInStore) {
			center.add(createMediaCell(media));
		}

		return center;
	}

	JPanel createMediaCell(Media media) {
		JPanel cell = new JPanel();
		cell.setLayout(new BoxLayout(cell, BoxLayout.Y_AXIS));
		cell.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JLabel titleLabel = new JLabel(media.getTitle());
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 14));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel detailsLabel = new JLabel(media.getClass().getSimpleName() + " - " + media.getCost() + " $");
		detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel buttonPanel = new JPanel();
		if (media instanceof Playable) {
			JButton playButton = new JButton("Play");
			playButton.addActionListener(e -> {
				try {
					playMedia(media);
				} catch (PlayerException e1) {
					JOptionPane.showMessageDialog(this, "Error playing media: " + e1.getMessage(), "Play Error", JOptionPane.ERROR_MESSAGE);
				}
			});
			buttonPanel.add(playButton);
		}

		cell.add(Box.createVerticalGlue());
		cell.add(titleLabel);
		cell.add(detailsLabel);
		cell.add(Box.createVerticalGlue());
		cell.add(buttonPanel);

		return cell;
	}

	private void playMedia(Media media) throws PlayerException {
		JDialog playDialog = new JDialog(this, "Now Playing", true);
		playDialog.setSize(400, 300);
		playDialog.setLocationRelativeTo(this);

		JTextArea playInfo = new JTextArea();
		playInfo.setEditable(false);
		playInfo.setFont(new Font("Monospaced", Font.PLAIN, 13));

		StringBuilder info = new StringBuilder("Now playing: " + media.getTitle() + "\n");

		if (media instanceof CompactDisc cd) {
			info.append("Performer: ").append(cd.getPerformer()).append("\n");
			info.append("Tracks:\n");
			for (Track track : cd.getTrackList()) {
				info.append(" - ").append(track.getTitle()).append(" (").append(track.getLength()).append(" sec)\n");
			}
		} else if (media instanceof DigitalVideoDisc dvd) {
			info.append("Director: ").append(dvd.getFilmDirector()).append("\n");
			info.append("Length: ").append(dvd.getDuration()).append(" min\n");
		}

		try {
			String output = ((Playable) media).play();
			info.append("\n▶ Output:\n").append(output);
		} catch (PlayerException e) {
			info.append("\n❌ Error: ").append(e.getMessage());
		}

		playInfo.setText(info.toString());
		playDialog.add(new JScrollPane(playInfo));
		playDialog.setVisible(true);
	}

	private void refreshStoreDisplay() {
		getContentPane().removeAll();
		getContentPane().add(createNorth(), BorderLayout.NORTH);
		getContentPane().add(createCenter(), BorderLayout.CENTER);
		revalidate();
		repaint();
	}

	public static void main(String[] args) {
		Store store = new Store();

		CompactDisc cd1 = new CompactDisc("The Dark Side of the Moon", "Rock", "Pink Floyd", 15.99f);
		cd1.appendTrack(new Track("Speak to Me", 90));
		cd1.appendTrack(new Track("Breathe", 163));
		store.addMedia(cd1);

		CompactDisc cd2 = new CompactDisc("Thriller", "Pop", "Michael Jackson", 12.99f);
		cd2.appendTrack(new Track("Wanna Be Startin' Somethin'", 363));
		cd2.appendTrack(new Track("Thriller", 357));
		store.addMedia(cd2);

		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Shawshank Redemption", "Drama", "Frank Darabont", 142, 9.99f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 12.99f);
		store.addMedia(dvd1);
		store.addMedia(dvd2);

		Book book1 = new Book("The Lord of the Rings", "Fantasy", 19.99f);
		book1.addWriter("J.R.R. Tolkien");
		Book book2 = new Book("Clean Code", "Programming", 29.99f);
		book2.addWriter("Robert C. Martin");
		store.addMedia(book1);
		store.addMedia(book2);

		SwingUtilities.invokeLater(() -> new StoreManagerScreen(store));
	}
}
