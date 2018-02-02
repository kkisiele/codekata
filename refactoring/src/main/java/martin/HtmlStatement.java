package martin;

class HtmlStatement extends Statement {
    String headerString(Customer aCustomer) {
        return "<H1>Rental Record for <EM>" + aCustomer.getName() + "</EM></H1><P>\n";
    }

    String eachRentalString(Rental aRental) {
        return aRental.getMovie().getTitle() + ": " + String.valueOf(aRental.getCharge()) + "<BR>\n";
    }

    String footerString(Customer aCustomer) {
        return "<P>You own <EM>" + String.valueOf(aCustomer.getTotalCharge()) + "</EM><P>\n" +
                "On this rental you earned <EM>" + String.valueOf(aCustomer.getTotalFrequentRenterPoints()) + "</EM> frequent renter points<P>";
    }
}
