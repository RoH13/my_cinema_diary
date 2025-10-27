package com.cinema.models;

public class Movie {

    private int id;
    private String title;
    private String director;
    private String genre;
    private int year;
    private int duration;
    private int rating;

    public Movie(int id, String title, String director, String genre, int year, int duration, int rating) {
        this.rating = rating;
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



    public void setRating(int rating) {
        if (rating < 1 || rating > 10)
            throw new IllegalArgumentException("Value can be in range(1,10");
        this.rating = rating;
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

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
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
                ", raiting=" + rating +
                '}';
    }
}
