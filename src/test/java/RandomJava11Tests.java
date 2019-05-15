import org.junit.Test;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

public class RandomJava11Tests {

@Test
    public void question1Test(){
    Stream<Integer> numbers = Stream.of(1,2,3,4);
    numbers.map(n->n+10).peek(s->System.out.println(s));
    numbers.forEach(s->System.out.println(s));
}

@Test
    public void question2Test(){
    List<String> strList = List.of("A", "B", "C", "D", "E", "F", "G", "H");
    Spliterator<String> part1 = strList.spliterator().trySplit();
   // part1.tryAdvance(s->System.out.println(s));
    part1.forEachRemaining(s->System.out.println(s));

}
}
