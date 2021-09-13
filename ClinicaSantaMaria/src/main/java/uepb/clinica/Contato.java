package uepb.clinica;

public class Contato {
   private String email;
   private String celular;
      
   public Contato(){
       
   }
   
   public Contato(String email, String celular){
       this.email = email;
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
    public String getCelular() {
        if(this.celular != null){
            if(!this.celular.isEmpty() && !this.celular.isBlank()){
                if(this.celular.length() == 11){
                    String aux = this.celular;
                    String format;

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
    public void setCelular(String celular) {
        this.celular = celular;
    }
   
}
