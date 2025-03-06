/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;

/**
 *
 * @author Gfmt
 */
public class MixedPayment implements Payment {
    @Override
    public String pay(double amount) {
        return "Pago realizado utilizando múltiples métodos de pago.";
    }
}
