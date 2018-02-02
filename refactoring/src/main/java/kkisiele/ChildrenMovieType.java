package kkisiele;

class ChildrenMovieType extends MovieType {
    @Override
    public double amount(int daysRented) {
        double result = 1.5;
        if(daysRented > 3)
            result += (daysRented - 3) * 1.5;
        return result;
    }
}
