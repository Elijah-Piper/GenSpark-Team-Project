import java.util.ArrayList;
import java.util.HashMap;

public class Scheduler {

    ArrayList<SEvent> listOfEvents = new ArrayList<>();
    ArrayList<SEvent> track1 = new ArrayList<>();
    ArrayList<SEvent> track2 = new ArrayList<>();

}

class SEvent{
    String title;
    int duration;
    boolean isNetworking = false;
}