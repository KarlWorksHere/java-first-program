package com.h2;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class SavingsCalculator {
    private float[] credits;
    private float[] debits;

    public static void main(String[] args) {
        String [] creditsAsString = args[0].split(",");
        String [] debitsAsString = args[1].split(",");
        float [] credits = new float[creditsAsString.length];
        float [] debits = new float[debitsAsString.length];
        for (int i = 0; i< credits.length ; i++) {
            credits[i] = Utilities.getFloatValue(creditsAsString[i]);
        }
        for (int i = 0; i< debits.length ; i++) {
            debits[i] = Utilities.getFloatValue(debitsAsString[i]);
        }
        final SavingsCalculator calculator = new SavingsCalculator(credits, debits);
        float netSavings = calculator.calculate();
        System.out.println("Net Savings = " + netSavings + ", remaining days in month = " + remainingDaysInMonth(LocalDate.now()));
    }
    public SavingsCalculator(float[] credits, float[] debits){
        this.credits = credits;
        this.debits = debits;
    }
    private float sumOfCredits(){
        float sum = 0.0f;
        for (float credit : credits) {
            sum += credit;
        }
        return sum;
    }
    private float sumOfDebits(){
        float sum = 0.0f;
        for (float debt : debits) {
            sum += debt;
        }
        return sum;
    }
    static private int remainingDaysInMonth(LocalDate localDate){
        int totalDaysInMonth = localDate.lengthOfMonth();
        int dayOfMonth = localDate.getDayOfMonth();
        return totalDaysInMonth - dayOfMonth;
    }
    public float calculate(){
        return sumOfCredits() - sumOfDebits();
    }
}
