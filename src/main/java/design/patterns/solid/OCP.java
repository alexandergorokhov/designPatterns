package design.patterns.solid;





import java.util.List;
import java.util.stream.Stream;

public class OCP {

    enum Color {
        RED, GREEN, BLUE;
    }

    enum Size {
        SMALL, MEDIUM, LARGE, HUGE
    }

    static class Product {
        public String name;
        public Color color;
        public Size size;

        public Product(String name, Color color, Size size) {
            this.name = name;
            this.color = color;
            this.size = size;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "name='" + name + '\'' +
                    ", color=" + color +
                    ", size=" + size +
                    '}';
        }
    }



    interface Specificaiton<T> {
        boolean isSatisfied(T item);
    }

    interface Filter<T> {
        Stream<T> filter(List<T> items, Specificaiton<T> specification);
    }

    static class ColorSpecification implements Specificaiton<Product> {
        private Color color;

        public ColorSpecification(Color color) {
            this.color = color;
        }

        @Override
        public boolean isSatisfied(Product item) {
            return item.color == color;
        }
    }

    static class SizeSpecification implements Specificaiton<Product> {
        private Size size;

        public SizeSpecification(Size size) {
            this.size = size;
        }

        @Override
        public boolean isSatisfied(Product item) {
            return item.size == size;
        }
    }

    static class BetterFilter implements Filter<Product>{

        @Override
        public Stream<Product> filter(List<Product> items, Specificaiton<Product> specification) {
            return items.stream().filter(p-> specification.isSatisfied(p));
        }
    }

    static class AndSpecification<T> implements Specificaiton<T>{
        private Specificaiton<T> first,second;

        public AndSpecification(Specificaiton<T> first, Specificaiton<T> second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean isSatisfied(T item) {
            return first.isSatisfied(item)&& second.isSatisfied(item);
        }
    }

}


