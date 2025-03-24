package Classes;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Files {
    public Files()
    {}
    public static void setKey(File dir, String Key)
    {
        try {

            String nume = "key.txt";
            File file = new File(dir + "/" + nume);
                if (file.exists()) {
                boolean deleted = file.delete();
                if (deleted) {
                } else {
                    }
            }


            FileWriter fileWriter = new FileWriter(dir + "/" + nume, false);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(Key+"\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getKey(File dir)
    {
        File file=new File(dir, "key.txt");
        String Key="";
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    Key=line;
                }
            } catch (IOException e) {
                Log.e("File Error", "Error reading file", e);
            }
        }
        System.out.println("Key:"+Key);
        return Key;
    }
}
