import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Scheduler {
    HashSet<SEvent> listOfEvents = new HashSet<>();
    ArrayList<SEvent> track1 = new ArrayList<>();
    ArrayList<SEvent> track2 = new ArrayList<>();
    SEvent lunch = new SEvent("lunch",60);


    Scheduler(String file){
        Scanner scan = null;
        String filePath = file;


        try {
            scan = new Scanner(new File(filePath));
        } catch (FileNotFoundException uhOh) {
            System.out.println("There is no file");
        }

        while (scan.hasNext()){
            String[] str;
            str = scan.nextLine().split("      ");

            str[1] = str[1].replace("min","");
            str[1] = str[1].replaceAll(" ","");
            if (str[1].contains("lightning")) {
                str[1]= "5";
            }
            if (str[1].contains("Event")) {
                str[1]= "0";
            }


            SEvent event = new SEvent(str[0],Integer.parseInt(str[1]));
            if(event.duration ==0){
                event.isNetworking = true;
            } else{
                event.isNetworking = false;
            }
            listOfEvents.add(event);
        }
        setMorningSchedule(track1);
        setAfternoonSchedule(track1);

        setMorningSchedule(track2);
        setAfternoonSchedule(track2);
    }

    public void setMorningSchedule(ArrayList<SEvent> track){

        int timeAllotted = 0;
        for(SEvent event : listOfEvents){

            if(!track1.contains(event) && !event.isNetworking){
                if((timeAllotted <= 150 && event.duration == 30) || (timeAllotted <= 135 && event.duration ==45) || (timeAllotted <= 120 && event.duration == 60)){
                    track.add(event);
                    timeAllotted = timeAllotted + event.duration;
                }else if(timeAllotted == 180){
                    track.add(lunch);
                    break;
                }
            }
        }
    }

    public void setAfternoonSchedule(ArrayList<SEvent> track){

        int timeAllotted = 0;
        SEvent network = null;
        for(SEvent event : listOfEvents){

            if(!track.contains(event) && !event.isNetworking){
                if((timeAllotted <= 150 && event.duration == 30) || (timeAllotted <= 135 && event.duration ==45) || (timeAllotted <= 120 && event.duration == 60)){
                    track.add(event);
                    timeAllotted = timeAllotted + event.duration;

                }
                else if(event.duration == 5){
                    track.add(event);

                }
            }
            else if(event.isNetworking){
                network = event;
            }

        }
        if(timeAllotted >= 180){
        track.add(network);
        }
    }

    public String printOut(){
        String print = "";
        for(SEvent event: track1){
            print = print + event.toString();
        }
        return print;
    }



}

class SEvent{
    String title;
    int duration;
    boolean isNetworking;

    SEvent(String t, int d){
        title = t;
        duration = d;
    }

    @Override
    public String toString() {
        return "Event: " + title + "        Duration: " + duration ;
    }
}
