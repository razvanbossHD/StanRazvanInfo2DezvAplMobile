public class EmailNotification extends Notification implements Notifiable{
    public void sendNotification()
    {
        System.out.println("Email:"+getMessage());
    }
}