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
//Clase de pago Como Cheque con dinero
class MoneyCheckPayment implements Payment {
    @Override
    public String pay(double amount) {
        double checkAmount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor del cheque:"));
        if (amount < checkAmount * 0.1) {            
            return "El monto de la compra debe ser al menos el 10% del valor del cheque.";            
        }
        double change = checkAmount - amount;
        return "Pago con cheque realizado. Monto: " + amount + ", Cheque: " + checkAmount + ", Cambio: " + change;
        //return "Pagado " + amount + " usando un Cheque.";
    }    
}

