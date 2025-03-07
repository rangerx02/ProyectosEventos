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
// Selector de métodos de pago con posibilidad de mezclar varios
class PaymentSelector {
    public static List<Payment> selectPaymentMethods(double totalAmount) {
        List<Payment> selectedPayments = new ArrayList<>();
        double remainingAmount = totalAmount;
        boolean usedCard = false;

        while (remainingAmount > 0) {
            String[] options = {"Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito", "Cheque", "PayPal", "Finalizar"};
            int choice = JOptionPane.showOptionDialog(null, "Saldo pendiente: " + remainingAmount + "\nSeleccione un método de pago:", "Método de Pago", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            
            if (choice == 5 || choice == JOptionPane.CLOSED_OPTION) break; // Finalizar selección

            Payment payment = null;
            
            switch (choice) {
                case 0:
                    payment = new CashPayment();
                    break;
                case 1:
                case 2:
                    if (!usedCard) {
                        int cardChoice = JOptionPane.showConfirmDialog(null, "¿Desea pagar el saldo restante con tarjeta de crédito/débito?", "Confirmación", JOptionPane.YES_NO_OPTION);
                        if (cardChoice == JOptionPane.YES_OPTION) {
                            payment = (choice == 1) ? new CreditCardPayment() : new DebitCardPayment();
                            usedCard = true;
                        }
                    }
                    break;
                case 3:
                    payment = new MoneyCheckPayment();
                    break;
                case 4:
                    payment = new PayPalPayment();
                    break;
            }
            
            if (payment != null) {
                selectedPayments.add(payment);
            }
        }
        return selectedPayments;
    }
}