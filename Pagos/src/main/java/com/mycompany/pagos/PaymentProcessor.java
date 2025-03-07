/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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

  
    public double processCheckPayment(MoneyCheckPayment checkPayment, double remainingAmount) {
        //Verificar si el usuario canceló la entrada del valor del cheque
        if (!checkPayment.isValid()) {
            return 0;
        }
        
        double checkAmount = checkPayment.getCheckAmount();
        //Verificar la regla del 10%
        if (remainingAmount < checkAmount * 0.1) {
            String errorMsg = "Cheque rechazado: El monto a pagar (" + remainingAmount + 
                    ") debe ser al menos el 10% del valor del cheque (" + (checkAmount * 0.1) + ")";
            JOptionPane.showMessageDialog(null, errorMsg, "Error de validación", JOptionPane.ERROR_MESSAGE);
            paymentSummary.add(errorMsg);
            return 0;
        }
        
        //El monto a usar es el menor entre el valor pendiente y el valor del cheque
        double usedAmount = Math.min(remainingAmount, checkAmount);
        double change = checkAmount - usedAmount;
        
        //Mostrar el cambio si corresponde
        if (change > 0) {
            JOptionPane.showMessageDialog(null, 
                    "Pago con cheque aceptado:\n" +
                    "Valor del cheque: " + checkAmount + "\n" +
                    "Monto aplicado: " + usedAmount + "\n" +
                    "Cambio a devolver: " + change,
                    "Pago exitoso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, 
                    "Pago con cheque aceptado:\n" +
                    "Valor del cheque: " + checkAmount + "\n" +
                    "Monto aplicado: " + usedAmount,
                    "Pago exitoso", JOptionPane.INFORMATION_MESSAGE);
        }
        //Registrar en el resumen
        paymentSummary.add("Cheque: " + usedAmount + " (Valor del cheque: " + checkAmount + 
                (change > 0 ? ", Cambio: " + change : "") + ")");
        
        return usedAmount;
    }
    
    /*
    public double processCheckPayment(double amount, double remainingAmount) {
        if (amount < remainingAmount * 0.1) {
            paymentSummary.add("Cheque rechazado: Monto insuficiente");
            return 0;
        }
        double usedAmount = Math.min(amount, remainingAmount);
        paymentSummary.add("Cheque: " + usedAmount);
        return usedAmount;
    }
*/
    
    public double processPayPalPayment(double amount, double remainingAmount) {
        double usedAmount = Math.min(amount, remainingAmount);
        paymentSummary.add("PayPal: " + usedAmount);
        return usedAmount;
    }

    public String getSummary() {
        return String.join("\n", paymentSummary);
    }
}