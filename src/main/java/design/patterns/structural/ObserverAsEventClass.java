package design.patterns.structural;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ObserverAsEventClass {


}

class Event<TArgs> {

    private int count = 0;
    private Map<Integer, Consumer<TArgs>> handlers = new HashMap<>();

    public Subscription addHandler(Consumer<TArgs> handler) {
        int i = count;
        handlers.put(count++, handler);
        return new Subscription(this, i);
    }

    public void fire(TArgs args) {
        for (Consumer<TArgs> handler : handlers.values()) {
            handler.accept(args);
        }

    }

    public class Subscription implements AutoCloseable {

        private Event<TArgs> event;
        private int id; //key to the map

        public Subscription(Event<TArgs> event, int id) {
            this.event = event;
            this.id = id;
        }

        @Override
        public void close() throws Exception {
            event.handlers.remove(id);
        }
    }


}

class PropertyChangedEventArgs1 {

    public Object source;
    public String propertyName;

    public PropertyChangedEventArgs1(Object source, String propertyName) {
        this.source = source;
        this.propertyName = propertyName;
    }
}


class Person1 {
    public Event<PropertyChangedEventArgs1> propertyChanger = new Event<>();
    private int age;

    public void setAge(int age) {

        if (this.age == age) return;
        this.age = age;
        propertyChanger.fire(new PropertyChangedEventArgs1(this, "age"));
    }

    public int getAge() {
        return age;
    }
}


class Demo {
    public static void main(String[] args) throws Exception {
        Person1 person = new Person1();
        Event<PropertyChangedEventArgs1>.Subscription sub =
                person.propertyChanger.addHandler(x -> {
                    System.out.println("Person's "
                            + x.propertyName + " has changed");
                });

        person.setAge(17);
        person.setAge(18);
        sub.close();
        person.setAge(19);

    }

}