package expression.operations;

import expression.Expression;
import expression.exceptions.EvaluateException;
import expression.types.*;

import java.util.Map;

public class Tabulator {

    private final Map<String, GenericType<?>> types = Map.of(
            "i", new IntegerType(),
            "d", new DoubleType(),
            "bi", new BigIntegerType(),
            "u", new UIntegerType(),
            "l", new LongType(),
            "f", new FloatType()
    );

    public Object[][][] tabulate(
            final String mode, final String expression,
            final int x1, final int x2, final int y1, final int y2, final int z1, final int z2
    ) throws Exception {
        return calculate(getType(mode), expression, x1, x2, y1, y2, z1, z2);
    }

    private GenericType<?> getType(final String mode) {
        if (types.containsKey(mode)) {
            return types.get(mode);
        }
        throw new IllegalArgumentException("Invalid mode parameter");
    }

    private <T> Object[][][] calculate(
            final GenericType<T> typeObject, final String expression,
            final int x1, final int x2, final int y1, final int y2, final int z1, final int z2
    ) throws Exception {
        final Expression<T> parsedExpression = new ExpressionParser<T>(typeObject).parseGeneric(expression);
        final Object[][][] table = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    try {
                        table[i][j][k] = parsedExpression.evaluate(
                                typeObject.parseInt(x1 + i),
                                typeObject.parseInt(y1 + j),
                                typeObject.parseInt(z1 + k)
                        );
                    } catch (final EvaluateException | ArithmeticException ignored) {}
                }
            }
        }
        return table;
    }
}
