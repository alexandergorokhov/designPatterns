package design.patterns.structural;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Command {
}

class BankAccount1 {
    private int balance;
    private int overdraftLimit = -500;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " , balance is now " + balance);
    }

    public boolean withdraw(int amount) {
        if (balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrew " + amount + " , balance is now " + balance);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}

interface Command1 {
    void call();

    void undo();
}

class BankAccountCommand implements Command1 {
    private BankAccount1 bankAccount1;
    private boolean succeded;

    @Override
    public void undo() {
        if(!succeded) return;
        switch (action) {
            case DEPOSIT:
                bankAccount1.withdraw(amount);
                break;
            case WITHDRAW:
                bankAccount1.deposit(amount);
                break;
        }
    }


    @Override
    public void call() {
        switch (action) {
            case DEPOSIT:
               succeded=true;
                bankAccount1.deposit(amount);
                break;
            case WITHDRAW:
                succeded = bankAccount1.withdraw(amount);
                break;

        }
    }

    public enum Action {DEPOSIT, WITHDRAW}

    private Action action;
    private int amount;

    public BankAccountCommand(BankAccount1 bankAccount1, Action action, int amount) {
        this.bankAccount1 = bankAccount1;
        this.action = action;
        this.amount = amount;
    }
}

class Demo5 {

    public static void main(String[] args) {
        BankAccount1 bankAccount1 = new BankAccount1();
        System.out.println(bankAccount1);
//        List<BankAccountCommand> commands = List.of(new BankAccountCommand(bankAccount1, BankAccountCommand.Action.DEPOSIT, 100),
//                new BankAccountCommand(bankAccount1, BankAccountCommand.Action.WITHDRAW, 1000)
//        );
        List<BankAccountCommand> commands = new ArrayList<>();
        commands.add(new BankAccountCommand(bankAccount1, BankAccountCommand.Action.DEPOSIT, 100));
        commands.add( new BankAccountCommand(bankAccount1, BankAccountCommand.Action.WITHDRAW, 1000));


        for (BankAccountCommand c : commands) {
            c.call();
            System.out.println(bankAccount1);
        }

        Collections.reverse(commands);



        for (BankAccountCommand c : commands) {
            c.undo();
            System.out.println(bankAccount1);
        }
    }


}
