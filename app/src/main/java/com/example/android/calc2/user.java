package com.example.android.calc2;

public class user {
    String id;
    String name;
    String email;
    String degree;
    String city;

    public user() {
    }

    public user(String id, String name, String email, String degree, String city) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.degree = degree;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDegree() {
        return degree;
    }

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }
}
