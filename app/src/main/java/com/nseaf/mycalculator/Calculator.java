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
    boolean percentageClicked = false;

    int counter = 0;

    public Calculator() {
    }

    public void processNumber(int i) {
        if (numHasRadixPoint) {
            counter++;
        }
        if (numberString.length() < 12) {  // limit of 12 digits
            intNumber = intNumber * 10 + i;
            numberString = String.valueOf(intNumber);
            if (numHasRadixPoint) {
                realNumber = intNumber / (Math.pow(10, counter));
                System.out.println(realNumber);
                numberString = String.valueOf(realNumber);
            }
            if (operation != "" || numHasRadixPoint) {
                detailsString += i;
            } else detailsString = numberString;
        } else detailsString = "The number is too long..";
    }

    public void processDecimal() {
        if (!numHasRadixPoint) {
            detailsString += ".";
            numHasRadixPoint = true;
            numberString += ".";
            counter = 0;
        }
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
        numHasRadixPoint = false;
    }

    public void equals() {
        String numbers[];
        switch (operation) {
            case "+":
                System.out.println("inside pluss");
                numbers = detailsString.split("\\+");
                if (percentageClicked) {
                    result = Double.parseDouble(numbers[0]) + (Double.parseDouble(numbers[0]) * (Double.parseDouble((numbers[1].substring(0, numbers[1].length() - 1))) / 100));
                } else {
                    for (String number : numbers) {
                        result += Double.parseDouble(number);
                    }
                }
                break;
            case "-":
                numbers = detailsString.split("-");
                if (percentageClicked) {
                    result = Double.parseDouble(numbers[0]) - (Double.parseDouble(numbers[0]) * (Double.parseDouble((numbers[1].substring(0, numbers[1].length() - 1))) / 100));
                } else {
                    result = Double.parseDouble(numbers[0]);
                    for (int i = 1; i < numbers.length; i++) {
                        result -= Double.parseDouble(numbers[i]);
                    }
                }
                break;

            case "*":
                numbers = detailsString.split("\\*");
                if (percentageClicked) {
                    result = Double.parseDouble(numbers[0]) * (Double.parseDouble(numbers[0]) * (Double.parseDouble((numbers[1].substring(0, numbers[1].length() - 1))) / 100));
                } else {
                    result = Double.parseDouble(numbers[0]);
                    for (int i = 1; i < numbers.length; i++) {
                        result *= Double.parseDouble(numbers[i]);
                    }
                }
                break;
            case "/":
                numbers = detailsString.split("/");
                if (percentageClicked) {
                    result = Double.parseDouble(numbers[0]) / (Double.parseDouble(numbers[0]) * (Double.parseDouble((numbers[1].substring(0, numbers[1].length() - 1))) / 100));
                } else {
                    result = Double.parseDouble(numbers[0]);
                    for (int i = 1; i < numbers.length; i++) {
                        result /= Double.parseDouble(numbers[i]);
                    }
                }
                break;
            case "^":
                numbers = detailsString.split("\\^");
                result = Math.pow(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1]));

                // case for pi
            case "\uD835\uDF0B":
                numbers = detailsString.split("\uD835\uDF0B");
                result = Double.parseDouble(numbers[0]) * 3.14 * Double.parseDouble(numbers[1]);
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

    public void percentage() {
        percentageClicked = true;
        detailsString += "%";
    }
}
