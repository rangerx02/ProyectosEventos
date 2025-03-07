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

    public double processCashPayment(double amount, double remainingAmount) {
        double usedAmount = Math.min(amount, remainingAmount);
        double change = amount - usedAmount;
        paymentSummary.add("Efectivo: " + usedAmount + (change > 0 ? " (Cambio: " + change + ")" : ""));
        return usedAmount;
    }

    public double processCardPayment(double amount) {
        paymentSummary.add("Tarjeta: " + amount);
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