package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

class Mails{
    Date date;
    String sender;
    String message;

    public Mails(Date date, String sender, String message) {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }
}


public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId){
        super(emailId);
    }
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
    }

    int receivedMail;
    TreeMap<Date,Mails> inbox = new TreeMap<>();
    TreeMap<Date,Mails> trash = new TreeMap<>();

    ArrayList<Date> dateAl = new ArrayList<>();
    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(this.receivedMail >= this.inboxCapacity){
            inbox.remove(inbox.firstKey());
        }
        inbox.put(date,new Mails(date,sender,message));
        dateAl.add(date);
        this.receivedMail+=1;
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(Date curr : inbox.keySet()){
            Mails currMail = inbox.get(curr);
            if(currMail.message.equals(message)){
                inbox.remove(curr);
                trash.put(currMail.date,new Mails(currMail.date,currMail.sender,currMail.message));
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        Mails mail = inbox.get(inbox.lastKey());
        return mail.message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        Mails mail = inbox.get(inbox.firstKey());
        return mail.message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for(int i = 0; i < dateAl.size(); i++){
            if(dateAl.get(i).compareTo(start)>=0 && dateAl.get(i).compareTo(end)<=0){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return this.receivedMail;
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
