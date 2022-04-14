package expression.operations;

import expression.Expression;
import expression.types.GenericType;

public class Abs<T> extends AbstractUnaryExpression<T>  {

    public static final int priority = 10;
    public static final int specialType = 0;

    GenericType<T> typeObject;

    public Abs(Expression<T> value, GenericType<T> typeObject) {
        super(value);
        this.typeObject = typeObject;
    }

    @Override
    protected T calculate(T x) {
        return typeObject.abs(x);
    }

    @Override
    protected String getOperation() {
        return "abs";
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
