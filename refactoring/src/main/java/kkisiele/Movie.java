package kkisiele;

public class Movie {
    private String title;
    private MovieType type;

    public Movie(String title, MovieType type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public double amount(int daysRented) {
        return type.amount(daysRented);
    }

    public int frequentRenterPoints(int daysRented) {
        return type.frequentRenterPoints(daysRented);
    }
}
