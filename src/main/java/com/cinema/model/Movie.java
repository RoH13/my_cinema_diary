package com.cinema.model;

public class Movie {

    private int id;
    private String title;
    private String director;
    private String genre;
    private int year;
    private int duration;
    private int raiting;

    public Movie(int id, String title, String director, String genre, int year, int duration, int raiting) {
        this.raiting = raiting;
        this.genre = genre;
        this.year = year;
        this.duration = duration;
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

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", raiting=" + raiting +
                '}';
    }
}
