package design.patterns.structural;

public class ChainOfResponsobilityMethodChain {
    static class Creature {
        public String name;
        public int attack, defense;

        @Override
        public String toString() {
            return "Creature{" +
                    "name='" + name + '\'' +
                    ", attack=" + attack +
                    ", defense=" + defense +
                    '}';
        }

        public Creature(String name, int attack, int defense) {
            this.name = name;
            this.attack = attack;
            this.defense = defense;
        }
    }

    static class Creaturemodifier {
        protected Creature creature;
        protected Creaturemodifier next;

        public Creaturemodifier(Creature creature) {
            this.creature = creature;
        }

        public void add(Creaturemodifier creaturemodifier) {
            if (next != null) {
                next.add(creaturemodifier);
            } else {
                next = creaturemodifier;
            }
        }

        public void handle() {
            if (next != null) {
                next.handle();
            }
        }
    }

    static class DoubleAttackModifier extends Creaturemodifier {

        public DoubleAttackModifier(Creature creature) {
            super(creature);
        }

        @Override
        public void handle() {
            System.out.println("Doubling " + creature.name + " s attack");
            creature.attack *= 2;
            super.handle();
        }
    }

    static class IncreaseDefenseModifier extends Creaturemodifier {
        public IncreaseDefenseModifier(Creature creature) {
            super(creature);
        }

        @Override
        public void handle() {

            System.out.println("Increasing " + creature.name + " s defense");
            creature.defense += 3;
            super.handle();
        }
    }

    static class NoBonussesModifier extends Creaturemodifier{
        public NoBonussesModifier(Creature creature) {
            super(creature);
        }

        @Override
        public void handle() {
            System.out.println("No bonuses for you!");
        }
    }

    static class Demo {
        public static void main(String[] args) {
            Creature goblin = new Creature("Goblin ", 2, 2);
            System.out.println(goblin);

            Creaturemodifier root = new Creaturemodifier(goblin);
            root.add(new NoBonussesModifier(goblin));
            System.out.println("Double goblin attack..");
            root.add(new DoubleAttackModifier(goblin));

            System.out.println("Double goblin defense..");
            root.add(new IncreaseDefenseModifier(goblin));

            root.handle();
            System.out.println(goblin);
        }
    }

}
