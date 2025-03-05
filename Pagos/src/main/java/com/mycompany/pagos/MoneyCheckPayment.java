/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;

/**
 *
 * @author Gfmt
 */
//Clase de pago Como Cheque con dinero
class MoneyCheckPayment implements Payment {
    @Override
    public String pay(double amount) {
        return "Pagado " + amount + " usando un Cheque.";
    }    
}

