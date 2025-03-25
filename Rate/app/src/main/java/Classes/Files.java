package Classes;

import android.util.Log;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Files {
    public Files(
    )
    {}
    public static void setKey(File dir, String Key)
    {
        try {

            String nume = "key.txt";


            FileWriter fileWriter = new FileWriter(dir + "/" + nume, false);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(Key);
            System.out.println(Key+"dsad");
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
                while((line = reader.readLine()) != null) {
                    Key=line;
                }
            } catch (IOException e) {
                Log.e("File Error", "Error reading file", e);
            }
        }
        System.out.println(Key+"cheie");
        return Key;
    }
    public static void setcomments(File dir, String comments)
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

            writer.write(comments+"\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Comments[] getcomments(File dir)
    {
        File file=new File(dir, "key.txt");
        Comments[] comments= new Comments[1000];
        int i=0;
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if(i%3==0)
                        comments[i/3]=new Comments(line);
                    else if(i%3==1)
                        comments[i/3].rating=Integer.parseInt(line);
                    else if(i%3==2)
                        comments[i/3].text=line;
                    i=i+1;
                }
            } catch (IOException e) {
                Log.e("File Error", "Error reading file", e);
            }
        }
        return comments;
    }
    public static void setGames(File dir, String comments)
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

            writer.write(comments+"\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Game[] getGames(File dir)
    {
        File file=new File(dir, "key.txt");
        Game[] comments= new Game[1000];
        int i=0;
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {

                    if(i%2==0)
                        comments[i/2]=new Game(line);
                    else
                        comments[i/2].ID=Integer.parseInt(line);
                    i=i+1;
                }
            } catch (IOException e) {
                Log.e("File Error", "Error reading file", e);
            }
        }
        return comments;
    }
}
