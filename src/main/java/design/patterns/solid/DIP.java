package design.patterns.solid;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

}
