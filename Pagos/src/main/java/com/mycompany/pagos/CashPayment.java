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
// Clase de pago en dinero efectivo
class CashPayment implements Payment {
    private double givenAmount;

    public CashPayment() {
        this.givenAmount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad entregada: "));
    }

    @Override
    public String pay(double amount) {
        return "Pago en efectivo registrado: " + givenAmount;
    }

    public double getGivenAmount() {
        return givenAmount;
    }
}