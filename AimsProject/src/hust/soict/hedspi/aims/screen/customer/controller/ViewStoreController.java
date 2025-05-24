package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewStoreController {

    private static final String CART_VIEW_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml";
    private static final String MEDIA_VIEW_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Item.fxml";

    private final Store storeData;
    private final Cart cartContext;

    public ViewStoreController(Store store, Cart cart) {
        this.storeData = store;
        this.cartContext = cart;
    }

    @FXML
    private GridPane gridPane;

    @FXML
    private void initialize() {
        int col = 0;
        int row = 1;

        for (Media media : storeData.getItemsInStore()) {
            try {
                AnchorPane mediaCard = loadMediaCard(media);
                gridPane.add(mediaCard, col, row);
                GridPane.setMargin(mediaCard, new Insets(20, 10, 10, 10));

                col++;
                if (col == 3) {
                    col = 0;
                    row++;
                }
            } catch (IOException e) {
                System.err.printf("⚠️ Không thể hiển thị '%s': %s\n", media.getTitle(), e.getMessage());
            }
        }
    }

    private AnchorPane loadMediaCard(Media media) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(MEDIA_VIEW_PATH));
        ItemController controller = new ItemController(cartContext);
        loader.setController(controller);

        AnchorPane card = loader.load();
        controller.setData(media);
        System.out.printf("📦 Đã tải: %s\n", media.getTitle());
        return card;
    }

    @FXML
    private void btnViewCartPressed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(CART_VIEW_PATH));
            loader.setController(new CartController(storeData, cartContext));
            Parent cartSceneRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(cartSceneRoot));
            stage.setTitle("Giỏ hàng");
            stage.show();
        } catch (IOException e) {
            System.err.println("🚫 Không thể mở giao diện giỏ hàng.");
            e.printStackTrace();
        }
    }
}
