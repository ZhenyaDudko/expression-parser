package expression.types;

public class DoubleType implements GenericType<Double>{
    @Override
    public Double parseInt(int x) {
        return (double) x;
    }

    @Override
    public Double add(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double subtract(Double x, Double y) {
        return x - y;
    }

    @Override
    public Double pow(Double x, Double y) {
        int step = y.intValue();
        Double result = x;
        while (step > 1) {
            result *= x;
            step--;
        }
        return result;
    }

    @Override
    public Double log(Double x, Double y) {
        Double step = (double) 0;
        while (x >= y) {
            x = x / y;
            step++;
        }
        return step;
    }

    @Override
    public Double min(Double x, Double y) {
        return Double.min(x, y);
    }

    @Override
    public Double max(Double x, Double y) {
        return Double.max(x, y);
    }

    @Override
    public Double multiply(Double x, Double y) {
        return x * y;
    }

    @Override
    public Double divide(Double x, Double y) {
        return x / y;
    }

    @Override
    public Double abs(Double x) {
        return Math.abs(x);
    }

    @Override
    public Double negate(Double x) {
        return -x;
    }

    @Override
    public String toString(Double x) {
        return x.toString();
    }

    @Override
    public Double parseValue(String value) {
        return Double.parseDouble(value);
    }

    @Override
    public Double lzeroes(Double x) {
        return (double) Long.numberOfLeadingZeros(Double.doubleToLongBits(x));
    }

    @Override
    public Double tzeroes(Double x) {
        return (double) Long.numberOfTrailingZeros(Double.doubleToLongBits(x));
    }

    @Override
    public Double count(Double x) {
        return (double) Long.bitCount(Double.doubleToLongBits(x));
    }
}
