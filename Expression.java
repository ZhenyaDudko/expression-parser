package expression;

public interface Expression<T> {
    T evaluate(T x, T y, T z);
    int getPriority();
    int getSpecialType();
    String toMiniString();
    String toString();
}
