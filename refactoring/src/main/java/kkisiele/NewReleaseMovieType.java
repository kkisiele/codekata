package kkisiele;

class NewReleaseMovieType extends MovieType {
    @Override
    public double amount(int daysRented) {
        return daysRented*3;
    }

    @Override
    public int frequentRenterPoints(int daysRented) {
        int result = super.frequentRenterPoints(daysRented);
        if(daysRented > 1) result++;
        return result;
    }
}
