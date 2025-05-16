package com.example.miniprojet;

public class User {
    public String name;
    public String surname;
    public String email;

    // Default constructor required for Firebase
    public User() {
    }

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
