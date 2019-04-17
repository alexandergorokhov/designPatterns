package design.patterns.structural;

import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StateMoreRealisticNoFramework {
    enum State {
        OFF_HOOK, //starting state
        ON_HOOK, // terminal state, final state
        CONNECTING, //
        CONNECTED,
        ON_HOLD
    }

    enum Trigger {
        CALL_DIALED,
        HUNG_UP,
        CALL_CONNECTED,
        PLACED_ON_HOLD,
        TAKEN_OFF_HOLD,
        LEFT_MESSAGE,
        STOP_USING_PHONE
    }

    static class Demo {

        private static Map<State, List<Pair<Trigger, State>>> rules = new HashMap<>();
        private static State currentState = State.OFF_HOOK;
        private static State exitState = State.ON_HOOK;

        static {
            List list1 = new LinkedList();
            list1.add(new Pair<>(Trigger.CALL_DIALED, State.CONNECTING));
            list1.add(new Pair<>(Trigger.STOP_USING_PHONE, State.ON_HOOK));
            rules.put(State.OFF_HOOK, list1);
            List list2 = new LinkedList();
            list2.add(new Pair<>(Trigger.HUNG_UP, State.OFF_HOOK));
            list2.add(new Pair<>(Trigger.CALL_CONNECTED, State.CONNECTED));
            rules.put(State.CONNECTING, list2);
            List list3 = new LinkedList();
            list3.add(new Pair<>(Trigger.LEFT_MESSAGE, State.OFF_HOOK));
            list3.add(new Pair<>(Trigger.HUNG_UP, State.OFF_HOOK));
            list3.add(new Pair<>(Trigger.PLACED_ON_HOLD, State.ON_HOLD));
            rules.put(State.CONNECTED, list3);
            List list4 = new LinkedList();
            list4.add(new Pair<>(Trigger.TAKEN_OFF_HOLD, State.CONNECTED));
            list4.add(new Pair<>(Trigger.HUNG_UP, State.OFF_HOOK));
            rules.put(State.ON_HOLD, list4);


        }

        public static void main(String[] args) {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("The phone is currently " + currentState);
                System.out.println("Select a trigger: ");
                for (int i = 0; i < rules.get(currentState).size(); ++i) {
                    Trigger trigger = rules.get(currentState).get(i).getValue0();
                    System.out.println(" " + i + ". " + trigger);
                }
                boolean parseOk;
                int choice = 0;
                do {
                    try {
                        System.out.println("Please enter your choice: ");
                        choice = Integer.parseInt(console.readLine());
                        parseOk = choice >= 0 && choice < rules.get(currentState).size();
                    } catch (Exception e) {
                        parseOk = false;
                    }

                } while (!parseOk);
                currentState = rules.get(currentState).get(choice).getValue1();
                if (currentState == exitState) break;
            }
            System.out.println("And we are done ");
        }
    }

}
