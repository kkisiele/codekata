package kkisiele;

public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public double amount() {
        return getMovie().amount(daysRented);
    }

    public int frequentRenterPoints() {
        return getMovie().frequentRenterPoints(daysRented);
    }
}
