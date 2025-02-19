public class PushNotification extends Notification implements Notifiable{
    public void sendNotification()
    {
        System.out.println("Push:"+getMessage());
    }
}
