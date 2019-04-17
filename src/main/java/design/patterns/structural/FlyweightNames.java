package design.patterns.structural;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FlyweightNames {

    static class User {
        private String fullName;

        public User(String fullName) {
            this.fullName = fullName;
        }
    }

    static class User2 {
        static List<String> strings = new ArrayList<>();
        private int[] names;

        public User2(String fullname) {
            Function<String, Integer> getorAdd = (String s )->{
                int idx=strings.indexOf(s);
              if(idx!=-1) return idx;
              else {
                  strings.add(s);
                  return strings.size()-1;
              }
            };
        names = Arrays.stream(fullname.split(" "))
                .mapToInt(s-> getorAdd.apply(s))
                .toArray();

        }

    }


    static class Demo {
        public static void main(String[] args) {
            User2 user = new User2("Jonh Smith");
            User2 user2 = new User2("Jane Smith");


        }
    }
}
