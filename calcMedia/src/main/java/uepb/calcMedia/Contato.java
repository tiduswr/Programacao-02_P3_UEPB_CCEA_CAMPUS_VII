package uepb.calcMedia;

public class Contato {
    private String numero;
    private String email;
    
    public Contato(String sNum, String sEmail){
        numero = sNum;
        email = sEmail;
    }
    
    public String getNumero() {
        return numero;
    }
    public String getNumeroFormatado(){
        String aux = this.getNumero();
        String format = "";
        
        format = "(" + aux.substring(0, 2) + ") ";
        format = format + aux.substring(2,3) + " ";
        format = format + aux.substring(3, 7) +"-";
        format = format + aux.substring(7, 11);
        return format;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
