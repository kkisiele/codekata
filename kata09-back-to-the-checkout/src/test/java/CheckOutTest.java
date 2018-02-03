import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckOutTest {
    @Test
    public void testTotals() throws Exception {
        assertEquals(Money.valueOf(0), price(""));
        assertEquals(Money.valueOf(50), price("A"));
        assertEquals(Money.valueOf(80), price("AB"));
        assertEquals(Money.valueOf(115), price("CDBA"));

        assertEquals(Money.valueOf(100), price("AA"));
        assertEquals(Money.valueOf(130), price("AAA"));
        assertEquals(Money.valueOf(180), price("AAAA"));
        assertEquals(Money.valueOf(230), price("AAAAA"));
        assertEquals(Money.valueOf(260), price("AAAAAA"));

        assertEquals(Money.valueOf(160), price("AAAB"));
        assertEquals(Money.valueOf(175), price("AAABB"));
        assertEquals(Money.valueOf(190), price("AAABBD"));
        assertEquals(Money.valueOf(190), price("DABABA"));
    }

    private Money price(String items) {
        CheckOut co = createCheckOut();
        for(int i = 0; i < items.length(); i++) {
            co.scan(ItemSku.valueOf(items.substring(i, i+1)));
        }
        return co.total();
    }

    private CheckOut createCheckOut() {
        PricingRules pricingRules = new PricingRules();
        pricingRules.addPrice(ItemSku.valueOf("A"), Money.valueOf(50));
        pricingRules.addPrice(ItemSku.valueOf("B"), Money.valueOf(30));
        pricingRules.addPrice(ItemSku.valueOf("C"), Money.valueOf(20));
        pricingRules.addPrice(ItemSku.valueOf("D"), Money.valueOf(15));
        pricingRules.addSpecialPrice(new MultiItemsPricingStrategy(ItemSku.valueOf("A"), Quantity.valueOf(3), Money.valueOf(130)));
        pricingRules.addSpecialPrice(new MultiItemsPricingStrategy(ItemSku.valueOf("B"), Quantity.valueOf(2), Money.valueOf(45)));

        return new CheckOut(pricingRules);
    }

    @Test
    public void testIncremental() throws Exception {
        CheckOut co = createCheckOut();
        assertEquals(Money.valueOf(0), co.total());
        co.scan(ItemSku.valueOf("A"));  assertEquals(Money.valueOf(50), co.total());
        co.scan(ItemSku.valueOf("B"));  assertEquals(Money.valueOf(80), co.total());
        co.scan(ItemSku.valueOf("A"));  assertEquals(Money.valueOf(130), co.total());
        co.scan(ItemSku.valueOf("A"));  assertEquals(Money.valueOf(160), co.total());
        co.scan(ItemSku.valueOf("B"));  assertEquals(Money.valueOf(175), co.total());
    }
}
