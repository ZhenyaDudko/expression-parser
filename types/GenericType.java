package expression.types;

public interface GenericType<T> {
    T parseInt(int x);
    T add(T x, T y);
    T subtract(T x, T y);
    T pow(T x, T y);
    T log(T x, T y);
    T min(T x, T y);
    T max(T x, T y);
    T multiply(T x, T y);
    T divide(T x, T y);
    T abs(T x);
    T negate(T x);
    String toString(T x);
    T parseValue(String value);
    T lzeroes(T x);
    T tzeroes(T x);
    T count(T x);
}
