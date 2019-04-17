package design.patterns.structural;

public class Memento {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(100);
        MementoToken m1 = bankAccount.deposit(50); //150
        MementoToken m2 =bankAccount.deposit(25); // 175

        System.out.println(bankAccount);

        bankAccount.restore(m1);
        System.out.println(bankAccount);

        bankAccount.restore(m2);
        System.out.println(bankAccount);

    }
}

class BankAccount {

    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public MementoToken deposit(int amount) {
        balance += amount;
        return new MementoToken(balance);
    }

    public void restore(MementoToken mementoToken) {
        balance = mementoToken.balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +


                ", balance=" + balance +
                '}';
    }
}

// THIS CLASS IS A SNAPSHOT OF THE BANK ACOCUNT
// it store the fields you want to restore later
class MementoToken {
    public int balance;

    public MementoToken(int balance) {
        this.balance = balance;
    }


}

