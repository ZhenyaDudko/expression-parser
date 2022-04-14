package expression;

import expression.exceptions.EvaluateException;
import expression.exceptions.ParsingException;
import expression.operations.Tabulator;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Input should be: -mode expression");
            return;
        }
        try {
            Tabulator tabulator = new Tabulator();
            Object[][][] result = tabulator.tabulate(args[0].substring(1), args[1], -2, 2, -2, 2, -2, 2);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        System.out.println("x = " + (i - 2) + " y = " + (j - 2) + " z = " + (k - 2) + " result = " + result[i][j][k]);
                    }
                }
            }
        } catch (ParsingException e) {
            System.out.println("Exception during parsing: " + e.getMessage());
        } catch (EvaluateException e) {
            System.out.println("Exception during evaluating: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
