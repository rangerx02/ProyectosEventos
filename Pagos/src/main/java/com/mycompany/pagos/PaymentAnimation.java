/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.net.URL;
/**
 *
 * @author Gfmt
 */
// Clase de animaciones de pago
class PaymentAnimation {
    public static void showAnimation(Payment paymentMethod) {
        String imagePath = "";

        if (paymentMethod instanceof CashPayment) {
            imagePath = "/images/cash.png";
        } else if (paymentMethod instanceof CreditCardPayment) {
            imagePath = "/images/credit_card.png";
        } else if (paymentMethod instanceof DebitCardPayment) {
            imagePath = "/images/debit_card.png";
        } else if (paymentMethod instanceof MoneyCheckPayment) {
            imagePath = "/images/check.png";
        } else if (paymentMethod instanceof PayPalPayment){
            imagePath = "/images/paypal.png";
        }
                

        // Cargar la imagen desde el classpath
        URL imageUrl = PaymentAnimation.class.getResource(imagePath);

        if (imageUrl == null) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo cargar la imagen: " + imagePath);
            return;
        }

        ImageIcon icon = new ImageIcon(imageUrl);
        JOptionPane.showMessageDialog(null, "Pago procesado correctamente.", "Método de Pago", JOptionPane.INFORMATION_MESSAGE, icon);
    }
}
