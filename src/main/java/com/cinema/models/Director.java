package com.cinema.models;

public class Director {
    private int id;
    private String firstName;
    private String secondName;

    public Director(int id, String firstName, String lastNAme) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = lastNAme;
    }

    public int getId() {
        return id;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastNAme='" + secondName + '\'' +
                '}';
    }
}
