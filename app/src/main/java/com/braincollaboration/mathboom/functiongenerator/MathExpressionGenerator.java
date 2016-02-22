package com.braincollaboration.mathboom.functiongenerator;

import java.util.Random;

public class MathExpressionGenerator {

    private final static int DIV_FIRST_ARG_MAX_RANGE = 147;
    private final static int DIV_SECOND_ARG_MAX_RANGE = 18;

    private final static int SUB_ARG_MAX_RANGE = 143;

    private final static int SUMM_FIRST_ARG_MAX_RANGE = 65;
    private final static int SUMM_SECOND_ARG_MAX_RANGE = 46;

    private final static int MULT_FIRST_ARG_MAX_RANGE = 22;
    private final static int MULT_SECOND_ARG_MAX_RANGE = 18;

    private final static String PLUS_STRING = " + ";
    private final static String SUBSTRACT_STRING = " - ";
    private final static String DIVIDE_STRING = " / ";
    private final static String MULTIPLY_STRING = " * ";

    private final static int ID_PLUS = 0;
    private final static int ID_SUBSTRACT = 1;
    private final static int ID_DIVIDE = 2;
    private final static int ID_MULTIPLY = 3;

    public static Random mRandom = new Random();
    private static MathExpressionGenerator instance;

    private MathExpressionGenerator() {
        mRandom = new Random();
    }

    public static MathExpressionGenerator getInstance() {
        if (instance == null) {
            instance = new MathExpressionGenerator();
        }
        return instance;
    }

    public Function getRandomExpression() {
        Function randFunction = new Function();
        int functionID = mRandom.nextInt(4);
        switch (functionID){
            case ID_PLUS:
                randFunction = generateSummingFunc(randFunction);
                break;
            case ID_SUBSTRACT:
                randFunction = generateSubstractionFunc(randFunction);
                break;
            case ID_DIVIDE:
                randFunction = generateDividerFunc(randFunction);
                break;
            case ID_MULTIPLY:
                randFunction = generateMultiplyFunc(randFunction);
                break;
        }
        return randFunction;
    }

    private Function generateSummingFunc(Function function) {
        boolean isResultCorrect = mRandom.nextBoolean();
        int firstArg = getRandomIntInRange(1, SUMM_FIRST_ARG_MAX_RANGE);
        int secondArg = getRandomIntInRange(1, SUMM_SECOND_ARG_MAX_RANGE);
        int answer = firstArg + secondArg;
        String strFunction = String.valueOf(firstArg) + PLUS_STRING + String.valueOf(secondArg);
        if (!isResultCorrect) {
            answer = generateRandIncorrectResult(answer);
            function.setResult(String.valueOf(answer));
            function.setResultCorrect(false);
        }
        function.setResult(String.valueOf(answer));
        function.setResultCorrect(true);
        function.setFunction(strFunction);
        return function;
    }

    private Function generateSubstractionFunc(Function function) {
        boolean isResultCorrect = mRandom.nextBoolean();
        int firstArg = getRandomIntInRange(5, SUB_ARG_MAX_RANGE);
        int secondArg = getRandomIntInRange(1, SUB_ARG_MAX_RANGE);
        while ((firstArg - secondArg) <= 3) {
            secondArg = getRandomIntInRange(1, SUB_ARG_MAX_RANGE);
        }
        int answer = firstArg - secondArg;
        String strFunction = String.valueOf(firstArg) + SUBSTRACT_STRING + String.valueOf(secondArg);
        if (!isResultCorrect) {
            answer = generateRandIncorrectResult(answer);
            function.setResult(String.valueOf(answer));
            function.setResultCorrect(false);
        }
        function.setResult(String.valueOf(answer));
        function.setResultCorrect(true);
        function.setFunction(strFunction);
        return function;
    }

    private Function generateMultiplyFunc(Function function) {
        boolean isResultCorrect = mRandom.nextBoolean();
        int firstArg = getRandomIntInRange(2, MULT_FIRST_ARG_MAX_RANGE);
        int secondArg = getRandomIntInRange(2, MULT_SECOND_ARG_MAX_RANGE);
        int answer = firstArg * secondArg;
        String strFunction = String.valueOf(firstArg) + MULTIPLY_STRING + String.valueOf(secondArg);
        if (!isResultCorrect) {
            answer = generateRandIncorrectResult(answer);
            function.setResult(String.valueOf(answer));
            function.setResultCorrect(false);
        }
        function.setResult(String.valueOf(answer));
        function.setResultCorrect(true);
        function.setFunction(strFunction);
        return function;
    }

    private Function generateDividerFunc(Function function) {
        boolean isResultCorrect = mRandom.nextBoolean();
        int secondArg = getRandomIntInRange(2, DIV_SECOND_ARG_MAX_RANGE);
        int firstArg = getRandomIntInRange(1, DIV_FIRST_ARG_MAX_RANGE);
        while ((firstArg % secondArg) != 0) {
            firstArg = getRandomIntInRange(1, DIV_FIRST_ARG_MAX_RANGE);
        }
        String strFunction = String.valueOf(firstArg) + DIVIDE_STRING + String.valueOf(secondArg);
        int answer = firstArg / secondArg;
        if (!isResultCorrect) {
            answer = generateRandIncorrectResult(answer);
            function.setResult(String.valueOf(answer));
            function.setResultCorrect(false);
        }
        function.setResult(String.valueOf(answer));
        function.setResultCorrect(true);
        function.setFunction(strFunction);
        return function;
    }

    private int generateRandIncorrectResult(int correctResult) {
        boolean isPlusSide = mRandom.nextBoolean();
        int randValue = getRandomIntInRange(2, 6);
        if (isPlusSide) {
            correctResult += randValue;
        } else {
            correctResult -= randValue;
        }
        return Math.abs(correctResult);
    }

    private int getRandomIntInRange(int min, int max) {
        return mRandom.nextInt((max - min) + 1) + min;
    }

}
