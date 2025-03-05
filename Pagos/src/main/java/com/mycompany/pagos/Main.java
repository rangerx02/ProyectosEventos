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
import javax.swing.JOptionPane;

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

        // Mostrar el resultado del pago en un cuadro de mensaje
        JOptionPane.showMessageDialog(null, result);
    }
}



