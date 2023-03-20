package com.stripe;

import com.models.Matrix;
import com.models.Result;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CalculatorStripe {
    public Result multiply(Matrix first, Matrix second) {
        int fR = first.getMatrix().length;
        int sC = second.getMatrix()[0].length;

        Result result = new Result(new int[fR][sC]);
        ExecutorService executor = Executors.newFixedThreadPool(6);

        int i = 1;
        while (fR - i >= 0 || sC - i >= 0) {
            executor.submit(new MultiplierThreadStripe(first, second, result, fR - i, sC - i));
            i++;
        }

        try {
            executor.shutdown();
            executor.awaitTermination(100L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
