import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsRepo {
    private static final ProductsRepo Repo = new ProductsRepo();
    private List<Product> products;
    private int product_counter=0;
    //private constructor so no one can create instance of repo
    private ProductsRepo(){
        this.products = new ArrayList<>();
    }
    public static ProductsRepo getInstance(){
        return Repo;
    }
    public void addProduct(Product product){
        product.setId(product_counter);
        products.add(product);
        product_counter++;
    }
    public List<Product> getProductsList(){
        return products;
    }
    public Product getProduct(int Id){
        return products.get(Id);
    }
    public boolean checkQuantity(int ProductId,int Quantity){
        return products.get(ProductId).getAvailable_quantity() >= Quantity;
    }
}
