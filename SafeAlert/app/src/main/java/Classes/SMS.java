package Classes;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.safealert.MainActivity;

import java.util.ArrayList;

public class SMS extends Service {

    private static final int REQUEST_CONTACTS_PERMISSION = 1;
    private static final int REQUEST_SMS_PERMISSION = 2;
    @Override
    public void onCreate() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            stopSelf();
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            stopSelf();
            return;
        }
        trimiteSms();
    }

    private ArrayList<String> getAllContactPhoneNumbers() {
        ArrayList<String> phoneNumberList = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();

        // Query for phone numbers
        Cursor cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phoneNumberList.add(phoneNumber);
            }
            cursor.close();
        }
        return phoneNumberList;
    }

    private void trimiteSms() {
        ArrayList<String> phoneNumbers = getAllContactPhoneNumbers();

        if (phoneNumbers.size() > 0) {
            double latitude = MainActivity.latitudine;
            double longitude = MainActivity.longitudine;
            String smsMessage = MainActivity.mesaj+String.valueOf(latitude)+" longitudine-"+String.valueOf(longitude);
            SmsManager smsManager = SmsManager.getDefault();

            for (String phoneNumber : phoneNumbers) {
                try {
                    smsManager.sendTextMessage(phoneNumber, null, smsMessage, null, null);
                    Toast.makeText(this, "SMS trimis la: " + phoneNumber+smsMessage, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                }
            }
        } else {
            Toast.makeText(this, "No contacts with phone numbers found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
