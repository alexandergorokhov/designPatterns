package design.patterns.solid;

import org.javatuples.Triplet;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class DIP {
    enum Relationship {
        PARRENT, CHILD, SIBLING;
    }

    interface RelationshipBrowser {
        public List<Person> findAllChilderOf(String name);
    }

    public static void main(String[] args) {
        Person parrent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parrent, child1);
        relationships.addParentAndChild(parrent, child2);
        new Research(relationships);
    }

    static class Person {
        public String name;

        public Person(String person) {
            this.name = person;
        }
    }

    static class Relationships implements RelationshipBrowser { // low-level because it is relatted to data storage
        private List<Triplet<Person, Relationship, Person>> relationships = new ArrayList<>();

        public void addParentAndChild(Person parrent, Person child) {
            relationships.add(new Triplet<>(parrent, Relationship.PARRENT, child));
            relationships.add(new Triplet<>(child, Relationship.CHILD, parrent));
        }

        public List<Triplet<Person, Relationship, Person>> getRelationships() {
            return relationships;
        }

        @Override
        public List<Person> findAllChilderOf(String name) {
            return relationships.stream()
                    .filter(x -> x.getValue0().name.equals("John") && x.getValue1() == Relationship.PARRENT)
                    .map(Triplet::getValue2)
                    .collect(Collectors.toList());
        }
    }

    static class Research // high level because it is to the user
    {
//        public Research(Relationships relationships) {
//            List<Triplet<Person, Relationship, Person>> relations = relationships.getRelationships();
//            relations.stream()
//                    .filter(x -> x.getValue0().name.equals("John") && x.getValue1() == Relationship.PARRENT)
//                    .forEach(ch -> System.out.println("Jonh has a child called " + ch.getValue2().name));
//        }

        public Research(RelationshipBrowser relationshipBrowser) {
            List<Person> list=relationshipBrowser.findAllChilderOf("John");
            for (Person child: list){
                System.out.println("John has a child called "+ child.name);
            }

        }


    }

    static class DateBucket {
        // Simplified for the example, should have getters at least for these fields
        final Instant from;
        final Instant to;

        DateBucket(Instant from, Instant to) {
            this.from = from;
            this.to = to;
        }


        public static List<DateBucket> bucketize(ZonedDateTime fromDate,

                                             ZonedDateTime toDate,
                                             int bucketSize,
                                             ChronoUnit bucketSizeUnit) {
        List<DateBucket> buckets = new ArrayList<>();
        boolean reachedDate = false;
        for (int i = 0; !reachedDate; i++) {
            ZonedDateTime minDate = fromDate.plus(i * bucketSize, bucketSizeUnit);
            ZonedDateTime maxDate = fromDate.plus((i + 1) * bucketSize, bucketSizeUnit);
            reachedDate = toDate.isBefore(maxDate);
            buckets.add(new DateBucket(minDate.toInstant(), maxDate.toInstant()));
        }

        return buckets;
    }

    public static List<DateBucket> bucketize2(ZonedDateTime fromDate,
                                             ZonedDateTime toDate,
                                             int bucketSize,
                                             ChronoUnit bucketSizeUnit) {
        return LongStream.rangeClosed(0, bucketSizeUnit.between(fromDate, toDate))
                .mapToObj(inc -> {
                    ZonedDateTime minDate = fromDate.plus(inc * bucketSize, bucketSizeUnit);
                    ZonedDateTime maxDate = fromDate.plus((inc + 1) * bucketSize, bucketSizeUnit);
                    return new DateBucket(minDate.toInstant(), maxDate.toInstant());
                })
                .filter(bucket -> {
                    ZonedDateTime maxDate = bucket.to.atZone(toDate.getZone());
                    ZonedDateTime limitDate = toDate.plus(bucketSize, bucketSizeUnit);
                    return maxDate.isBefore(limitDate) || maxDate.isEqual(limitDate);
                })
                .collect(Collectors.toList());



    }
    }}
