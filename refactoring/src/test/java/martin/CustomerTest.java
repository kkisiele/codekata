package martin;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {
    @Test
    public void testStatement() throws Exception {
        Assert.assertEquals("Rental Record for Kowalski\n" +
                "\tHelikopter w ogniu\t2.0\n" +
                "Amount owned is 2.0\n" +
                "You earned 1 frequent renter points", statement(starter.Movie.REGULAR, 1));
        Assert.assertEquals("Rental Record for Kowalski\n" +
                "\tHelikopter w ogniu\t5.0\n" +
                "Amount owned is 5.0\n" +
                "You earned 1 frequent renter points", statement(starter.Movie.REGULAR, 4));
        Assert.assertEquals("Rental Record for Kowalski\n" +
                "\tHelikopter w ogniu\t3.0\n" +
                "Amount owned is 3.0\n" +
                "You earned 1 frequent renter points", statement(starter.Movie.NEW_RELEASE, 1));
        Assert.assertEquals("Rental Record for Kowalski\n" +
                "\tHelikopter w ogniu\t6.0\n" +
                "Amount owned is 6.0\n" +
                "You earned 2 frequent renter points", statement(starter.Movie.NEW_RELEASE, 2));
        Assert.assertEquals("Rental Record for Kowalski\n" +
                "\tHelikopter w ogniu\t1.5\n" +
                "Amount owned is 1.5\n" +
                "You earned 1 frequent renter points", statement(starter.Movie.CHILDREN, 1));
        Assert.assertEquals("Rental Record for Kowalski\n" +
                "\tHelikopter w ogniu\t3.0\n" +
                "Amount owned is 3.0\n" +
                "You earned 1 frequent renter points", statement(starter.Movie.CHILDREN, 4));
    }

    @Test
    public void testHtmlStatement() throws Exception {
        Assert.assertEquals("<H1>Rental Record for <EM>Kowalski</EM></H1><P>\n" +
                "Helikopter w ogniu: 2.0<BR>\n" +
                "<P>You own <EM>2.0</EM><P>\n" +
                "On this rental you earned <EM>1</EM> frequent renter points<P>", htmlStatement(starter.Movie.REGULAR, 1));
    }

    private String statement(int priceCode, int daysRented) {
        Movie movie = new Movie("Helikopter w ogniu", priceCode);
        Rental rental = new Rental(movie, daysRented);
        Customer customer = new Customer("Kowalski");
        customer.addRental(rental);
        return customer.statement();
    }

    private String htmlStatement(int priceCode, int daysRented) {
        Movie movie = new Movie("Helikopter w ogniu", priceCode);
        Rental rental = new Rental(movie, daysRented);
        Customer customer = new Customer("Kowalski");
        customer.addRental(rental);
        return customer.htmlStatement();
    }
}
