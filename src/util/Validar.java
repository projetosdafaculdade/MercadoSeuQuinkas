package util;

public class Validar {

    public static boolean validarString(String string) {
        if (string.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validarCEP(String CEP) {
        if (CEP.length() == 9) {
            if(CEP.replaceAll("[^0-9.]", "").length() == 8){
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }

}
