import java.time.LocalDate;

public class PerishableProduct extends Product {
    LocalDate expire_date;

    public boolean isExpired(){
        return LocalDate.now().isAfter(expire_date) ;
    }
    public LocalDate getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(LocalDate expire_date) {
        this.expire_date = expire_date;
    }


    public PerishableProduct(String name, double price, int available_quantity, boolean shippable_item, double weight_in_grams, LocalDate expire_date) {
        super(name, price, available_quantity, shippable_item, weight_in_grams);
        this.expire_date = expire_date;
    }
}
