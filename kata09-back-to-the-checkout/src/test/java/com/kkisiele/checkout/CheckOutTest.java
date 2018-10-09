package com.kkisiele.checkout;

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
        TotalPriceCalculator totalPricing = new TotalPriceCalculator();
        totalPricing.addPrice(ItemSku.valueOf("A"), Money.valueOf(50));
        totalPricing.addPrice(ItemSku.valueOf("B"), Money.valueOf(30));
        totalPricing.addPrice(ItemSku.valueOf("C"), Money.valueOf(20));
        totalPricing.addPrice(ItemSku.valueOf("D"), Money.valueOf(15));
        totalPricing.addMultiItemsPrice(new Item("A", 3), Money.valueOf(130));
        totalPricing.addMultiItemsPrice(new Item("B", 2), Money.valueOf(45));

        return new CheckOut(totalPricing);
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
