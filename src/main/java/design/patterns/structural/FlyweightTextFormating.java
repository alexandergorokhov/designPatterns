package design.patterns.structural;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlyweightTextFormating {


    static class FormatedText {
        private String plainText;
        private boolean[] capitalize;

        public FormatedText(String plainText) {
            this.plainText = plainText;
            capitalize = new boolean[plainText.length()];
        }

        public void capitalize(int start, int end) {
            for (int i = start; i <= end; ++i) {

                capitalize[i] = true;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < plainText.length(); ++i) {
                char c = plainText.charAt(i);
                sb.append(
                        capitalize[i] ? Character.toUpperCase(c)
                                : c

                );
            }
            return sb.toString();
        }
    }

    static class BetterFormattedText {

        private String plainText;
        private List<TextRange> formatting = new ArrayList<>();

        public BetterFormattedText(String plainText) {
            this.plainText = plainText;
        }


        public TextRange getRange(int start, int end) {

            TextRange textRange = new TextRange(start, end);
            formatting.add(textRange);
            return textRange;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < plainText.length(); ++i) {
                char c = plainText.charAt(i);
                for (TextRange range : formatting) {

                    if (range.covers(i) && range.capitalize) {
                        c = Character.toUpperCase(c);
                    }
                    sb.append(c);
                }

            }
            return sb.toString();
        }

        //Flyweight class
        public class TextRange {
            public int start, end;
            public boolean capitalize, bold, italic;

            public TextRange(int start, int end) {
                this.start = start;
                this.end = end;
            }

            public boolean covers(int position) {
                return position >= start && position <= end;
            }
        }
    }

    static class Demo {

        public static void main(String[] args) {
//            FormatedText formatedText = new FormatedText("This is a brave new world");
//            // formatedText.capitalize(10,15);
//            System.out.println(formatedText);
//
//            BetterFormattedText betterFormattedText = new BetterFormattedText("Make America Great Again");
//            betterFormattedText.getRange(13, 18).capitalize = true;
//            System.out.println(betterFormattedText);

            Sentence sentence = new Sentence("bla ble blajuria");
            sentence.getWord(1).capitalize=true;
            System.out.println(sentence);
        }
    }

    static class Sentence {
        private String[] words;
        private HashMap<Integer, WordToken> tokens = new HashMap<>();

        public Sentence(String words) {
            this.words = words.split(" ");
        }

        public WordToken getWord(int index) {
            WordToken wordToken = new WordToken();
            tokens.put(index, wordToken);
            return tokens.get(index);
        }


        @Override
        public String toString() {
            ArrayList<String> ws = new ArrayList<>();
            for (int i = 0; i < words.length; ++i) {
                String w = words[i];
                if (tokens.containsKey(i) && tokens.get(i).capitalize) {
                    w = w.toUpperCase();}
                    ws.add(w);

            }
            return String.join(" ", ws);

        }

        static class WordToken {
            public boolean capitalize;

        }
    }
}
