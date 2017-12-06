public interface SpecialPrice {
    Result calculate(LineItems lineItems, RegularPrices regularPrices);

    class Result {
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