public class CartItem {
    private int ProductId;
    private int Quantity;

    public CartItem(int productId, int quantity) {
        ProductId = productId;
        Quantity = quantity;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
