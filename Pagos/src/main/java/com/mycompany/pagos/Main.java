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
            String message = "Saldo pendiente: " + remainingAmount + "\nSeleccione un método de pago:";
            String[] options = {"Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito", "Cheque", "PayPal", "Cancelar"};
            int option = JOptionPane.showOptionDialog(null, message, "Método de Pago",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            
            if (option == 5 || option == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return;
            }
            
            switch (option) {                             
                case 0: // Efectivo
                CashPayment cashPayment = new CashPayment();
                double cashUsed = processor.processCashPayment(cashPayment, remainingAmount);
                double change = cashPayment.getGivenAmount() - cashUsed;
                
                if (cashUsed > 0) {
                    remainingAmount -= cashUsed;
                    if (change > 0) {
                        JOptionPane.showMessageDialog(null, 
                                
                                "Pago aplicado: " + cashUsed + 
                                "\nCantidad entregada: " + cashPayment.getGivenAmount() + 
                                "\nCambio a devolver: " + change);
                    } else if (cashUsed < remainingAmount) {
                        JOptionPane.showMessageDialog(null, 
                                "Pago parcial aplicado: " + cashUsed +
                                "\nSaldo restante: " + (remainingAmount + cashUsed - cashUsed));
                    } else {
                        JOptionPane.showMessageDialog(null, 
                                "Pago completo aplicado: " + cashUsed);
                    }
                }
                PaymentAnimation.showAnimation(cashPayment);
                break;
                case 1:
                    CreditCardPayment creditCardPayment = new CreditCardPayment();
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Desea pagar el saldo restante en su totalidad?", "Pago con tarjeta", JOptionPane.YES_NO_OPTION);
                    double cardAmount;
                    if (confirm == JOptionPane.YES_OPTION) {
                        cardAmount = remainingAmount;
                    } else {
                        cardAmount = obtenerMontoPago("Ingrese el monto a pagar con tarjeta:");
                        if (cardAmount == -1) continue;
                    }
                    remainingAmount -= processor.processCardPayment(cardAmount, "Tarjeta de Crédito");            
                    PaymentAnimation.showAnimation(creditCardPayment);
                    break;
                case 2:
                    DebitCardPayment debitCardPayment = new DebitCardPayment();
                    confirm = JOptionPane.showConfirmDialog(null, "¿Desea pagar el saldo restante en su totalidad?", "Pago con tarjeta", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        cardAmount = remainingAmount;
                    } else {
                        cardAmount = obtenerMontoPago("Ingrese el monto a pagar con tarjeta:");
                        if (cardAmount == -1) continue;
                    }
                    remainingAmount -= processor.processCardPayment(cardAmount, "Tarjeta de Débito");                    
                    PaymentAnimation.showAnimation(debitCardPayment);
                    break;
                case 3:
                    MoneyCheckPayment checkPayment = new MoneyCheckPayment();
                    //Si el usuario canceló la operación, continuar con el siguiente ciclo
                    if (!checkPayment.isValid()) {
                        continue;
                    }
                    //Procesar el pago con cheque
                    double processedAmount = processor.processCheckPayment(checkPayment, remainingAmount);
                    
                    if (processedAmount > 0) {
                        //Si el pago fue exitoso, restar del monto pendiente y mostrar animación
                        remainingAmount -= processedAmount;
                        PaymentAnimation.showAnimation(checkPayment);
                    }
                    break;
                    
                    /*
                    MoneyCheckPayment checkPayment = new MoneyCheckPayment();
                    double checkAmount = obtenerMontoPago("Ingrese el monto a pagar con cheque:");
                    if (checkAmount == -1) continue;
                    remainingAmount -= processor.processCheckPayment(checkAmount, remainingAmount);
                    PaymentAnimation.showAnimation(checkPayment);
                    break;
                    */
                case 4:
                    PayPalPayment paypalPayment = new PayPalPayment();
                    double paypalAmount = obtenerMontoPago("Ingrese el monto a pagar con PayPal:");
                    if (paypalAmount == -1) continue;
                    remainingAmount -= processor.processPayPalPayment(paypalAmount, remainingAmount);
                    PaymentAnimation.showAnimation(paypalPayment);
                    break;
            }
            JOptionPane.showMessageDialog(null, "Saldo actualizado: " + remainingAmount);
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

