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
import javax.swing.JOptionPane;

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

