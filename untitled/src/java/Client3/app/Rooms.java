package Client3.app;

import Client3.net.SocketClient;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class Rooms extends Application {
    public void start(Stage primaryStage) {
        SocketClient client = new SocketClient();
        client.startConnection("localhost", 6666);
        client.sendMessage("get-rooms");
        String responce = "";
//        System.out.println(SocketClient.responce);
        while (SocketClient.responce == null) {
            System.out.print("");
            if (SocketClient.responce != null) {
                System.out.println(SocketClient.responce);
                responce = SocketClient.responce;
                break;
            }

        }
        String[] rooms = responce.split(" ");
//        List<Button> buttonList = new LinkedList<>();
        List<MenuItem> menuItemList = new LinkedList<>();
        Double layoutY = 0d;
        for (String roomId:
             rooms) {
            MenuItem item = new MenuItem("Подключиться к комнате № " + roomId );
//            System.out.println(roomId);
//            Button button = new Button();
//
//            button.setMinWidth(400);
            item.setLayoutY(layoutY);
            layoutY += 40;
            Main main = new Main();
            EventHandler<MouseEvent> connect =
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent t) {
                            try {
//                        main.start(primaryStage);
                        main.connect(Integer.parseInt(roomId), primaryStage);
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                        }
                    };
            item.setOnMouseClicked(connect);
//            button.setText(roomId);
//            Main main = new Main();
//            button.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    try {
////                        main.start(primaryStage);
//                        main.connect(Integer.parseInt(roomId), primaryStage);
//                    } catch (Exception e) {
//                        throw new IllegalStateException(e);
//                    }
//                }
//            });
//            buttonList.add(button);
            menuItemList.add(item);
        }

        Group root = new Group();
//        for (Button button:
//             buttonList) {
//            System.out.println(button);
//            root.getChildren().add(button);
//        }
        for (MenuItem button:
             menuItemList) {
            System.out.println(button);
            root.getChildren().add(button);
        }
        primaryStage.setResizable(true);
        Scene scene = new Scene(root, 400, 500, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class MenuItem extends StackPane {
        public MenuItem(String name) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                    new Stop(0, Color.DARKBLUE),
                    new Stop(0.1, Color.BLACK),
                    new Stop(0.9, Color.BLACK),
                    new Stop(1, Color.DARKBLUE)

            });

            Rectangle bg = new Rectangle(398,30);
            bg.setStroke(Color.WHITE);
            bg.setOpacity(0.4);

            Text text = new Text(name);
            text.setFill(Color.DARKGREY);
            text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD,20));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
            setOnMouseEntered(event -> {
                bg.setFill(gradient);
                text.setFill(Color.WHITE);

            });

            setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.DARKGREY);
            });
            setOnMousePressed(event -> {
                bg.setFill(Color.DARKVIOLET);
                Main main = new Main();
//                main.start;()
            });

            setOnMouseReleased(event -> {
                bg.setFill(gradient);
            });

        }
    }
}
