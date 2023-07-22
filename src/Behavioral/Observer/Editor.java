package Behavioral.Observer;

public class Editor {
    EventManager events;
    public Editor(){
        events = new EventManager();
    }

    public void openFile(){

    }

    public void saveFile(){

    }

    public EventManager getEvents() {
        return events;
    }
}
