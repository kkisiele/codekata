package kkisiele;

public abstract class MovieType {
    public static final MovieType NEW_RELEASE = new NewReleaseMovieType();
    public static final MovieType REGULAR = new RegularMovieType();
    public static final MovieType CHILDREN = new ChildrenMovieType();

    protected MovieType() {
    }

    public int frequentRenterPoints(int daysRented) {
        return 1;
    }

    public abstract double amount(int daysRented);
}
