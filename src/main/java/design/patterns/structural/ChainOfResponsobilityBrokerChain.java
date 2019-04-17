package design.patterns.structural;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

//chain of responsobility+ observer + mediator + memento
public class ChainOfResponsobilityBrokerChain {


    //Comand Query Separatin
    static class Event<Args> {
        private int index = 0;
        private Map<Integer, Consumer<Args>> handlers = new HashMap<>();


        public int subscribe(Consumer<Args> handler) {
            int i = index;
            handlers.put(index++, handler);
            return i;
        }

        public void unsubscribe(int key) {
            handlers.remove(key);
        }

        public void fire(Args args) {
            for (Consumer<Args> handler : handlers.values()) {
                handler.accept(args);
            }
        }
    }

    static class Query {
        public String creatureName;
        public Argument argument;
        public int result;

        enum Argument {
            ATTACK, DEFENSE;
        }

        public Query(String creatureName, Argument argument, int result) {
            this.creatureName = creatureName;
            this.argument = argument;
            this.result = result;
        }


    }

    //Mediator
    static class Game {
        public Event<Query> queries = new Event<>();

    }

    static class Creature {
        private Game game;
        public String name;
        public int baseAttack, baseDefense;

        public Creature(Game game, String name, int baseAttack, int baseDefense) {
            this.game = game;
            this.name = name;
            this.baseAttack = baseAttack;
            this.baseDefense = baseDefense;
        }

        public Game getGame() {
            return game;
        }

        public String getName() {
            return name;
        }

        public int getBaseAttack() {
            return baseAttack;
        }

        public int getBaseDefense() {
            return baseDefense;
        }

        public int getAttcak() {
            Query query = new Query(name, Query.Argument.ATTACK, baseAttack);
            game.queries.fire(query);
            return query.result;
        }

        public int getDefense() {
            Query query = new Query(name, Query.Argument.DEFENSE, baseDefense);
            game.queries.fire(query);
            return query.result;
        }

        @Override
        public String toString() {
            return "Creature{" +
                    "game=" + game +
                    ", name='" + name + '\'' +
                    ", Attack=" + getAttcak() +
                    ", Defense=" + getDefense() +
                    '}';
        }
    }

    static class CreatureModifier {
        protected Game game;
        protected Creature creature;

        public CreatureModifier(Game game, Creature creature) {
            this.game = game;
            this.creature = creature;
        }
    }


    static class DoubleAttackmodifier extends CreatureModifier implements AutoCloseable {
        private final int token;

        public DoubleAttackmodifier(Game game, Creature creature) {
            super(game, creature);
            token = game.queries.subscribe(query -> {
                if (query.creatureName.equals(creature.name) && query.argument == Query.Argument.ATTACK) {
                    query.result *= 2;
                }
            });
        }

        @Override
        public void close() {
            game.queries.unsubscribe(token);
        }
    }

    static class IncreaseDefenseModifier extends CreatureModifier {
        private final int token;

        public IncreaseDefenseModifier(Game game, Creature creature) {
            super(game, creature);
            token = game.queries.subscribe(query -> {
                if (query.creatureName.equals(creature.name) && query.argument == Query.Argument.DEFENSE) {
                    query.result += 3;
                }
            });
        }
    }

    static class Demo {
        public static void main(String[] args) {
            Game game = new Game();
            Creature goblin = new Creature(game, "Strong Goblin", 2, 2);
            System.out.println(goblin);
            IncreaseDefenseModifier increaseDefenseModifier = new IncreaseDefenseModifier(game, goblin);

            DoubleAttackmodifier doubleAttackmodifier = new DoubleAttackmodifier(game, goblin);
            System.out.println(goblin);
            doubleAttackmodifier.close();


            System.out.println(goblin);
        }
    }

}
