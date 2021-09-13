package uepb.clinica;

public class IMC {
    float peso, altura, imc;
    
    public IMC(){
    }
    public IMC(float peso, float altura){
        this.peso = peso;
        this.altura = altura;
        this.imc = peso / (altura*altura);
    }

    public float getPeso() {
        return peso;
    }
    public void setPeso(float peso) {
        this.peso = peso;
    }
    public float getAltura() {
        return altura;
    }
    public void setAltura(float altura) {
        this.altura = altura;
    }
    public float getImc() {
        return imc;
    } 
    
    public void recalcularIMC(){
        this.imc = this.getPeso() / (this.getAltura()*this.getAltura());
    }
    public String getFaixaIMC(){
        float arg = this.getImc();
        
        if(arg < 18.5f){
            return "Abaixo do peso.";
        }else if(arg >= 18.5f && arg < 25f){
            return "Peso saudável.";
        }else if(arg >= 25 && arg < 30f){
            return "Sobrepeso.";
        }else if(arg >= 30 && arg < 40){
            return "Obeso.";
        }else{
            return "Obesidade mórbida.";
        }
        
    }
}
