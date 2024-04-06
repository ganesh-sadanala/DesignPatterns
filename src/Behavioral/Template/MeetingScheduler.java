package Behavioral.Template;

import java.util.ArrayList;
import java.util.List;

public abstract class MeetingScheduler {
    protected String meetingTitle;
    protected List<String> participants;
    protected String scheduledDate;

    public MeetingScheduler(String meetingTitle) {
        this.meetingTitle = meetingTitle;
        this.participants = new ArrayList<>();
    }

    public final void scheduleMeeting() {
        // Template method defining the scheduling process
        createMeeting();
        addParticipants();
        setScheduledDate();
        sendNotifications();
    }

    protected abstract void createMeeting();

    protected void addParticipants() {
        // Default implementation to add participants
        participants.add("Alice");
        participants.add("Bob");
        participants.add("Charlie");
    }

    protected abstract void setScheduledDate();

    protected void sendNotifications() {
        // Default implementation to send notifications
        System.out.println("Sending notifications to participants:");
        for (String participant : participants) {
            System.out.println("Notification sent to: " + participant);
        }
    }
}
