import java.util.List;

public class ShippingService {
    public static void printShippableItems(List<Shippable> shippableList){
        System.out.println("\n** Shipment notice **");
        double totalPackageWeight=0;
//        int index=0;
        for (Shippable e:shippableList){
            String name = e.getName();
            String weight = Double.toString(e.getWeight());
            System.out.printf("%s   %s gram\n",name,weight);
            totalPackageWeight += e.getWeight();
//            index++;
        }
        System.out.printf("Total package weight %.2f Kilogram\n\n",totalPackageWeight/1000);
    }
}
