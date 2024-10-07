package ceyal;

//TimeEstimation.java
import java.util.List;

public class TimeEstimation {
 public double estimateTotalTime(List<Event> events) {
     double totalTime = 0.0;
     for (Event event : events) {
         totalTime += event.getDuration();
     }
     return totalTime;
 }
}
