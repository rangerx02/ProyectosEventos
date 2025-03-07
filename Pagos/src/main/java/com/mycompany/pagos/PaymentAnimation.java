/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Gfmt
 */
// Clase de animaciones de pago

class PaymentAnimation {
    public static void showAnimation(Payment paymentMethod) {
        String message = "";
        String imagePath = "";

        if (paymentMethod instanceof CashPayment) {
            message = "Procesando pago en efectivo...";
            imagePath = "images/cash.png";  
        } else if (paymentMethod instanceof CreditCardPayment) {
            message = "Procesando pago con tarjeta de crédito...";
            imagePath = "images/credit_card.png";
        } else if (paymentMethod instanceof DebitCardPayment) {
            message = "Procesando pago con tarjeta de débito...";
            imagePath = "images/debit_card.png";
        } else if (paymentMethod instanceof MoneyCheckPayment) {
            message = "Procesando pago con cheque...";
            imagePath = "images/check.png";
        } else if (paymentMethod instanceof PayPalPayment) {
            message = "Procesando pago con PayPal...";
            imagePath = "images/paypal.png";
        }

        // Cargar la imagen
        ImageIcon icon = new ImageIcon(imagePath);

        // Mostrar mensaje con imagen
        JOptionPane.showMessageDialog(null, message, "Método de Pago", JOptionPane.INFORMATION_MESSAGE, icon);
    }
}





/*Funcional principal
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
*/