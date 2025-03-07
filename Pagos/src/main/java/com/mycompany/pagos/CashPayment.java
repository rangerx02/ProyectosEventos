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
    @Override
    public String pay(double amount) {
        double givenAmount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad entregada: "));
        if (givenAmount < amount) {
            return "Monto insuficiente. Falta: " + (amount - givenAmount);
        }
        double change = givenAmount - amount;
        return "Pago exitoso. Entregado: " + givenAmount + ", Cambio: " + change + ", Monto inicial: " + amount;
    }
}

