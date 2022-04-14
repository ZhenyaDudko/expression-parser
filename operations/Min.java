package expression.operations;

import expression.Expression;
import expression.types.GenericType;

public class Min<T> extends AbstractBinaryExpression<T> {

    public static final int priority = 1;
    public static final int specialType = 1;

    GenericType<T> typeObject;

    public Min(Expression<T> first, Expression<T> second, GenericType<T> typeObject) {
        super(first, second);
        this.typeObject = typeObject;
    }

    @Override
    protected T calculate(T x, T y) {
        return typeObject.min(x, y);
    }

    @Override
    protected String getOperation() {
        return "min";
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
