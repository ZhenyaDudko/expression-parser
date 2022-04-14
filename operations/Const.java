package expression.operations;

import expression.Expression;
import expression.types.GenericType;

public class Const<T> implements Expression<T> {

    public static final int priority = 10;
    public static final int specialType = 0;

    protected T value;

    GenericType<T> typeObject;

    public Const(T value, GenericType<T> typeObject) {
        this.value = value;
        this.typeObject = typeObject;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return value;
    }

    @Override
    public String toString() {
        return typeObject.toString(value);
    }

    @Override
    public String toMiniString() {
        return toString();
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
