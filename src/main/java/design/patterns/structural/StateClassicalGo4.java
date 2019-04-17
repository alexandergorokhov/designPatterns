package design.patterns.structural;

public class StateClassicalGo4 {
    static class State {
        void on(LightSwitch lightSwitch) {
            System.out.println("Light already on ");
        }

        void off(LightSwitch lightSwitch) {
            System.out.println("Light already off ");
        }
    }

    static class OnState extends State {
        public OnState() {

            System.out.println("Light turned on");
        }

        @Override
        void off(LightSwitch lightSwitch) {
            System.out.println("Switching light off ");
            lightSwitch.setState(new OffState());

        }
    }

    static class OffState extends State {

        public OffState() {
            System.out.println("Light turned off");

        }

        @Override
        void on(LightSwitch lightSwitch) {
            System.out.println("Switching light on ");
            lightSwitch.setState(new OnState());
        }

    }

    static class LightSwitch {
        private State state;

        public LightSwitch() {
            this.state = new OffState();
        }

        public void on() {
            state.on(this);
        }

        public void off() {
            state.off(this);
        }

        public void setState(State state) {
            this.state = state;
        }
    }

    static class Demo {
        public static void main(String[] args) {
            LightSwitch lightSwitch= new LightSwitch();
            lightSwitch.on();
            lightSwitch.off();lightSwitch.off();
        }


    }
}
