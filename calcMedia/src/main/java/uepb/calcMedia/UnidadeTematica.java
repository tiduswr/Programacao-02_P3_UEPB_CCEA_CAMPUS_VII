package uepb.calcMedia;

public class UnidadeTematica {
    private double nota1;
    private double nota2;

    public UnidadeTematica(double n1, double n2){
        nota1 = n1;
        nota2 = n2;
    }
 
    public double getNota1() {
        return nota1;
    }
    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }
    public double getNota2() {
        return nota2;
    }
    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }
    public double getSoma(){
        return (double) Double.sum(this.getNota1(), this.getNota2());
    }
    public double getMedia(){
        return this.getSoma()/(double) 2;
    }  
}
