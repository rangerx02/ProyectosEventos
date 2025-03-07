/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;
import java.util.List;
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
            int option = JOptionPane.showOptionDialog(null, "Seleccione un método de pago:", "Método de Pago",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            
            if (option == 5 || option == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return;
            }
            
            double amount = 0;
            if (option == 1 || option == 2) { // Tarjeta de Crédito o Débito
                int confirm = JOptionPane.showConfirmDialog(null, "¿Desea pagar el saldo restante en su totalidad?", "Pago con tarjeta", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    amount = remainingAmount;
                } else {
                    amount = obtenerMontoPago();
                    if (amount == -1) continue; // Si cancela, volver al menú
                }
            } else {
                amount = obtenerMontoPago();
                if (amount == -1) continue; // Si cancela, volver al menú
            }
            
            switch (option) {
                case 0:
                    remainingAmount -= processor.processCashPayment(amount, remainingAmount);
                    break;
                case 1:
                case 2:
                    remainingAmount -= processor.processCardPayment(amount);
                    break;
                case 3:
                    remainingAmount -= processor.processCheckPayment(amount, remainingAmount);
                    break;
                case 4:
                    remainingAmount -= processor.processPayPalPayment(amount, remainingAmount);
                    break;
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
    
    private static double obtenerMontoPago() {
        while (true) {
            String input = JOptionPane.showInputDialog("Ingrese el monto a pagar:");
            if (input == null) return -1; // Cancelar
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0) return amount;
            } catch (NumberFormatException ignored) {}
            JOptionPane.showMessageDialog(null, "Monto inválido. Intente nuevamente.");
        }
    }
}









/*Este si funciona
public class Main {
    public static void main(String[] args) {
        double totalAmount = obtenerMontoInicial();
        if (totalAmount == -1) return; // Si el usuario cancela, salir
        
        double remainingAmount = totalAmount;
        PaymentProcessor processor = new PaymentProcessor();

        while (remainingAmount > 0) {
            String[] options = {"Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito", "Cheque", "PayPal", "Cancelar"};
            int option = JOptionPane.showOptionDialog(null, "Seleccione un método de pago:", "Método de Pago",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            
            if (option == 5 || option == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return;
            }
            
            double amount = obtenerMontoPago();
            if (amount == -1) continue; // Si cancela, volver al menú

            switch (option) {
                case 0:
                    remainingAmount -= processor.processCashPayment(amount, remainingAmount);
                    break;
                case 1:
                case 2:
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Desea pagar el saldo restante en su totalidad?", "Pago con tarjeta", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        remainingAmount -= processor.processCardPayment(remainingAmount);
                    } else {
                        remainingAmount -= processor.processCardPayment(amount);
                    }
                    break;
                case 3:
                    remainingAmount -= processor.processCheckPayment(amount, remainingAmount);
                    break;
                case 4:
                    remainingAmount -= processor.processPayPalPayment(amount, remainingAmount);
                    break;
            }
        }
        JOptionPane.showMessageDialog(null, "Pago completado. Resumen:\n" + processor.getSummary());
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
    
    private static double obtenerMontoPago() {
        while (true) {
            String input = JOptionPane.showInputDialog("Ingrese el monto a pagar:");
            if (input == null) return -1; // Cancelar
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0) return amount;
            } catch (NumberFormatException ignored) {}
            JOptionPane.showMessageDialog(null, "Monto inválido. Intente nuevamente.");
        }
    }
}
*/





/*
public class Main {
    public static void main(String[] args) {
        // Pedir al usuario que ingrese el monto total a pagar
        String amountString = JOptionPane.showInputDialog("Ingrese el monto total a pagar:");
        double amount = 0;

        try {
            amount = Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Monto no válido. Se utilizará 0 como monto por defecto.");
        }

        // Procesar pago con múltiples métodos si es necesario
        List<Payment> payments = PaymentSelector.selectPaymentMethods(amount);
        double remainingAmount = amount;
        StringBuilder paymentSummary = new StringBuilder();
        
        for (Payment payment : payments) {
            String result = payment.pay(remainingAmount);
            paymentSummary.append(result).append("\n");
            
            // Mostrar la animación correspondiente
            PaymentAnimation.showAnimation(payment);
        }

        // Mostrar el resultado del pago en un cuadro de mensaje
        JOptionPane.showMessageDialog(null, paymentSummary.toString());
    }
}
*/

/*
public class Main {
    public static void main(String[] args) {
        // Seleccionar el método de pago
        Payment paymentMethod = PaymentSelector.selectPaymentMethod();

        // Pedir al usuario que ingrese el monto mediante un cuadro de texto
        String amountString = JOptionPane.showInputDialog("Ingrese el monto a pagar:");
        double amount = 0;

        // Verificar que la entrada sea un número válido
        try {
            amount = Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Monto no válido. Se utilizará 0 como monto por defecto.");
        }

        // Procesar el pago con el método seleccionado
        String result = paymentMethod.pay(amount);
        
        // Mostrar la animación correspondiente
        PaymentAnimation.showAnimation(paymentMethod);

        // Mostrar el resultado del pago en un cuadro de mensaje
        JOptionPane.showMessageDialog(null, result);
    }
}

*/

