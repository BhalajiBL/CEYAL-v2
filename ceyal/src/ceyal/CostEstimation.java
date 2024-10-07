package ceyal;

//CostEstimation.java
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CostEstimation {
 private Map<String, Double> activityCosts;

 public CostEstimation() {
     activityCosts = new HashMap<>();
 }

 public void setActivityCost(String activity, double cost) {
     activityCosts.put(activity, cost);
 }

 public double estimateTotalCost(List<Event> events) {
     double totalCost = 0.0;
     for (Event event : events) {
         totalCost += activityCosts.getOrDefault(event.getActivity(), 0.0);
     }
     return totalCost;
 }
}
