package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class CartController {
    private final Cart cart;
    private final Store store;

    @FXML private Label lblTotalCost;
    @FXML private TextField tfFilter;
    @FXML private RadioButton radioBtnFilterId;
    @FXML private RadioButton radioBtnFilterTitle;
    @FXML private ToggleGroup filterCategory;
    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, Integer> colMediaId;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediaCategory;
    @FXML private TableColumn<Media, Float> colMediaCost;
    @FXML private Button btnPlay;
    @FXML private Button btnRemove;

    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    private void initialize() {
        configureTable();
        setupSearchFunction();
        setupSelectionTracking();
        observeCartChanges();
        updateTotalLabel();
    }

    private void configureTable() {
        colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        tblMedia.setItems(cart.getCartItems());
    }

    private void setupSearchFunction() {
        tfFilter.textProperty().addListener((obs, oldText, newText) -> applyFilter(newText));
        radioBtnFilterId.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) applyFilter(tfFilter.getText());
        });
        radioBtnFilterTitle.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) applyFilter(tfFilter.getText());
        });
        radioBtnFilterId.setSelected(true);
    }

    private void setupSelectionTracking() {
        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    btnRemove.setVisible(newSelection != null);
                    btnPlay.setVisible(newSelection instanceof Playable);
                });
    }

    private void observeCartChanges() {
        cart.getCartItems().addListener((ListChangeListener<Media>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    updateTotalLabel();
                }
            }
        });
    }

    private void applyFilter(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            tblMedia.setItems(cart.getCartItems());
            return;
        }

        String lower = keyword.trim().toLowerCase();
        if (radioBtnFilterId.isSelected()) {
            tblMedia.setItems(cart.getCartItems().filtered(
                    media -> String.valueOf(media.getId()).contains(lower)));
        } else {
            tblMedia.setItems(cart.getCartItems().filtered(
                    media -> media.getTitle() != null && media.getTitle().toLowerCase().contains(lower)));
        }
    }

    private void updateTotalLabel() {
        lblTotalCost.setText(String.format("%.2f $", cart.getTotalCost()));
    }

    @FXML
    private void btnRemovePressed(javafx.event.ActionEvent event) {
        Media chosen = tblMedia.getSelectionModel().getSelectedItem();
        if (chosen != null) {
            cart.removeItem(chosen);
            applyFilter(tfFilter.getText());
        }
    }

    @FXML
    private void btnPlayPressed(javafx.event.ActionEvent event) {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();
        if (selected instanceof Playable playable) {
            try {
                playable.play();
                showMessage("Media Playback", "▶ Now playing: " + selected.getTitle(), Alert.AlertType.INFORMATION);
            } catch (PlayerException e) {
                showMessage("Playback Error", e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void btnPlaceOrderPressed(javafx.event.ActionEvent event) {
        if (cart.getCartItems().isEmpty()) {
            showMessage("Cart Empty", "Please add items before placing order.", Alert.AlertType.WARNING);
        } else {
            showMessage("Order Confirmed", "Your order has been placed!", Alert.AlertType.INFORMATION);
            cart.clearCart();
        }
    }

    @FXML
    private void btnViewStorePressed(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml"));
            loader.setController(new ViewStoreController(store, cart));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Store");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String header, String content, Alert.AlertType type) {
        Alert popup = new Alert(type);
        popup.setTitle("Notification");
        popup.setHeaderText(header);
        popup.setContentText(content);
        popup.showAndWait();
    }
}
