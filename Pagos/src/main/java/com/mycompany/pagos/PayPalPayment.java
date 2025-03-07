/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;
//Gean Franco Muñoz Toro - 2266833-2724
//Julián David Bedoya Arango - 2160535-2724

/**
 *
 * @author UNIVALLE
 */
// Implementamos la interfaz en la clase PayPalPayment
class PayPalPayment implements Payment {
    @Override
    public String pay(double amount) {
        
        return "Pagado " + amount + " usando PayPal.";
    }
}

