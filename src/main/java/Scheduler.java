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
            if (str[1].contains("lightning")) {
                str[1]= "5";
            }
            str[1] = str[1].replace("min","");
            str[1] = str[1].replaceAll(" ","");


            SEvent event = new SEvent(str[0],Integer.parseInt(str[1]));
            if(str[0].contains("Networking")){
                event.isNetworking = true;
            }
            listOfEvents.add(event);
        }
        setMorningSchedule(track1);
        setAfternoonSchedule(track1);
    }

    public void setMorningSchedule(ArrayList<SEvent> track){

        int timeAllotted = 0;
        for(SEvent event : listOfEvents){

            if(!track1.contains(event) && !event.isNetworking){
                if((timeAllotted <= 150 && event.duration == 30) || (timeAllotted <= 135 && event.duration ==45) || (timeAllotted <= 120 && event.duration == 60)){
                    track.add(event);
                    timeAllotted = timeAllotted + event.duration;
                }else{
                    track.add(lunch);
                    break;
                }
            }
        }
    }

    public void setAfternoonSchedule(ArrayList<SEvent> track){

        int timeAllotted = 0;
        for(SEvent event : listOfEvents){

            if(!track1.contains(event) && !event.isNetworking){
                if((timeAllotted <= 150 && event.duration == 30) || (timeAllotted <= 135 && event.duration ==45) || (timeAllotted <= 120 && event.duration == 60)){
                    track.add(event);
                    timeAllotted = timeAllotted + event.duration;
                }
                else if(event.isNetworking && timeAllotted == 180){
                    track.add(event);
                }

            }
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
    boolean isNetworking = false;

    SEvent(String t, int d){
        title = t;
        duration = d;
    }

    @Override
    public String toString() {
        return "Event: " + title + "\nDuration: " + duration + "\nNetworking event: " + isNetworking;
    }
}