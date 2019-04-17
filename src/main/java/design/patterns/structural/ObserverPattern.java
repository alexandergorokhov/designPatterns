package design.patterns.structural;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern implements Observer<Person> {

    public static void main(String[] args) {
        new ObserverPattern();
    }

    public ObserverPattern() {
        Person person = new Person();
        person.subscribe(this);
        for (int i =20;i<24;i++){

            person.setAge(i);
        }
    }

    @Override
    public void handle(PropertyChangedEventArgs<Person> propertyChangedEventArgs) {
        System.out.println("Persons " + propertyChangedEventArgs.propertyName + " has changed to " + propertyChangedEventArgs.newValue);
    }
}

class Person extends Observable<Person> {
    private int age;


    public void setAge(int age) {
        if(this.age==age)return;
        this.age = age;
        propertyChanges(this,"age",age);
    }
}


class PropertyChangedEventArgs<T> {
    public T source;
    public String propertyName;
    public Object newValue;

    public PropertyChangedEventArgs(T source, String propertyName, Object newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.newValue = newValue;
    }
}
class Observable<T>{

    private List<Observer<T>> observableList = new ArrayList<>();

    public void subscribe(Observer<T> observer){
        observableList.add(observer);
    }

    protected void propertyChanges(T source, String propertyName, Object newValue){
        for(Observer<T> o: observableList){
            o.handle(new PropertyChangedEventArgs<T>(source,propertyName,newValue));

        }

    }
}

interface Observer<T>{
void handle(PropertyChangedEventArgs<T> propertyChangedEventArgs);
}
