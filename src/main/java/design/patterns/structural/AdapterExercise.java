package design.patterns.structural;

public class AdapterExercise {
    public static void main(String[] args) {
        SquareToRectangleAdapter squareToRectangleAdapter = new SquareToRectangleAdapter(new Square(2));
        int area = squareToRectangleAdapter.getArea();
    }
    static class Square {
        public int side;

        public Square(int side) {
            this.side = side;
        }
    }

   static class Rectangle {
        public int width;
        public int height;

        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    interface RectangleI {
        int getWidth();

        int getHeight();

        default int getArea() {
            return getWidth() * getHeight();
        }
    }

    static class SquareToRectangleAdapter implements RectangleI {

      private Square square;

        public SquareToRectangleAdapter(Square square) {

            this.square = square;
        }

        @Override
        public int getWidth() {
            return square.side;
        }

        @Override
        public int getHeight() {
            return square.side;
        }

    }
}
