import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static final Cart cart = new  Cart();
    List<CartItem> Items;
//    double subTotal;
    private Cart(){
        Items=new ArrayList<>();
    }

    public static Cart getInstance(){
        return cart;
    }

    public void Add_item(int ProductId,int Quantity){
        ProductsRepo repo = ProductsRepo.getInstance();
        Product product = repo.getProduct(ProductId);
        if (Quantity<=0){
            System.out.printf("Failed to add %s, quantity should be a positive number.\n",product.getName());
            return;
        }
        int availableQuantity=product.getAvailable_quantity();
        List<CartItem> itemsToBeRemoved = new ArrayList<>();
        for(CartItem item : Items){
            if(ProductId==item.getProductId()){
                Quantity += item.getQuantity();
                itemsToBeRemoved.add(item);
            }
        }

        if(availableQuantity>=Quantity){
            Items.removeAll(itemsToBeRemoved);
            CartItem newItem = new CartItem(ProductId,Quantity);
            Items.add(newItem);
            return;
        }
        if(availableQuantity==0){
            System.out.printf("we are sorry! %s is out of Stock\n",product.getName());
            return;
        }
        System.out.printf("we are sorry! only %d items of %s is available\n",availableQuantity,product.getName());

    }

    public void checkOut(double balance){
if (Items.isEmpty()){
    System.out.println("Your Cart is empty.");
    return;
}
        //all products quantities in cart item list is available
        ProductsRepo repo = ProductsRepo.getInstance();
        List<Shippable> shippableList=new ArrayList<>();
        double subtotal=0;
        double shippingFees=0;
        double totalAmount=0;
        List<Integer> productsIdsToBeRemoved = new ArrayList<>();
        for(CartItem item : Items){
            Product product = repo.getProduct(item.getProductId());
            //System.out.printf("********** %s %d\n",product.getName(),item.getQuantity());
            //search for expired products
            if(product instanceof PerishableProduct){
               if (((PerishableProduct) product).isExpired()){
                   productsIdsToBeRemoved.add(product.getId());
                   continue;
                }
            }
            subtotal+=(product.getPrice()* item.getQuantity());
            //add shippable items to shippableList
            if(product.isShippable()){
                //assume shipping fees is 10$ per item
                shippingFees+= 10*item.getQuantity();
            for (int i=0;i<item.getQuantity();i++){
                shippableList.add(product);
                 }
            }
        }

        //remove expired items from cart
        if(!productsIdsToBeRemoved.isEmpty()){
            int size = productsIdsToBeRemoved.size();
            System.out.printf("these %d products are expired and have been removed from your cart.\n",size);
            for (int i:productsIdsToBeRemoved){
                System.out.printf(" - %s\n",repo.getProduct(i).getName());
                for (CartItem item:Items){
                    if(item.getProductId()==i){
                        Items.remove(item);
                        break;
                    }
                }
            }
        }

        totalAmount=subtotal+shippingFees;
        if(totalAmount<=balance){
        ShippingService.printShippableItems(shippableList);
        System.out.println("** Checkout receipt **");
        for (CartItem item:Items){
            Product product = repo.getProduct(item.getProductId());
            int quantity= item.getQuantity();
            int newQuantity = product.getAvailable_quantity()-quantity;
            String name = product.getName();
            double subtotalForThisItem = product.getPrice()*quantity;
            System.out.printf("%2dx %-15s %5.2f\n", quantity, name, subtotalForThisItem);
            repo.updateQuantity(item.getProductId(),newQuantity);
        }
        System.out.println("-------------------------");
            System.out.printf("%-20s %10.2f\n", "Subtotal:", subtotal);
            System.out.printf("%-20s %10.2f\n", "Shipping Fees:", shippingFees);
            System.out.printf("%-20s %10.2f\n", "Total Amount:", totalAmount);
        }else {
            System.out.printf("you cannot proceed your order.\ncurrent balance: %.2f\nTotal Amount: %.2f\n",balance,totalAmount);
        }


    }
}


