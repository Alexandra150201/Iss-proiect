package theater.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TheaterApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderA = new FXMLLoader(TheaterApplication.class.getResource("/logIn.fxml"));
        Parent root = fxmlLoaderA.load();
        stage.setTitle("Theater");
        LogInController ctrl = fxmlLoaderA.getController();
        ctrl.init();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
