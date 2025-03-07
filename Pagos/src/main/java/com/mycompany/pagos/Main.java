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
        if (totalAmount == -1) return;
        
        double remainingAmount = totalAmount;
        PaymentProcessor processor = new PaymentProcessor();
        
        while (remainingAmount > 0) {
            String message = "Saldo pendiente: " + remainingAmount + "\nSeleccione un método de pago:";
            String[] options = {"Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito", "Cheque", "PayPal", "Cancelar"};
            int option = JOptionPane.showOptionDialog(null, message, "Método de Pago",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            
            if (option == 5 || option == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return;
            }
            
            switch (option) {
                case 0:
                    CashPayment cashPayment = new CashPayment();
                    double cashUsed = processor.processCashPayment(cashPayment, remainingAmount);
                    remainingAmount -= cashUsed;
                    JOptionPane.showMessageDialog(null, "Saldo actualizado: " + remainingAmount);
                    break;
                case 1:
                case 2:
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Desea pagar el saldo restante en su totalidad?", "Pago con tarjeta", JOptionPane.YES_NO_OPTION);
                    double cardAmount;
                    if (confirm == JOptionPane.YES_OPTION) {
                        cardAmount = remainingAmount;
                    } else {
                        cardAmount = obtenerMontoPago("Ingrese el monto a pagar con tarjeta:");
                        if (cardAmount == -1) continue;
                    }
                    remainingAmount -= processor.processCardPayment(cardAmount);
                    JOptionPane.showMessageDialog(null, "Saldo actualizado: " + remainingAmount);
                    break;
                case 3:
                    double checkAmount = obtenerMontoPago("Ingrese el monto a pagar con cheque:");
                    if (checkAmount == -1) continue;
                    remainingAmount -= processor.processCheckPayment(checkAmount, remainingAmount);
                    JOptionPane.showMessageDialog(null, "Saldo actualizado: " + remainingAmount);
                    break;
                case 4:
                    double paypalAmount = obtenerMontoPago("Ingrese el monto a pagar con PayPal:");
                    if (paypalAmount == -1) continue;
                    remainingAmount -= processor.processPayPalPayment(paypalAmount, remainingAmount);
                    JOptionPane.showMessageDialog(null, "Saldo actualizado: " + remainingAmount);
                    break;
            }
        }
        JOptionPane.showMessageDialog(null, "Pago completado.\nMonto inicial: " + totalAmount + "\nResumen:\n" + processor.getSummary());
    }

    private static double obtenerMontoInicial() {
        while (true) {
            String input = JOptionPane.showInputDialog("Ingrese el monto total a pagar:");
            if (input == null) return -1;
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
            if (input == null) return -1;
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0) return amount;
            } catch (NumberFormatException ignored) {}
            JOptionPane.showMessageDialog(null, "Monto inválido. Intente nuevamente.");
        }
    }
}
