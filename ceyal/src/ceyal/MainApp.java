package ceyal;
//MainApp.java
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MainApp extends Application {

 @Override
 public void start(Stage primaryStage) {
     primaryStage.setTitle("Process Mining Tool");

     EventLogView eventLogView = new EventLogView();
     eventLogView.setActivityCost("Activity A", 100);
     eventLogView.setActivityCost("Activity B", 200);
     eventLogView.setActivityCost("Activity C", 150);

     FileChooser fileChooser = new FileChooser();
     Button uploadButton = new Button("Upload Event Log");
     uploadButton.setOnAction(e -> {
         File file = fileChooser.showOpenDialog(primaryStage);
         if (file != null) {
             eventLogView.uploadFile(file);
         }
     });

     // Example historical data for predictions
     List<Double> historicalCosts = Arrays.asList(120.0, 130.0, 150.0);
     List<Double> historicalTimes = Arrays.asList(30.0, 45.0, 35.0);
     Button predictButton = new Button("Predict Costs and Time");
     predictButton.setOnAction(e -> eventLogView.predictCostsAndTime(historicalCosts, historicalTimes));

     VBox vbox = new VBox(uploadButton, predictButton);
     Scene scene = new Scene(vbox, 300, 200);
     primaryStage.setScene(scene);
     primaryStage.show();
 }

 public static void main(String[] args) {
     launch(args);
 }
}
