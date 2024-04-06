package Behavioral.Template;

public class InPersonMeetingScheduler extends MeetingScheduler{

    private String meetingLocation;

    public InPersonMeetingScheduler(String meetingTitle) {
        super(meetingTitle);
    }
    @Override
    protected void createMeeting() {
        // Specialized implementation for creating an in-person meeting
        System.out.println("Creating an in-person meeting: " + meetingTitle);
        meetingLocation = "Conference Room A";
    }

    @Override
    protected void setScheduledDate() {
        // Specialized implementation for setting the scheduled date of an in-person meeting
        scheduledDate = "2023-06-15";
        System.out.println("In-person meeting scheduled for: " + scheduledDate);
    }
}
