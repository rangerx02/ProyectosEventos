/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pagos;

/**
 *
 * @author UNIVALLE
 */
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


// Selector de métodos de pago con posibilidad de mezclar varios
class PaymentSelector {
    public static List<Payment> selectPaymentMethods(double totalAmount) {
        List<Payment> selectedPayments = new ArrayList<>();
        double remainingAmount = totalAmount;
        boolean usedCard = false;

        while (remainingAmount > 0) {
            String[] options = {"Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito", "Cheque", "Paypal", "Finalizar"};
            int choice = JOptionPane.showOptionDialog(null, "Seleccione un método de pago:", "Método de Pago", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            
            if (choice == 4) break; // Finalizar selección

            Payment payment = null;
            
            switch (choice) {
                case 0: payment = new CashPayment(); break;
                case 1: 
                case 2: 
                    if (!usedCard) {
                        int cardChoice = JOptionPane.showConfirmDialog(null, "¿Desea pagar el saldo restante con tarjeta de crédito/débito?", "Confirmación", JOptionPane.YES_NO_OPTION);
                        if (cardChoice == JOptionPane.YES_OPTION) {
                            payment = (choice == 1) ? new CreditCardPayment() : new DebitCardPayment();
                            usedCard = true;
                        }
                    }
                    break;
                case 3: payment = new MoneyCheckPayment(); break;
            }

            if (payment != null) {
                selectedPayments.add(payment);
            }
        }
        return selectedPayments;
    }
}



/*
// Selector de método de pago con posibilidad de mezcla
class PaymentSelector {
    public static Payment selectPaymentMethod() {
        String[] options = {"Efectivo", "Tarjeta de Crédito", "Tarjeta de Débito", "Cheque", "Mezcla"};
        int choice = JOptionPane.showOptionDialog(null, "Seleccione el método de pago:", "Método de Pago", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        
        switch (choice) {
            case 0: return new CashPayment();
            case 1: return new CreditCardPayment();
            case 2: return new DebitCardPayment();
            case 3: return new MoneyCheckPayment();
            case 4: return new MixedPayment();
            default: return new CashPayment();
        }
    }
}
*/

/*
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

*/