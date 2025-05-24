package hust.soict.hedspi.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton pen;

    @FXML
    private RadioButton eraser;

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        Color colorToDraw = Color.BLACK;

        // Nếu chọn eraser thì vẽ màu trắng (xóa)
        if (eraser.isSelected()) {
            colorToDraw = Color.WHITE;
        }

        Circle newCircle = new Circle(event.getX(), event.getY(), 4, colorToDraw);
        drawingAreaPane.getChildren().add(newCircle);
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }
}
