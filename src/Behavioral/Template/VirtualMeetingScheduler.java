package Behavioral.Template;

public class VirtualMeetingScheduler extends MeetingScheduler{
    private String meetingLink;

    public VirtualMeetingScheduler(String meetingTitle) {
        super(meetingTitle);
    }
    @Override
    protected void createMeeting() {

    }

    @Override
    protected void setScheduledDate() {

    }

    @Override
    protected void sendNotifications() {
        // Specialized implementation for sending notifications with meeting link
        System.out.println("Sending notifications to participants with meeting link:");
        for (String participant : participants) {
            System.out.println("Notification sent to: " + participant + " - Meeting Link: " + meetingLink);
        }
    }
}
