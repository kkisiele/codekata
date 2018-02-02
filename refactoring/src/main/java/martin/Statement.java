package martin;

import java.util.Iterator;

abstract class Statement {
    public String value(Customer aCustomer) {
        Iterator<Rental> it = aCustomer.getRentals();
        String result = headerString(aCustomer);
        while (it.hasNext()) {
            Rental each = it.next();
            result += eachRentalString(each);
        }
        result += footerString(aCustomer);
        return result;
    }

    abstract String eachRentalString(Rental each);
    abstract String headerString(Customer aCustomer);
    abstract String footerString(Customer aCustomer);
}
