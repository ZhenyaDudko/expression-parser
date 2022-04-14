package expression.operations;

import expression.Expression;
import expression.types.GenericType;

public class Add<T> extends AbstractBinaryExpression<T> {

    public static final int priority = 2;
    public static final int specialType = 0;

    GenericType<T> typeObject;

    public Add(final Expression<T> first, final Expression<T> second, GenericType<T> typeObject) {
        super(first, second);
        this.typeObject = typeObject;
    }

    @Override
    protected T calculate(T x, T y) {
        return typeObject.add(x, y);
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int getSpecialType() {
        return specialType;
    }

    @Override
    protected String getOperation() {
        return "+";
    }
}
