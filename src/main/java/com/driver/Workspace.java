package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public ArrayList<Meeting> getCalendar() {
        return calendar;
    }

    public void setCalendar(ArrayList<Meeting> calendar) {
        this.calendar = calendar;
    }

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId);
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar = new ArrayList<>();
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int count = 0;
        Meeting currMeeting = calendar.get(0);
        for(Meeting meeting : calendar){
            if(meeting.getStartTime().isAfter(currMeeting.getEndTime()) ||
                    meeting.getStartTime().equals(currMeeting.getEndTime())){
                currMeeting = meeting;
                count++;
            }
        }
        return count;

    }
}
