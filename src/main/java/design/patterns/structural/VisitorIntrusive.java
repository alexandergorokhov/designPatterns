package design.patterns.structural;

public class VisitorIntrusive {
}

abstract class Expression {
    public abstract void print(StringBuilder stringBuilder);
}

class DoubleExpression extends Expression {

    private double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

    @Override
    public void print(StringBuilder stringBuilder) {
        stringBuilder.append(value);
    }
}

class AdditionExpression extends Expression {
    private Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print(StringBuilder stringBuilder) {
        stringBuilder.append("(");
        left.print(stringBuilder);
        stringBuilder.append(" +");
        right.print(stringBuilder);
        stringBuilder.append(" )");

    }
}

class Demo1 {
    public static void main(String[] args) {
        AdditionExpression e =new AdditionExpression(new DoubleExpression(1), new AdditionExpression(new DoubleExpression(2), new DoubleExpression(2)));
        StringBuilder stringBuilder = new StringBuilder();
        e.print(stringBuilder);
        System.out.println(stringBuilder);
    }
}
