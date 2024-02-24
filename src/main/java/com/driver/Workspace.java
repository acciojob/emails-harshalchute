//package com.driver;
//
//import org.apache.commons.lang3.tuple.Pair;
//
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Collections;
//
//public class Workspace extends Gmail{
//
//    private ArrayList<Meeting> calendar; // Stores all the meetings
//
//    public ArrayList<Meeting> getCalendar() {
//        return calendar;
//    }
//
//    public void setCalendar(ArrayList<Meeting> calendar) {
//        this.calendar = calendar;
//    }
//
//    public Workspace(String emailId) {
//        // The inboxCapacity is equal to the maximum value an integer can store.
//        super(emailId);
//        calendar = new ArrayList<>();
//    }
//
//    public void addMeeting(Meeting meeting){
//        //add the meeting to calendar
//        calendar.add(meeting);
//    }
//
//    public int findMaxMeetings(){
//        // find the maximum number of meetings you can attend
//        // 1. At a particular time, you can be present in at most one meeting
//        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
//        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
//        int count = 0;
//        for(int i = 0; i < calendar.size(); i++){
//            if(calendar.get(i).getStartTime().compareTo(calendar.get(i).getEndTime()) < 0 ){
//                count++;
//            }
//        }
//        return count;
//    }
//}

package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
        calendar = new ArrayList<>();

    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);

    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

        ArrayList<Meeting> curr = new ArrayList<>(calendar);

        Collections.sort(curr, Comparator.comparing(Meeting::getStartTime));

        LocalTime prevS = curr.get(0).getStartTime();
        LocalTime prevE = curr.get(0).getEndTime();

        int count=0;

        for(int i=1;i<curr.size();i++){

            LocalTime currS = curr.get(i).getStartTime();
            LocalTime currE = curr.get(i).getEndTime();

            if(currS.isAfter(prevE)){
                count++;
                prevS = currS;
                prevE = currE;
            }else{
                if(prevE.isAfter((currE))){
                    prevS = currS;
                    prevE = currE;
                }
            }
        }
        return count+1;
    }
}