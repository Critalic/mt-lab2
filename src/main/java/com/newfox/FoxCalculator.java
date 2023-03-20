package com.newfox;

import com.models.Matrix;
import com.models.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FoxCalculator {
    public Result multiply(Matrix first, Matrix second, int processNumber) {
        ExecutorService executorAdd = Executors.newCachedThreadPool();

        int fR = first.getMatrix().length;
        int sC = second.getMatrix()[0].length;
        int common = second.getMatrix().length;

        int blockFR = (int) Math.ceil((double) fR / processNumber);
        int blockSC = (int) Math.ceil((double) sC / processNumber);
        int blockCommon = (int) Math.ceil((double) common / processNumber);
        Result result = new Result(new int[fR][sC]);

        for (int i = 0; i < processNumber; i++) {
            for (int j = 0; j < processNumber; j++) {
                ExecutorService executorMultiplier = Executors.newCachedThreadPool();
                List<Matrix> toAdd = Collections.synchronizedList(new ArrayList<>());
                for (int k = 0; k < processNumber; k++) {
                    int startFR = i * blockFR;
                    int startSC = j * blockSC;
                    int startC = k * blockCommon;

                    executorMultiplier.submit(new MultiplierThread(toAdd, first, second,
                            new Coordinates(startFR, startC, startFR + blockFR, startC + blockCommon),
                            new Coordinates(startC, startSC, startC + blockCommon, startSC + blockSC)));
                }
                try {
                    executorMultiplier.shutdown();
                    executorMultiplier.awaitTermination(1L, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                executorAdd.submit(new AddThread(toAdd, result, i * blockFR, j * blockSC));
            }
        }
        try {
            executorAdd.shutdown();
            executorAdd.awaitTermination(100L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }


}
