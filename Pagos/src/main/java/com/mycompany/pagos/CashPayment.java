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

//Clase de pago en dinero efectivo
class CashPayment implements Payment {
    @Override
    public String pay(double amount) {
        double givenAmount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto entregado:"));
        double change = givenAmount - amount;
        return "Pago en efectivo realizado. Monto: " + amount + ", Entregado: " + givenAmount + ", Cambio: " + change;
    } 
}
