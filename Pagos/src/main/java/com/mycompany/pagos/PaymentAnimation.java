/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;

import javax.swing.JOptionPane;

/**
 *
 * @author Gfmt
 */
// Clase de animaciones de pago
class PaymentAnimation {
    public static void showAnimation(Payment paymentMethod) {
        if (paymentMethod instanceof CashPayment) {
            JOptionPane.showMessageDialog(null, "[Animación] Pago en efectivo.");
        } else if (paymentMethod instanceof CreditCardPayment) {
            JOptionPane.showMessageDialog(null, "[Animación] Pago con tarjeta de crédito.");
        } else if (paymentMethod instanceof DebitCardPayment) {
            JOptionPane.showMessageDialog(null, "[Animación] Pago con tarjeta de débito.");
        } else if (paymentMethod instanceof MoneyCheckPayment) {
            JOptionPane.showMessageDialog(null, "[Animación] Pago con cheque.");
        }
    }
}
