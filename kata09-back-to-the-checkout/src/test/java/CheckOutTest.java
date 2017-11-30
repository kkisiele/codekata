import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckOutTest {
    @Test
    public void testTotals() throws Exception {
        assertEquals(  0, price(""));
        assertEquals( 50, price("A"));
        assertEquals( 80, price("AB"));
        assertEquals(115, price("CDBA"));

        assertEquals(100, price("AA"));
        assertEquals(130, price("AAA"));
        assertEquals(180, price("AAAA"));
        assertEquals(230, price("AAAAA"));
        assertEquals(260, price("AAAAAA"));

        assertEquals(160, price("AAAB"));
        assertEquals(175, price("AAABB"));
        assertEquals(190, price("AAABBD"));
        assertEquals(190, price("DABABA"));
    }

    private int price(String items) {
        CheckOut co = createCheckOut();
        for(int i = 0; i < items.length(); i++) {
            co.scan(items.substring(i, i+1));
        }
        return co.total();
    }

    private CheckOut createCheckOut() {
        Prices prices = new Prices();
        prices.addPrice("A", 50);
        prices.addPrice("B", 30);
        prices.addPrice("C", 20);
        prices.addPrice("D", 15);

        prices.addSpecialPrice(new SpecialPrice("A", 3, 130));
        prices.addSpecialPrice(new SpecialPrice("B", 2, 45));

        CheckOut checkOut = new CheckOut(prices);
        return checkOut;
    }

    @Test
    public void testIncremental() throws Exception {
        CheckOut co = createCheckOut();
        assertEquals(  0, co.total());
        co.scan("A");  assertEquals( 50, co.total());
        co.scan("B");  assertEquals( 80, co.total());
        co.scan("A");  assertEquals(130, co.total());
        co.scan("A");  assertEquals(160, co.total());
        co.scan("B");  assertEquals(175, co.total());
    }
}
