package martin;

import java.util.ArrayList;
import java.util.Iterator;
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

    public Iterator<Rental> getRentals() {
        return rentals.iterator();
    }

    public String getName() {
        return name;
    }

    public String statement() {
        return new TextStatement().value(this);
    }

    public String htmlStatement() {
        return new HtmlStatement().value(this);
    }

    public int getTotalFrequentRenterPoints() {
        int result = 0;
        Iterator<Rental> it = rentals.iterator();
        while (it.hasNext()) {
            Rental each = it.next();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }

    public double getTotalCharge() {
        double result = 0;
        Iterator<Rental> it = rentals.iterator();
        while (it.hasNext()) {
            Rental each = it.next();
            result += each.getCharge();
        }
        return result;
    }
}
