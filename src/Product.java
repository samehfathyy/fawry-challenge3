public class Product implements Shippable {
    private int id;
    private String name;
    private double price;
    private int available_quantity;
    private boolean shippable;
    private double weight_in_grams;

    public Product( String name, double price,int available_quantity,  boolean shippable_item, double weight_in_grams) {
        this.available_quantity = available_quantity;
        this.id = 0;
        this.name = name;
        this.price = price;
        this.shippable = shippable_item;

        this.weight_in_grams = weight_in_grams;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWeight() {
        return weight_in_grams;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity = available_quantity;
    }

    public boolean isShippable() {
        return shippable;
    }

    public void setShippable(boolean shippable) {
        this.shippable = shippable;
    }

    public double getWeight_in_grams() {
        return weight_in_grams;
    }

    public void setWeight_in_grams(double weight_in_grams) {
        this.weight_in_grams = weight_in_grams;
    }

}
