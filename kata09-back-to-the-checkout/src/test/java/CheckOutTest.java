import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckOutTest {
    @Test
    public void testTotals() throws Exception {
        assertEquals(Money.of(0), price(""));
        assertEquals(Money.of(50), price("A"));
        assertEquals(Money.of(80), price("AB"));
        assertEquals(Money.of(115), price("CDBA"));

        assertEquals(Money.of(100), price("AA"));
        assertEquals(Money.of(130), price("AAA"));
        assertEquals(Money.of(180), price("AAAA"));
        assertEquals(Money.of(230), price("AAAAA"));
        assertEquals(Money.of(260), price("AAAAAA"));

        assertEquals(Money.of(160), price("AAAB"));
        assertEquals(Money.of(175), price("AAABB"));
        assertEquals(Money.of(190), price("AAABBD"));
        assertEquals(Money.of(190), price("DABABA"));
    }

    private Money price(String items) {
        CheckOut co = createCheckOut();
        for(int i = 0; i < items.length(); i++) {
            co.scan(items.substring(i, i+1));
        }
        return co.total();
    }

    private CheckOut createCheckOut() {
        Prices prices = new Prices();
        prices.addPrice("A", 50);
        prices.addSpecialPrice(new MultiItemsPriceCalculation(ItemSku.of("A"), 3, Money.of(130)));
        prices.addPrice("B", 30);
        prices.addSpecialPrice(new MultiItemsPriceCalculation(ItemSku.of("B"), 2, Money.of(45)));
        prices.addPrice("C", 20);
        prices.addPrice("D", 15);


        CheckOut checkOut = new CheckOut(prices);
        return checkOut;
    }

    @Test
    public void testIncremental() throws Exception {
        CheckOut co = createCheckOut();
        assertEquals(Money.of(0), co.total());
        co.scan("A");  assertEquals(Money.of(50), co.total());
        co.scan("B");  assertEquals(Money.of(80), co.total());
        co.scan("A");  assertEquals(Money.of(130), co.total());
        co.scan("A");  assertEquals(Money.of(160), co.total());
        co.scan("B");  assertEquals(Money.of(175), co.total());
    }
}
