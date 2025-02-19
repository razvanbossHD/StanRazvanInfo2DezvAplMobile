//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.TimeUnit;



public class Main {
    static void ex1()
    {
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Introduce text");
        String text = scan.nextLine();
        char[] ch = text.toCharArray();
        StringBuilder temp1 = new StringBuilder();
        StringBuilder temp2 = new StringBuilder();
        int i;
        for (i = 0; i < ch.length; i++) {

            if(ch[i]>='A'&&ch[i]<='Z')
            {
                temp2.append(ch[i]);
            }
            else
                temp1.append(ch[i]);

        }
        text=temp1.toString()+temp2.toString();
        System.out.print(text);
    }
    static int sumadiv(int nr)
    {   int suma=0;
        for (int i=1;i<=nr;++i)
        {
            if(nr%i==0)
                suma+=i;
        }
        return suma;
    }
    static void ex2()
    {
        System.out.println("Introduceti cele 2 numere");
        Scanner scan = new Scanner(System.in);
        int nr1=scan.nextInt();
        int nr2=scan.nextInt();
        if(sumadiv(nr1)==sumadiv(nr2))
            System.out.println("Sunt prietene");
        else
            System.out.println("Nu sunt prietene");
    }
    static int pow(int nr, int power)
    {   int rez=1;
        while (power>0)
        {
            rez=rez*nr;
            power=power-1;
        }
        return rez;
    }
    static int hextodec(String hex)
    {
        char[] ch = hex.toCharArray();
        int dec = 0;

        // Traverse the character array
        for (int i = 0; i < ch.length; i++) {
            if(ch[i]>='0'&&ch[i]<='9') {dec=(ch[i]-'0')+(dec*16);

            }
            else if (ch[i]=='A') {
                dec=10+(dec*16);
            }
            else if (ch[i]=='B') {
                dec=11+(dec*16);
            }
            else if (ch[i]=='C') {
                dec=12+(dec*16);
            }
            else if (ch[i]=='D') {
                dec=13+(dec*16);
            }
            else if (ch[i]=='E') {
                dec=14+(dec*16);
            }
            else if (ch[i]=='F') {
                dec=15+(dec*16);
            }

        }
        return dec;
    }
    static int vai(String teren)
    {
        char[] ch = teren.toCharArray();
        int lvl = 0, nr=0;

        // Traverse the character array
        for (int i = 0; i < ch.length; i++) {
            if(ch[i]=='U'||ch[i]=='u')
            {
                lvl++;
                if(lvl==0)
                    nr++;
            }
            else if (ch[i]=='D'||ch[i]=='d') {
                lvl--;
            }

        }
        return nr;
    }
    static void ex3()
    {
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Introduce text");
        String text = scan.nextLine();
        System.out.println("Rezultatul este:"+hextodec(text));

    }
    static void ex4()
    {
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Introduce text");
        String text = scan.nextLine();
        System.out.println("Rezultatul este:"+vai(text));

    }
    static void ex5()
    {
        SMSNotification sms = new SMSNotification();
        EmailNotification email = new EmailNotification();
        PushNotification push = new PushNotification();
        sms.setMessage("cf?");
        email.setMessage("Stimate Domn Presedinte,");
        push.setMessage("cineva ti-a furat aurul!");
        sms.sendNotification();
        email.sendNotification();
        push.sendNotification();
        sms.displayMessage();
        email.displayMessage();
        push.displayMessage();

    }
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
            ex5();
    }
}