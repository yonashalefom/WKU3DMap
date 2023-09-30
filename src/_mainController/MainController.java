package _mainController;

import main.StartingPoint;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by Yonas Halefom on 1/24/2017.
 */
public class MainController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(event -> {
            StartingPoint.movableObject.setPosition(new Vector3f(StartingPoint.movableObject.getPosition().x, StartingPoint.movableObject.getPosition().y, StartingPoint.movableObject.getPosition().z - 10));
            System.out.println("Mouse Clicked");
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
