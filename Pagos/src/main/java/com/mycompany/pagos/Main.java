/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;
import javax.swing.JOptionPane;

/**
 *
 * @author UNIVALLE
 */
public class Main {
    public static void main(String[] args) {
        double totalAmount = obtenerMontoInicial();
        if (totalAmount == -1) return; // Si el usuario cancela, salir
        
        double remainingAmount = totalAmount;
        PaymentProcessor processor = new PaymentProcessor();

        while (remainingAmount > 0) {
            String[] options = {"Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito", "Cheque", "PayPal", "Cancelar"};
            int option = JOptionPane.showOptionDialog(null, "Saldo pendiente: " + remainingAmount + "\nSeleccione un método de pago:", "Método de Pago",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            
            if (option == 5 || option == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return;
            }
            
            double amount = 0;
            if (option == 0) { // Efectivo
                amount = obtenerMontoPago("Ingrese el monto entregado:");
                if (amount == -1) continue;
                
                PaymentAnimation.showAnimation(new CashPayment());
                double change = amount - remainingAmount;
                remainingAmount -= processor.processCashPayment(amount, remainingAmount);
                
                if (change > 0) {
                    JOptionPane.showMessageDialog(null, "Cambio a devolver: " + change);
                }
            } else if (option == 1 || option == 2) { // Tarjeta de Crédito o Débito
                int confirm = JOptionPane.showConfirmDialog(null, "¿Desea pagar el saldo restante en su totalidad?", "Pago con tarjeta", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    amount = remainingAmount;
                } else {
                    amount = obtenerMontoPago("Ingrese el monto a pagar con tarjeta:");
                    if (amount == -1) continue;
                }
                
                PaymentAnimation.showAnimation(option == 1 ? new CreditCardPayment() : new DebitCardPayment());
                remainingAmount -= processor.processCardPayment(amount);
            } else {
                amount = obtenerMontoPago("Ingrese el monto a pagar:");
                if (amount == -1) continue;
                
                switch (option) {
                    case 3:
                        PaymentAnimation.showAnimation(new MoneyCheckPayment());
                        remainingAmount -= processor.processCheckPayment(amount, remainingAmount);
                        break;
                    case 4:
                        PaymentAnimation.showAnimation(new PayPalPayment());
                        remainingAmount -= processor.processPayPalPayment(amount, remainingAmount);
                        break;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Pago completado.\nMonto inicial: " + totalAmount + "\nResumen:\n" + processor.getSummary());
    }

    private static double obtenerMontoInicial() {
        while (true) {
            String input = JOptionPane.showInputDialog("Ingrese el monto total a pagar:");
            if (input == null) return -1; // Cancelar
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0) return amount;
            } catch (NumberFormatException ignored) {}
            JOptionPane.showMessageDialog(null, "Monto inválido. Intente nuevamente.");
        }
    }
    
    private static double obtenerMontoPago(String mensaje) {
        while (true) {
            String input = JOptionPane.showInputDialog(mensaje);
            if (input == null) return -1; // Cancelar
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0) return amount;
            } catch (NumberFormatException ignored) {}
            JOptionPane.showMessageDialog(null, "Monto inválido. Intente nuevamente.");
        }
    }
}