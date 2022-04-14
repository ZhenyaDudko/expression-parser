package expression.operations;

import expression.Expression;

public abstract class AbstractBinaryExpression<T> implements Expression<T> {

    protected final Expression<T> first, second;

    public AbstractBinaryExpression(Expression<T> first, Expression<T> second) {
        this.first = first;
        this.second = second;
    }

    protected abstract T calculate(T x, T y);

    protected abstract String getOperation();

    public T evaluate(T x, T y, T z) {
        return calculate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + first + " " + getOperation() + " " + second + ")";
    }

    @Override
    public String toMiniString() {
        StringBuilder result = new StringBuilder();
        int priorityFirst = first.getPriority();
        int prioritySecond = second.getPriority();

        if (priorityFirst < getPriority()) {
            result.append("(").append(first.toMiniString()).append(")");
        } else {
            result.append(first.toMiniString());
        }
        result.append(" ").append(getOperation()).append(" ");
        if (getSpecialType() == 0 && prioritySecond < getPriority()
                || getSpecialType() == 1 && prioritySecond <= getPriority() && this.getClass() != second.getClass()
                || getSpecialType() == 2 && prioritySecond <= getPriority()) {
            result.append("(").append(second.toMiniString()).append(")");
        } else {
            result.append(second.toMiniString());
        }
        return result.toString();
    }

}
