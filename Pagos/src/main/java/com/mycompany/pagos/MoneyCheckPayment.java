/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;

import javax.swing.JOptionPane;
//Gean Franco Muñoz Toro - 2266833-2724
//Julián David Bedoya Arango - 2160535-2724

/**
 *
 * @author Gfmt
 */
//Clase de pago Como Cheque con dinero
class MoneyCheckPayment implements Payment {
    private double checkAmount;
    
    public MoneyCheckPayment() {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog("Ingrese el valor del cheque:");
                if (input == null) {
                    this.checkAmount = -1; // Indica cancelación
                    return;
                }
                this.checkAmount = Double.parseDouble(input);
                if (this.checkAmount > 0) {
                    return;
                }
                JOptionPane.showMessageDialog(null, "Valor del cheque inválido. Debe ser mayor que cero.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor del cheque inválido. Ingrese un número válido.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    @Override
    public String pay(double amount) {
        if (checkAmount <= 0) {
            return "Operación cancelada.";
        }
        
        if (amount < checkAmount * 0.1) {            
            return "ERROR: El monto de la compra debe ser al menos el 10% del valor del cheque.";            
        }
        
        double change = checkAmount - amount;
        return "Pago con cheque realizado. Monto: " + amount + ", Cheque: " + checkAmount + ", Cambio: " + change;
    }
    
    public double getCheckAmount() {
        return checkAmount;
    }
    
    public boolean isValid() {
        return checkAmount > 0;
    }
}

