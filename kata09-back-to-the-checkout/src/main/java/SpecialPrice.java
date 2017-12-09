import java.util.Map;

public interface SpecialPrice {
    Result calculate(Map<ItemSku, Integer> itemQuantities, RegularPrices regularPrices);

    class Result {
        public static final Result NULL = new Result(Money.of(0), Money.of(0));

        private Money calculatedPrice;
        private Money regularPrice;

        public Result(Money calculatedPrice, Money regularPrice) {
            this.calculatedPrice = calculatedPrice;
            this.regularPrice = regularPrice;
        }

        public Money calculatedPrice() {
            return calculatedPrice;
        }

        public Money regularPrice() {
            return regularPrice;
        }
    }
}