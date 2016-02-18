package com.braincollaboration.mathboom.functiongenerator;

public class Function {

    private String function;
    private String result;
    private boolean isResultCorrect;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isResultCorrect() {
        return isResultCorrect;
    }

    public void setResultCorrect(boolean resultCorrect) {
        isResultCorrect = resultCorrect;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
