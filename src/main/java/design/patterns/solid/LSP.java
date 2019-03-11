package design.patterns.solid;

public class LSP {

    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2,3);
        userIt(rc);

        Rectangle sq = new Square();
        sq.setWidth(5);
        userIt(sq);



    }

    static void userIt(Rectangle r ){

        int width = r.getWidth();
        r.setHeight(10);
        System.out.println("Expect area of " + (width*10) + ", got " + r.getArea());

    }

    static class Rectangle {
        protected int width, height;

        public Rectangle() {
        }

        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        public String toString() {
            return "Rectangle{" +
                    "width=" + width +
                    ", height=" + height +
                    '}';
        }

        public boolean isSquare(){

            return width == height;
        }

        public int getArea() {

            return width * height;
        }

    }

    static class Square extends Rectangle {
        public Square() {
        }

        public Square(int size) {
            width = height = size;
        }

        @Override
        public void setWidth(int width) {
            super.setWidth(width);
            super.setHeight(width);
        }

        @Override
        public void setHeight(int height) {
            super.setHeight(height);
            super.setWidth(width);

        }
    }

}
