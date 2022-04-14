package expression.operations;

import expression.Expression;

public abstract class AbstractUnaryExpression<T> implements Expression<T> {

    protected final Expression<T> value;

    protected AbstractUnaryExpression(Expression<T> value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(value.evaluate(x, y, z));
    }

    protected abstract T calculate(T x);

    protected abstract String getOperation();

    @Override
    public String toString() {
        return getOperation() + "(" + value.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (value.getPriority() >= getPriority()) {
            return getOperation() + " " + value.toMiniString();
        }
        return getOperation() + "(" + value.toMiniString() + ")";
    }
}
