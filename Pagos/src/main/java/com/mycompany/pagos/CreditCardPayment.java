/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;

/**
 *
 * @author UNIVALLE
 */
// Implementamos la interfaz en la clase CreditCardPayment
//Clase de pago con Tarjeta Credito
class CreditCardPayment implements Payment {
    @Override
    public String pay(double amount) {        
        return "Pagado " + amount + " usando tarjeta de crédito.";
    }
}


