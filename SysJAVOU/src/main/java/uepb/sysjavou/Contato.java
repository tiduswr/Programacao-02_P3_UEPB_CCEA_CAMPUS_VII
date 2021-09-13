package uepb.sysjavou;

public class Contato {
   private String email;
   private String telefone;
   private String celular;
   private String redeSocial;
      
   public Contato(){
       
   }
   
   public Contato(String email, String telefone, String celular, String redeSocial){
       this.email = email;
       this.telefone = telefone;
       this.redeSocial = redeSocial;
       this.celular = celular;
   }
   
   //Métodos getters and setters
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCelularSemFormatacao(){
        return this.celular;
    }
    public String getTelefoneSemFormatacao(){
        return this.telefone;
    }
    public String getCelular() {
        if(this.celular != null){
            if(!this.celular.isEmpty() && !this.celular.isBlank()){
                if(this.celular.length() == 11){
                    String aux = this.celular;
                    String format = "";

                    format = "(" + aux.substring(0, 2) + ") ";
                    format = format + aux.substring(2,3) + " ";
                    format = format + aux.substring(3, 7) +"-";
                    format = format + aux.substring(7, 11);
                    return format;
                }
                return celular;
            }
        }
        return null;
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
    public String getTelefone() {
        if(this.telefone != null){
            if(!this.telefone.isEmpty() && !this.telefone.isBlank()){
                if(this.telefone.length() == 10){
                    String aux = this.telefone;
                    String format = "";

                    format = "(" + aux.substring(0, 2) + ") ";
                    format = format + aux.substring(2, 6) +"-";
                    format = format + aux.substring(6, 10);
                    return format;
                }
                return telefone;
            }
        }
        return null;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
   
}
