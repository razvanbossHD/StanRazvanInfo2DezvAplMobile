public class SMSNotification extends Notification implements Notifiable{
    public void sendNotification()
    {
        System.out.println("Sms:"+getMessage());
    }
}
