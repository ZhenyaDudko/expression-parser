package expression.types;

public class FloatType implements GenericType<Float> {
    @Override
    public Float parseInt(int x) {
        return (float) x;
    }

    @Override
    public Float add(Float x, Float y) {
        return x + y;
    }

    @Override
    public Float subtract(Float x, Float y) {
        return x - y;
    }

    @Override
    public Float pow(Float x, Float y) {
        int step = y.intValue();
        Float result = x;
        while (step > 1) {
            result *= x;
            step--;
        }
        return result;
    }

    @Override
    public Float log(Float x, Float y) {
        Float step = (float) 0;
        while (x >= y) {
            x = x / y;
            step++;
        }
        return step;
    }

    @Override
    public Float min(Float x, Float y) {
        return Float.min(x, y);
    }

    @Override
    public Float max(Float x, Float y) {
        return Float.max(x, y);
    }

    @Override
    public Float multiply(Float x, Float y) {
        return x * y;
    }

    @Override
    public Float divide(Float x, Float y) {
        return x / y;
    }

    @Override
    public Float abs(Float x) {
        return Math.abs(x);
    }

    @Override
    public Float negate(Float x) {
        return -x;
    }

    @Override
    public String toString(Float x) {
        return x.toString();
    }

    @Override
    public Float parseValue(String value) {
        return Float.parseFloat(value);
    }

    @Override
    public Float lzeroes(Float x) {
        return (float) Integer.numberOfLeadingZeros(Float.floatToIntBits(x));
    }

    @Override
    public Float tzeroes(Float x) {
        return (float) Integer.numberOfTrailingZeros(Float.floatToIntBits(x));
    }

    @Override
    public Float count(Float x) {
        return (float) Integer.bitCount(Float.floatToIntBits(x));
    }
}
