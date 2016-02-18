package com.braincollaboration.mathboom.functiongenerator;

import java.util.Random;

public class MathExpressionGenerator {

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
        randFunction = generateSummingFunc(randFunction);
//        int functionID = mRandom.nextInt(4);
//        switch (functionID){
//            case ID_PLUS:
//                randFunction = generateSummingFunc(randFunction);
//                break;
//            case ID_SUBSTRACT:
//                break;
//            case ID_DIVIDE:
//                break;
//            case ID_MULTIPLY:
//                break;
//        }
        return randFunction;
    }

    private Function generateSummingFunc(Function function) {
        boolean isResultCorrect = mRandom.nextBoolean();
        int firstArg = getRandomIntInRange(1, 25);
        int secondArg = getRandomIntInRange(1, 25);
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

    private int generateSubstractionFunc() {
        return 0;
    }

    private int generateMultiplyFunc() {
        return 0;
    }

    private int generateDividerFunc() {
        return 0;
    }

    private int generateRandIncorrectResult(int correctResult) {
        boolean isPlusSide = mRandom.nextBoolean();
        int randValue = getRandomIntInRange(2, 9);
        if (isPlusSide) {
            correctResult += randValue;
        } else {
            correctResult -= randValue;
        }
        return correctResult;
    }

    private int getRandomIntInRange(int min, int max) {
        return mRandom.nextInt((max - min) + 1) + min;
    }

}
