package design.patterns.structural;

public class DynamicDecorator {

    interface Shape {
        String info();
    }

   static class Circle implements Shape {
        private float radius;

        public Circle(float radius) {
            this.radius = radius;
        }

        public Circle() {
        }

        ;


        void resize(float factor) {
            radius *= factor;
        }

        @Override
        public String info() {
            return "A circle of radius " + radius;
        }
    }

    static class Square implements Shape {
        private float side;

        public Square(float side) {
            this.side = side;
        }

        public Square() {
        }

        @Override
        public String info() {
            return "A square with side " + side;

        }
    }

    static class ColoredShape implements Shape {

        private Shape shape;
        private String color;

        public ColoredShape(Shape shape, String color) {
            this.shape = shape;
            this.color = color;
        }

        @Override
        public String info() {
            return shape.info() + " has the color " + color;
        }
    }

    static class TransparentShape implements Shape {
        private Shape shape;
        private int transparency;

        public TransparentShape(Shape shape, int transparency) {
            this.shape = shape;
            this.transparency = transparency;
        }

        @Override
        public String info() {
            return shape.info() + " has the transparency " + transparency + "% transparency";
        }
    }

    static class Demo{
        public static void main(String[] args) {
            Circle cicle = new Circle(10);
            System.out.println(cicle.info());

           ColoredShape blueSquare =  new ColoredShape(
                    new Square(20), "blue"

            );
            System.out.println(blueSquare.info());

            TransparentShape transparentShapeCircle = new TransparentShape(new ColoredShape(
                    new Circle(5),"green"), 50

            );
            System.out.println(transparentShapeCircle.info());
        }

    }

}