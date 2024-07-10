package Controllers;

import java.util.Arrays;

/**
 *
 * @author Joao
 */
public class Validators {
    public static boolean campoCadenaVacia(String cadena) {
        if (!cadena.equals("")) {
            return true;
        } 
        else if (cadena == null) {
            return false;
        } else {
            System.out.println("Cadena Vacia");
            return false;
        }
    }
    
    public static boolean campoNumericoVacio(Double numero) {
        if (numero <= 0) {
            System.out.println("Valor numerico no permitido");
            return false;
        }
        else {
            return true;
        }
    }
    
    public static void campoCorreoElectronico (String email) {
        String[] cadena = email.split( "@");
        for(String i : cadena) {
            System.out.println(i);
        }
    }
    
    public static boolean campoNumeroTelefono (String telefono) {
        if (telefono.length() == 9) {
            return true;
        } else {
            return false;
        }
    }

    
}
