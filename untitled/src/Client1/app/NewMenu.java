package Client1.app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

public class NewMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button = new Button("Начать игру");
        Main main = new Main();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    main.start(primaryStage);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        });
        Group root = new Group();
        root.getChildren().addAll(button);
        primaryStage.setTitle("MENU");
        primaryStage.setScene(new Scene(root, 400, 500, Color.BLACK));
        Rectangle rectangle = new Rectangle();

        primaryStage.show();
    }
}
