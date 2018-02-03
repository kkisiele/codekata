import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CheckOutTest {
    @Test
    public void testTotals() throws Exception {
        assertEquals(BigDecimal.valueOf(0), price(""));
        assertEquals(BigDecimal.valueOf(50), price("A"));
        assertEquals(BigDecimal.valueOf(80), price("AB"));
        assertEquals(BigDecimal.valueOf(115), price("CDBA"));

        assertEquals(BigDecimal.valueOf(100), price("AA"));
        assertEquals(BigDecimal.valueOf(130), price("AAA"));
        assertEquals(BigDecimal.valueOf(180), price("AAAA"));
        assertEquals(BigDecimal.valueOf(230), price("AAAAA"));
        assertEquals(BigDecimal.valueOf(260), price("AAAAAA"));

        assertEquals(BigDecimal.valueOf(160), price("AAAB"));
        assertEquals(BigDecimal.valueOf(175), price("AAABB"));
        assertEquals(BigDecimal.valueOf(190), price("AAABBD"));
        assertEquals(BigDecimal.valueOf(190), price("DABABA"));
    }

    private BigDecimal price(String items) {
        CheckOut co = createCheckOut();
        for(int i = 0; i < items.length(); i++) {
            co.scan(items.substring(i, i+1));
        }
        return co.total();
    }

    private CheckOut createCheckOut() {
        PricingRules pricingRules = new PricingRules();
        pricingRules.addPrice(ItemSku.valueOf("A"), BigDecimal.valueOf(50));
        pricingRules.addPrice(ItemSku.valueOf("B"), BigDecimal.valueOf(30));
        pricingRules.addPrice(ItemSku.valueOf("C"), BigDecimal.valueOf(20));
        pricingRules.addPrice(ItemSku.valueOf("D"), BigDecimal.valueOf(15));
        pricingRules.addSpecialPrice(new MultiItemsPricingStrategy(ItemSku.valueOf("A"), 3, BigDecimal.valueOf(130)));
        pricingRules.addSpecialPrice(new MultiItemsPricingStrategy(ItemSku.valueOf("B"), 2, BigDecimal.valueOf(45)));

        return new CheckOut(pricingRules);
    }

    @Test
    public void testIncremental() throws Exception {
        CheckOut co = createCheckOut();
        assertEquals(BigDecimal.valueOf(0), co.total());
        co.scan("A");  assertEquals(BigDecimal.valueOf(50), co.total());
        co.scan("B");  assertEquals(BigDecimal.valueOf(80), co.total());
        co.scan("A");  assertEquals(BigDecimal.valueOf(130), co.total());
        co.scan("A");  assertEquals(BigDecimal.valueOf(160), co.total());
        co.scan("B");  assertEquals(BigDecimal.valueOf(175), co.total());
    }
}
