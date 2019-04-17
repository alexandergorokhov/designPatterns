package design.patterns.solid;

import java.util.ArrayList;
import java.util.List;

public class OCPApp {

    public static void main(String[] args) {
        OCP.Product apple = new OCP.Product("Apple", OCP.Color.GREEN, OCP.Size.SMALL);
        OCP.Product tree = new OCP.Product("Tree", OCP.Color.GREEN, OCP.Size.LARGE);
        OCP.Product house = new OCP.Product("House", OCP.Color.BLUE, OCP.Size.HUGE);
        List<OCP.Product> products = new ArrayList<>();
        products.add(apple);
        products.add(tree);
        products.add(house);
        System.out.println("Specification pattern: ");
        System.out.println(products);

        OCP.BetterFilter bf = new OCP.BetterFilter();
        bf.filter(products, new OCP.ColorSpecification(OCP.Color.GREEN))
                .forEach(p -> System.out.println("The Green products; " + p));

        bf.filter(products, new OCP.SizeSpecification(OCP.Size.LARGE))
                .forEach(p -> System.out.println("The Large products; " + p));

        bf.filter(products, new OCP.SizeSpecification(OCP.Size.HUGE))
                .forEach(p -> System.out.println("The Huge products; " + p));

        System.out.println("Large blue items ");
        bf.filter(products,
                new OCP.AndSpecification<>(
                        new OCP.ColorSpecification(OCP.Color.BLUE),
                        new OCP.SizeSpecification(OCP.Size.HUGE)

                )
        ).forEach(p -> System.out.println("The Huge and Blue products; " + p));
    }

}
