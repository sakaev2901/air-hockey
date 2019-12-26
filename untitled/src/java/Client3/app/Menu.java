package Client3.app;

import Client3.app.Main;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu extends Application {
//    private Parent createContent() {
//
//
//
//
//    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = new Pane();

        root.setPrefSize(400, 500);

//        try(InputStream is = Files.newInputStream(Paths.get("res/war1.jpg"))){
//            ImageView img = new ImageView(new Image(is));
//            img.setFitWidth(1050);
//            img.setFitHeight(600);
//            root.getChildren().add(img);
//        }
//        catch(IOException e) {
//            System.out.println("Couldn't load image");
//        }
        Main main = new Main();
        Title title = new Title ("A I R H O C K E Y");
        title.setTranslateX(0);
        title.setTranslateY(100);
        MenuItem menuItem1 = new MenuItem("Подключиться");
        EventHandler<MouseEvent> circleOnMousePressedEventHandler =
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        try {
                            main.start(primaryStage);
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    }
                };
        menuItem1.setOnMouseClicked(circleOnMousePressedEventHandler);
        MenuItem menuItem2 = new MenuItem("Создать игру");
        MenuItem menuItem3 = new MenuItem("Настройки");
        MenuItem menuItem4 = new MenuItem("Выход");
        MenuBox vbox = new MenuBox(menuItem1, menuItem2, menuItem3, menuItem4
        );
        vbox.setTranslateX(100);
        vbox.setTranslateY(300);

        root.getChildren().addAll(title,vbox);
        Scene scene = new Scene(root, Color.BLACK);
        primaryStage.setTitle("VIDEO GAME");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static class Title extends StackPane {
        public Title(String name) {
            Rectangle bg = new Rectangle(398, 60);
            bg.setStroke(Color.WHITE);
            bg.setStrokeWidth(2);
            bg.setFill(null);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 40));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg,text);
        }
    }

    private static class MenuBox extends VBox {
        public MenuBox(MenuItem...items) {
            getChildren().add(createSeperator());

            for(MenuItem item : items) {
                getChildren().addAll(item, createSeperator());
            }
        }

        private Line createSeperator() {
            Line sep = new Line();
            sep.setEndX(210);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }

    }


    private static class MenuItem extends StackPane{
        public MenuItem(String name) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                    new Stop(0, Color.DARKBLUE),
                    new Stop(0.1, Color.BLACK),
                    new Stop(0.9, Color.BLACK),
                    new Stop(1, Color.DARKBLUE)

            });

            Rectangle bg = new Rectangle(200,30);
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

    public static void main(String[] args) {

        launch(args);
    }
}
