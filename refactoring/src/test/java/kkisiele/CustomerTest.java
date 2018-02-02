package kkisiele;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {
    @Test
    public void testStatement() throws Exception {
        Assert.assertEquals("Rental Record for Kowalski\n" +
                "\tHelikopter w ogniu\t2.0\n" +
                "Amount owned is 2.0\n" +
                "You earned 1 frequent renter points", statement(MovieType.REGULAR, 1));
        Assert.assertEquals("Rental Record for Kowalski\n" +
                "\tHelikopter w ogniu\t5.0\n" +
                "Amount owned is 5.0\n" +
                "You earned 1 frequent renter points", statement(MovieType.REGULAR, 4));
        Assert.assertEquals("Rental Record for Kowalski\n" +
                "\tHelikopter w ogniu\t3.0\n" +
                "Amount owned is 3.0\n" +
                "You earned 1 frequent renter points", statement(MovieType.NEW_RELEASE, 1));
        Assert.assertEquals("Rental Record for Kowalski\n" +
                "\tHelikopter w ogniu\t6.0\n" +
                "Amount owned is 6.0\n" +
                "You earned 2 frequent renter points", statement(MovieType.NEW_RELEASE, 2));
        Assert.assertEquals("Rental Record for Kowalski\n" +
                "\tHelikopter w ogniu\t1.5\n" +
                "Amount owned is 1.5\n" +
                "You earned 1 frequent renter points", statement(MovieType.CHILDREN, 1));
        Assert.assertEquals("Rental Record for Kowalski\n" +
                "\tHelikopter w ogniu\t3.0\n" +
                "Amount owned is 3.0\n" +
                "You earned 1 frequent renter points", statement(MovieType.CHILDREN, 4));
    }

    private String statement(MovieType movieType, int daysRented) {
        Movie movie = new Movie("Helikopter w ogniu", movieType);
        Rental rental = new Rental(movie, daysRented);
        Customer customer = new Customer("Kowalski");
        customer.addRental(rental);
        return customer.statement();
    }
}
