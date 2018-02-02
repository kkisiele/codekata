package kkisiele;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        return new PlainTextStatement(this).generate();
    }

    public double totalAmount() {
        double totalAmount = 0;
        for(Rental rental : rentals) {
            totalAmount += rental.amount();
        }
        return totalAmount;
    }

    public int frequentRenterPoints() {
        int result = 0;
        for(Rental rental : rentals) {
            result += rental.frequentRenterPoints();
        }
        return result;
    }

    public List<Rental> getRentals() {
        return rentals;
    }
}
