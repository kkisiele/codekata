package kkisiele;

class PlainTextStatement {
    private Customer customer;

    public PlainTextStatement(Customer customer) {
        this.customer = customer;
    }

    public String generate() {
        return header().concat(body()).concat(footer());
    }

    private String header() {
        return "Rental Record for " + customer.getName() + "\n";
    }

    private String body() {
        String result = "";
        for (Rental rental : customer.getRentals()) {
            result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rental.amount()) + "\n";
        }
        return result;
    }

    private String footer() {
        String result = "";
        result += "Amount owned is " + String.valueOf(customer.totalAmount()) + "\n";
        result += "You earned " + String.valueOf(customer.frequentRenterPoints()) + " frequent renter points";
        return result;
    }
}
