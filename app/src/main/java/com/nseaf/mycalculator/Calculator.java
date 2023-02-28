package com.nseaf.mycalculator;

public class Calculator {
    String numberString = "0";
    String detailsString = "";
    String operation = "";

    long intNumber;
    long memoryInt = 0;
    double result = 0;

    double realNumber;
    double memoryDouble = 0.0;

    boolean isIntNumber = true;
    boolean numHasRadixPoint = false;
    boolean isIntMemory = true;

    public Calculator() {
    }

    public void processNumber(int i) {
        if (numberString.length() < 12) {  // limit of 12 digits
            intNumber = intNumber * 10 + i;
            numberString = String.valueOf(intNumber);
            if (operation != "") {
                detailsString += i;
            } else detailsString = numberString;
        } else detailsString = "The number is too long..";
    }

    public void clearClicked() {
        numberString = "0";
        detailsString = "";
        intNumber = 0;
        realNumber = 0.0;
        isIntNumber = true;
        numHasRadixPoint = false;
        result = 0;
    }

    public void processOperation(String operation) {
        this.operation = operation;
        detailsString += operation;
        intNumber = 0;
    }

    public void equals() {
        String numbers[];
        switch (operation) {
            case "+":
                numbers = detailsString.split("\\+");
                for (String number : numbers) {
                    result += Integer.parseInt(number);
                }
                break;
            case "-":
                numbers = detailsString.split("-");
                result = Integer.parseInt(numbers[0]);
                for (int i = 1; i < numbers.length; i++) {
                    result -= Integer.parseInt(numbers[i]);
                }
                break;

            case "*":
                numbers = detailsString.split("\\*");
                result = Integer.parseInt(numbers[0]);
                for (int i = 1; i < numbers.length; i++) {
                    result *= Integer.parseInt(numbers[i]);
                }
                break;
            case "/":
                numbers = detailsString.split("/");
                result = Integer.parseInt(numbers[0]);
                for (int i = 1; i < numbers.length; i++) {
                    result /= Integer.parseInt(numbers[i]);
                }
                break;
            case "^":
                numbers = detailsString.split("\\^");
                result = Math.pow(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));

                // case for pi
            case "\uD835\uDF0B":
                numbers = detailsString.split("\uD835\uDF0B");
                result = Integer.parseInt(numbers[0]) * 3.14 * Integer.parseInt(numbers[1]);
        }
        numberString = String.valueOf(result);
    }

    public void memPlusClicked() {
        if (isIntMemory) {
            if (isIntNumber) {
                memoryInt += intNumber;
                detailsString = "Memory: " + memoryInt;
            } else {
                isIntNumber = false;
                memoryDouble = memoryInt + realNumber;
            }
        }
    }

    public void memMinusClicked() {
        if (isIntMemory) {
            if (isIntNumber) {
                memoryInt -= intNumber;
                detailsString = "Memory: " + memoryInt;
            } else {
                isIntNumber = false;
                memoryDouble = memoryInt - realNumber;
            }
        }
    }

    public void memoryRecall() {
        numberString = detailsString = String.valueOf(memoryInt);
        intNumber = memoryInt;
    }

    public void memoryClear() {
        memoryInt = 0;
        numberString = "0";
        detailsString = "";
        intNumber = 0;
    }
}
