package com;

import com.models.Matrix;
import com.models.Result;
import com.newfox.FoxCalculator;
import com.stripe.CalculatorStripe;

import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        MatrixGenerator matrixGenerator = new MatrixGenerator();
        CalculatorStripe calculatorStripe = new CalculatorStripe();
        FoxCalculator calculatorFox = new FoxCalculator();

        int fR = 2000;
        int sC = 2000;
        int common = 2000;


        Matrix first = new Matrix(matrixGenerator.generate(fR, common, 5));
        Matrix second = new Matrix(matrixGenerator.generate(common, sC, 5));

        Instant startStrip = Instant.now();
        Result resultStripe = calculatorStripe.multiply(first, second);
        System.out.println("Result for strip method - " + Duration.between(startStrip, Instant.now()).toMillis());
        Instant startFox = Instant.now();
        Result resultFox = calculatorFox.multiply(first, second, 40);
        System.out.println("Result for fox method - " + Duration.between(startFox, Instant.now()).toMillis());

    }
}
