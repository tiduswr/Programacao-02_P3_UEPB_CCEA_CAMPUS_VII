package uepb.zologico;


public abstract class Caninos extends Mamifero {
   
    private String capacidadeFarejar;
    
    public Caninos(){
        super();
    }

    public Caninos(String capacidadeFarejar, String familia, String genero, String especia, Localizacao localizacao) {
        super(familia, genero, especia, localizacao);
        this.capacidadeFarejar = capacidadeFarejar;
    }

    public String getCapacidadeFarejar() {
        return capacidadeFarejar;
    }

    public void setCapacidadeFarejar(String capacidadeFarejar) {
        this.capacidadeFarejar = capacidadeFarejar;
    }
    
    @Override
    public void setSomEmitido (){ 
        super.somEmitido = "Late";
    }
    
    @Override
    public void setMesesGestacao(){
        super.mesesGestacao = 6;
    }
}
