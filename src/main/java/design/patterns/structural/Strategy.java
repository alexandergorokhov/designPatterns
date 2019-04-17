package design.patterns.structural;

import java.util.List;

public class Strategy {

    enum OutputFormal {
        MARKDOWN, HTML
    }

    interface ListStrategy {
        default void start(StringBuilder sb) {
        }

        ;

        void addListItem(StringBuilder sb, String item);

        default void end(StringBuilder sb) {
        }


    }

    static class MarkdownListStrategy implements ListStrategy {

        @Override
        public void addListItem(StringBuilder sb, String item) {
            sb.append("*").append(item).append(System.lineSeparator());
        }
    }

    static class HtmlListStrategy implements ListStrategy {

        @Override
        public void start(StringBuilder sb) {
            sb.append("<ul>").append(System.lineSeparator());
        }

        @Override
        public void addListItem(StringBuilder sb, String item) {
            sb.append(" <li>").append(item).append("</li>").append(System.lineSeparator());
        }

        @Override
        public void end(StringBuilder sb) {
            sb.append("</ul>").append(System.lineSeparator());
        }
    }

    static class TextProcessor {

        private StringBuilder sb = new StringBuilder();
        private ListStrategy listStrategy;

        public TextProcessor(OutputFormal format) {
            setPutputFormat(format);

        }

        public void setPutputFormat(OutputFormal format) {
            switch (format) {

                case MARKDOWN:
                    listStrategy = new MarkdownListStrategy();
                    break;
                case HTML:
                    listStrategy = new HtmlListStrategy();
                    break;
            }
        }

        public void appendList(List<String> items) {
            listStrategy.start(sb);
            for (String item : items) listStrategy.addListItem(sb, item);
            listStrategy.end(sb);

        }

        public void clear (){
            sb.setLength(0);
        }

        @Override
        public String toString() {
            return sb.toString();
        }
    }
     static class Demo{
         public static void main(String[] args) {
             TextProcessor textProcessor =new TextProcessor(OutputFormal.MARKDOWN);
         //    textProcessor.appendList(List.of("a","b","c"));
             System.out.println(textProcessor);

             textProcessor.clear();
             textProcessor.setPutputFormat(OutputFormal.HTML);
          //   textProcessor.appendList(List.of("1","2","3"));
             System.out.println(textProcessor);

         }
    }
}


