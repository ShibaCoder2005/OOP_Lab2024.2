package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hust.soict.hedspi.aims.store.Store;

public abstract class AddItemToStoreScreen extends JFrame {
	protected Store store;

	public AddItemToStoreScreen(Store store) {
		this.store = store;

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(createCenter(), BorderLayout.CENTER);  // Thêm phần center mặc định

		setTitle("Add Item to Store");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Đảm bảo thoát khi đóng
	}

	private JPanel createNorth() {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}

	private JMenuBar createMenuBar() {
		JMenu menu = new JMenu("Options");

		JMenuItem viewStore = new JMenuItem("View Store");
		viewStore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StoreManagerScreen(store);
				dispose();
			}
		});
		menu.add(viewStore);

		JMenu smUpdateStore = new JMenu("Update Store");

		JMenuItem addBook = new JMenuItem("Add Book");
		addBook.addActionListener(e -> {
			new AddBookToStoreScreen(store);
			dispose();
		});

		JMenuItem addCD = new JMenuItem("Add CD");
		addCD.addActionListener(e -> {
			new AddCompactDiscToStoreScreen(store);
			dispose();
		});

		JMenuItem addDVD = new JMenuItem("Add DVD");
		addDVD.addActionListener(e -> {
			new AddDigitalVideoDiscToStoreScreen(store);
			dispose();
		});

		smUpdateStore.add(addBook);
		smUpdateStore.add(addCD);
		smUpdateStore.add(addDVD);
		menu.add(smUpdateStore);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar.add(menu);

		return menuBar;
	}

	private JPanel createHeader() {
		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

		JLabel title = new JLabel("Add Item");
		title.setFont(new Font(title.getFont().getName(), Font.BOLD, 24));
		title.setForeground(Color.BLUE);

		header.add(Box.createRigidArea(new Dimension(10, 10)));
		header.add(title);
		header.add(Box.createHorizontalGlue());
		header.add(Box.createRigidArea(new Dimension(10, 10)));

		return header;
	}

	protected abstract JPanel createCenter();
}
