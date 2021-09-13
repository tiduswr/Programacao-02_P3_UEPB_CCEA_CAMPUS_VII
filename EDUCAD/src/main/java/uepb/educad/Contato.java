package uepb.educad;

public class Contato {
   private String email;
   private String telefone;
   private String redeSocial;
   
   public Contato(){
       this.email = "";
       this.telefone = "";
       this.redeSocial = "";
   }
   
   //Métodos getters and setters
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        if(this.telefone.length() == 11){
            String aux = this.telefone;
            String format = "";

            format = "(" + aux.substring(0, 2) + ") ";
            format = format + aux.substring(2,3) + " ";
            format = format + aux.substring(3, 7) +"-";
            format = format + aux.substring(7, 11);
            return format;
        }
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getRedeSocial() {
        return redeSocial;
    }
    public void setRedeSocial(String redeSocial) {
        this.redeSocial = redeSocial;
    }
   
}
