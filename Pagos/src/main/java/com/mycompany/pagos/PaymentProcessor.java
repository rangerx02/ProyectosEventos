/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;

/**
 *
 * @author UNIVALLE
 */
public class PaymentProcessor {
    private Payment paymentMethod;

    // Método para seleccionar el tipo de pago
    public void setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Método para realizar el pago
    public void processPayment(double amount) {
        if (paymentMethod != null) {
            paymentMethod.pay(amount);
        } else {
            System.out.println("Método de pago no seleccionado.");
        }
    }
}


