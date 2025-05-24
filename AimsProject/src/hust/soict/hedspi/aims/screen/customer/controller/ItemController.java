package hust.soict.hedspi.aims.screen.customer.controller;

import javax.naming.LimitExceededException;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ItemController {

    private Media mediaRef;
    private final Cart shoppingCart;

    public ItemController(Cart cart) {
        this.shoppingCart = cart;
    }

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    public void setData(Media media) {
        this.mediaRef = media;
        lblTitle.setText(media.getTitle());
        lblCost.setText(String.format("%.2f $", media.getCost()));

        boolean playable = media instanceof Playable;
        btnPlay.setVisible(playable);

        if (!playable) {
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60));
        }
    }

    @FXML
    private void btnAddToCartClicked(ActionEvent event) {
        try {
            String result = shoppingCart.addItem(mediaRef);
            showMessage(Alert.AlertType.INFORMATION, "Thêm sản phẩm", null, result);
        } catch (LimitExceededException ex) {
            showMessage(Alert.AlertType.WARNING, "Giới hạn giỏ hàng", null, ex.getMessage());
            System.err.println("Lỗi khi thêm sản phẩm: " + ex.getMessage());
        }
    }

    @FXML
    private void btnPlayClicked(ActionEvent event) {
        if (mediaRef instanceof Playable playable) {
            try {
                playable.play();
                showMessage(Alert.AlertType.INFORMATION, "Trình phát", null, "▶ Đang phát: " + mediaRef.getTitle());
            } catch (PlayerException pe) {
                showMessage(Alert.AlertType.ERROR, "Phát thất bại", "Không thể phát nội dung", pe.getMessage());
                pe.printStackTrace();
            } catch (Exception e) {
                showMessage(Alert.AlertType.ERROR, "Lỗi hệ thống", "Đã xảy ra lỗi không xác định", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void showMessage(Alert.AlertType type, String title, String header, String content) {
        Alert popup = new Alert(type);
        popup.setTitle(title);
        popup.setHeaderText(header);
        popup.setContentText(content);
        popup.showAndWait();
    }
}
