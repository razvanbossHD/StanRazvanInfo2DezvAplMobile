public abstract class Notification {
    private String message="mesaj";

    public void displayMessage()
    {
        System.out.println(message);
    }

    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message=message;
    }
}
