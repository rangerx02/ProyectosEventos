/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UNIVALLE
 */

public class PaymentProcessor {
    private List<String> paymentSummary = new ArrayList<>();

    public double processCashPayment(CashPayment cashPayment, double remainingAmount) {
        double givenAmount = cashPayment.getGivenAmount();
        double usedAmount;
        
        if (givenAmount < remainingAmount) {
            usedAmount = givenAmount;
            paymentSummary.add("Efectivo: " + givenAmount + " (Pago insuficiente. Falta: " + (remainingAmount - givenAmount) + ")");
        } else {
            usedAmount = remainingAmount;
            double change = givenAmount - remainingAmount;
            paymentSummary.add("Efectivo: " + givenAmount + " (Cambio: " + change + ")");
        }
        return usedAmount;
    }
    
 
    public double processCardPayment(double amount, String cardType) {
        paymentSummary.add(cardType + ": " + amount);
        return amount;
    }

    public double processCheckPayment(double amount, double remainingAmount) {
        if (amount < remainingAmount * 0.1) {
            paymentSummary.add("Cheque rechazado: Monto insuficiente");
            return 0;
        }
        double usedAmount = Math.min(amount, remainingAmount);
        paymentSummary.add("Cheque: " + usedAmount);
        return usedAmount;
    }
    
    public double processPayPalPayment(double amount, double remainingAmount) {
        double usedAmount = Math.min(amount, remainingAmount);
        paymentSummary.add("PayPal: " + usedAmount);
        return usedAmount;
    }

    public String getSummary() {
        return String.join("\n", paymentSummary);
    }
}