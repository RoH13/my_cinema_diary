package com.cinema.model;

public class Movie {
    enum Status {
        PLANNED, WATCHING, WATCHED, DROPPED
    }
    private int id;
    private String title;
    private String director;
    private String genre;
    private int year;
    private int duration;
    private Status status;
    private int raiting;

    public Movie(int raiting, String genre, int year, int duration, Status status, String director, String title, int id) {
        this.raiting = raiting;
        this.genre = genre;
        this.year = year;
        this.duration = duration;
        this.status = status;
        this.director = director;
        this.title = title;
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setRaiting(int raiting) {
        if (raiting < 1 || raiting > 10)
            throw new IllegalArgumentException("Value can be in range(1,10");
        this.raiting = raiting;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setId(int id) {
        this.id = id;
    }
}
