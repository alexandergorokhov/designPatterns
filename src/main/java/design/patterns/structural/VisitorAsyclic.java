package design.patterns.structural;

public class VisitorAsyclic {
}
interface Visitor{} //Marker interface

interface ExpressionVisitor3 extends Visitor{
    void visit(Expression3 expression3);
}

interface DoubleExpressionVisitorExpressionVisitor3 extends Visitor{
    void visit(DoubleExpression3 expression3);
}

interface AdditinExpressionVisitor3 extends Visitor{
    void visit(AdditionExpression3 expression3);
}


abstract class Expression3 {
    // public abstract void print(StringBuilder stringBuilder);
    public  void accept(Visitor visitior){
        if(visitior instanceof ExpressionVisitor3){
            ((ExpressionVisitor3) visitior).visit(this);
        }

    }

}

class DoubleExpression3 extends Expression3 {

    public double value;

    public DoubleExpression3(double value) {
        this.value = value;
    }
    @Override
    public  void accept(Visitor visitior){
        if(visitior instanceof DoubleExpressionVisitorExpressionVisitor3){
            ((DoubleExpressionVisitorExpressionVisitor3) visitior).visit(this);
        }

    }
}

class AdditionExpression3 extends Expression3 {
    public Expression3 left, right;

    public AdditionExpression3(Expression3 left, Expression3 right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public  void accept(Visitor visitior){
        if(visitior instanceof AdditinExpressionVisitor3){
            ((AdditinExpressionVisitor3) visitior).visit(this);
        }

    }
}

//class ExpressionPrinter3 implements DoubleExpressionVisitorExpressionVisitor3, AdditinExpressionVisitor3{
class ExpressionPrinter3 implements  AdditinExpressionVisitor3{
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
   // @Override
    public void visit(DoubleExpression3 doubleExpression2) {
        sb.append(doubleExpression2.value);
    }

    @Override
    public void visit(AdditionExpression3 e) {
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

class Demo4 {
    public static void main(String[] args) {
        AdditionExpression3 e = new AdditionExpression3(new DoubleExpression3(1), new AdditionExpression3(new DoubleExpression3(2), new DoubleExpression3(2)));
        StringBuilder stringBuilder = new StringBuilder();


        ExpressionPrinter3 ep = new ExpressionPrinter3();
        ep.visit(e);
        System.out.println(ep);
    }
}


