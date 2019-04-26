package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Conversor {
    

    public static String dataBancoParaUsuario(java.sql.Date data){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(data);
    }
    
    public static java.sql.Date dataUtilParaSql(java.util.Date data){
        return new java.sql.Date(data.getTime());
    }
    
    public static java.sql.Date dataUsuarioParaBanco(String data){
        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dataUtil = formatador.parse(data);
            return new java.sql.Date(dataUtil.getTime());
        } catch (ParseException ex) {
            System.out.println("Erro ao converter data");
            return null;
        }
        
    }
    
    public static String dataAtual(){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dataUtil = new java.util.Date();
        return formatador.format(dataUtil);
    }
    
}
