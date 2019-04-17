package design.patterns.structural;

public class VisitorClassicDobleDispatch {
}

interface ExpressionVisitior{
    void visit(DoubleExpression2 doubleExpression2);
    void visit(AdditionExpression2 e );

}
abstract class Expression2 {
    // public abstract void print(StringBuilder stringBuilder);
    public abstract void accept(ExpressionVisitior visitior);
}

class DoubleExpression2 extends Expression2 {

    public double value;

    public DoubleExpression2(double value) {
        this.value = value;
    }


    @Override
    public void accept(ExpressionVisitior visitior) {
        visitior.visit(this);
    }
}

class AdditionExpression2 extends Expression2 {
    public Expression2 left, right;

    public AdditionExpression2(Expression2 left, Expression2 right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(ExpressionVisitior visitior) {
        visitior.visit(this);
    }
}

class ExpressionPrinter2 implements ExpressionVisitior{
//    public static void print(Expression2 e, StringBuilder sb) {
//
//        if (e.getClass() == DoubleExpression2.class) {
//            sb.append(((DoubleExpression2) e).value);
//        } else if (e.getClass() == AdditionExpression2.class) {
//
//            AdditionExpression2 ae = (AdditionExpression2) e;
//            sb.append("(");
//            print(ae.left, sb);
//            sb.append("+");
//            print(ae.right, sb);
//            sb.append(")");
//        }
//    }

private StringBuilder sb = new StringBuilder();
    @Override
    public void visit(DoubleExpression2 doubleExpression2) {
sb.append(doubleExpression2.value);
    }

    @Override
    public void visit(AdditionExpression2 e) {
        sb.append("(");
        e.left.accept(this);
        sb.append("+");
        e.right.accept(this);
        sb.append(")");

    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

class Demo3 {
    public static void main(String[] args) {
        AdditionExpression2 e = new AdditionExpression2(new DoubleExpression2(1), new AdditionExpression2(new DoubleExpression2(2), new DoubleExpression2(2)));
        StringBuilder stringBuilder = new StringBuilder();
//        e.print(stringBuilder);

//        ExpressionPrinter2.print(e,stringBuilder);
//        System.out.println(stringBuilder);

        ExpressionPrinter2 ep = new ExpressionPrinter2();
        ep.visit(e);
        System.out.println(ep);
    }
}

