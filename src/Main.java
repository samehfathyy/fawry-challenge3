import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println();
        ProductsRepo repo = ProductsRepo.getInstance();
        Cart cart = Cart.getInstance();
        LocalDate non_expired_date= LocalDate.of(2026,1,1);
        LocalDate expired_date= LocalDate.of(2024,1,1);



        Product product0 = new Product("Laptop",15000,5,true,2200);
        repo.addProduct(product0); //laptop is affordable and shippable
        Product product1 = new Product("Iphone",55000,10,true,170);
        repo.addProduct(product1); //Iphone is too expensive
        Product product2 = new PerishableProduct("Cheese",50,10,false,1000,non_expired_date);
        repo.addProduct(product2); //Cheese isn't expired and not shippable
        Product product3 = new PerishableProduct("Milk",40,5,true,1000,expired_date);
        repo.addProduct(product3); //milk is expired and shippable



        /*
        id  name    quantity    price   shippable   expired
        0   laptop  5           15k     yes         no
        1   iphone  10          55k     yes         no
        2   cheese  10          50      no          no
        3   milk    5           40      yes         yes
        */
        double balance=50000;
        cart.Add_item(0,1);
        cart.Add_item(2,10);
        cart.Add_item(3,1);
        cart.checkOut(balance);
        cart.Add_item(2,10);

    }
}