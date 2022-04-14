package expression.operations;

import expression.Expression;
import expression.types.GenericType;

public class Multiply<T> extends AbstractBinaryExpression<T> {

    public static final int priority = 3;
    public static final int specialType = 1;

    GenericType<T> typeObject;

    public Multiply(Expression<T> first, Expression<T> second, GenericType<T> typeObject) {
        super(first, second);
        this.typeObject = typeObject;
    }

    @Override
    protected T calculate(T x, T y) {
        return typeObject.multiply(x, y);
    }

    @Override
    protected String getOperation() {
        return "*";
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int getSpecialType() {
        return specialType;
    }
}