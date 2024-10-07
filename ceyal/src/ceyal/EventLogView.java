package ceyal;

//EventLogView.java
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventLogView {
 private CostEstimation costEstimation;
 private TimeEstimation timeEstimation;
 private List<Event> events;

 public EventLogView() {
     costEstimation = new CostEstimation();
     timeEstimation = new TimeEstimation();
 }

 public void setActivityCost(String activity, double cost) {
     costEstimation.setActivityCost(activity, cost);
 }

 public List<Event> parseCSV(File file) throws IOException {
     List<Event> events = new ArrayList<>();
     try (CSVParser parser = new CSVParser(new FileReader(file), CSVFormat.DEFAULT.withHeader())) {
         for (CSVRecord record : parser) {
             String caseId = record.get("Case ID"); // Updated this line
             String activity = record.get("Task");   // Updated this line
             long timestamp = Long.parseLong(record.get("Timestamp")); // Updated this line
             double duration = 0; // Placeholder, you may want to adjust based on your logic
             events.add(new Event(caseId, activity, timestamp, duration));
         }
     }
     return events;
 }

 public void uploadFile(File file) {
     try {
         events = parseCSV(file);
         double totalCost = costEstimation.estimateTotalCost(events);
         double totalTime = timeEstimation.estimateTotalTime(events);

         System.out.println("Total Estimated Cost: " + totalCost);
         System.out.println("Total Estimated Time: " + totalTime);
     } catch (IOException e) {
         e.printStackTrace();
     }
 }

 public void predictCostsAndTime(List<Double> historicalCosts, List<Double> historicalTimes) {
     CostPrediction costPrediction = new CostPrediction();
     TimePrediction timePrediction = new TimePrediction();
     
     double predictedCost = costPrediction.predictFutureCost(historicalCosts);
     double predictedTime = timePrediction.predictFutureTime(historicalTimes);

     System.out.println("Predicted Future Cost: " + predictedCost);
     System.out.println("Predicted Future Time: " + predictedTime);
 }
}
