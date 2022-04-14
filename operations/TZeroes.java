package expression.operations;

import expression.Expression;
import expression.types.GenericType;

public class TZeroes<T> extends AbstractUnaryExpression<T> {

    public static final int priority = 10;
    public static final int specialType = 0;

    GenericType<T> typeObject;

    public TZeroes(Expression<T> value, GenericType<T> typeObject) {
        super(value);
        this.typeObject = typeObject;
    }

    @Override
    protected T calculate(T x) {
        return typeObject.tzeroes(x);
    }

    @Override
    protected String getOperation() {
        return "t0";
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
