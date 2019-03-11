package design.patterns.solid;

public class ISP {
    public static void main(String[] args) {

    }

    static class Document {

    }

    interface Machine {
        void print(Document document);

        void fax(Document document);

        void scan(Document document);
    }

    interface Printer{
        void print(Document document);
    }

    interface Scanner{
        void scan(Document document);
    }

    interface MultiFunctionDvice extends Printer, Scanner{

    }

    static class MultiFunctionMachine implements MultiFunctionDvice{
        private Printer printer;
        private Scanner scanner;

        public MultiFunctionMachine(Printer printer, Scanner scanner) {
            this.printer = printer;
            this.scanner = scanner;
        }

        @Override
        public void print(Document document) {
            printer.print(document);
        }

        @Override
        public void scan(Document document) {
            scanner.scan(document);
        }
    }

    static class justAPrinter implements Printer, Scanner{

        @Override
        public void print(Document document) {

        }

        @Override
        public void scan(Document document) {

        }
    }

    class MultiFunctionPrinter implements Machine{
        @Override
        public void print(Document document) {

        }

        @Override
        public void fax(Document document) {

        }

        @Override
        public void scan(Document document) {

        }
    }

  static  class OldFashionedPrinter implements Machine{

      @Override
      public void print(Document document) {

      }

      @Override
      public void fax(Document document) {

      }

      @Override
      public void scan(Document document) {

      }
  }
}
