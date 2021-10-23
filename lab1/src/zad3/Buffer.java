package zad3;

public class Buffer {
    private boolean occupied;
    private String message;

    public Buffer(){
        occupied = false;
        message = "";
    }

    public synchronized String take() throws InterruptedException {
        while(!occupied){
            wait();
        }
        String messageToConsume = getMessage();
        occupied = false;
        notifyAll();

        return messageToConsume;
    }

    public synchronized void put(String message) throws InterruptedException {
        while (occupied){
            wait();
        }
        occupied = true;
        setMessage(message);
        notifyAll();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
