package Clase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class Contacts {
    private String name, phone, email;
    public Contacts(String name)
    {
        this.name=name;
    }
    public Contacts(String name, String phone, String email)
    {
        this.name=name;
        this.phone=phone;
        this.email=email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
