/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;

/**
 *
 * @author UNIVALLE
 */
import javax.swing.JOptionPane;

public class PaymentSelector {
    
    // Método para seleccionar el tipo de pago
    public static Payment selectPaymentMethod() {
        // Opciones de pago
        String[] options = {"Tarjeta de crédito","Tarjeta de debito", "PayPal", "Efectivo", "Cheque" };
        
        
        // Mostrar cuadro de selección
        int option = JOptionPane.showOptionDialog(
            null, 
            "Selecciona un método de pago:", 
            "Método de Pago", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, 
            options, 
            options[0]
        );
        
        // Retornar el método de pago seleccionado
        if (option == 0) {
            return new CreditCardPayment();
        } else if (option == 1) {
            return new DebitCardPayment();
        } else if (option == 2){
            return new PayPalPayment();
        } else if (option == 3){
            return new CashPayment(); 
        } else if (option == 4){
            return new MoneyCheckPayment();
        } else {
            JOptionPane.showMessageDialog(null, "Opción no válida. Se utilizará el pago con tarjeta de crédito por defecto.");
            return new CreditCardPayment();
        }
    }
}

/*
// Selector de método de pago
class PaymentSelector {
    public static Payment selectPaymentMethod() {
        String[] options = {"Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito", "Cheque"};
        int choice = JOptionPane.showOptionDialog(null, "Seleccione el método de pago:", "Método de Pago", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        
        switch (choice) {
            case 0: return new CashPayment();
            case 1: return new CreditCardPayment();
            case 2: return new DebitCardPayment();
            case 3: return new CheckPayment();
            default: return new CashPayment();
        }
    }
}
*/