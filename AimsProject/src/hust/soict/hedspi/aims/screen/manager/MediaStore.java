package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.*;

public class MediaStore extends JPanel {
    private final Media media;

    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Title
        JLabel titleLabel = new JLabel(media.getTitle());
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Cost
        JLabel costLabel = new JLabel(String.format("%.2f $", media.getCost()));
        costLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons (e.g. Play if Playable)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        if (media instanceof Playable playable) {
            JButton btnPlay = new JButton("Play");
            btnPlay.addActionListener((ActionEvent e) -> playMedia(playable));
            buttonPanel.add(btnPlay);
        }

        this.add(Box.createVerticalStrut(10));
        this.add(titleLabel);
        this.add(costLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(buttonPanel);
        this.add(Box.createVerticalStrut(10));
    }

    private void playMedia(Playable playable) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Playing Media: " + media.getTitle());
        dialog.setSize(350, 200);
        dialog.setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        dialog.add(scrollPane);

        try {
            String output = playable.play();
            textArea.setText(output);
        } catch (PlayerException e) {
            textArea.setText("\u274C Error while playing: " + e.getMessage());
        } catch (Exception e) {
            textArea.setText("\u274C Unexpected error: " + e.getMessage());
        }

        dialog.setVisible(true);
    }
}
