package uepb.zologico;

public abstract class Felinos extends Mamifero{
    private int metrosMaximoEscala;
    
    public Felinos(){
        super();
    }
    
    public Felinos(int metrosMaximoEscala, String familia, String genero, String especia, Localizacao localizacao) {
        super(familia, genero, especia, localizacao);
        this.metrosMaximoEscala = metrosMaximoEscala;
    }

    public int getMetrosMaximoEscala() {
        return metrosMaximoEscala;
    }

    public void setMetrosMaximoEscala(int metrosMaximoEscala) {
        this.metrosMaximoEscala = metrosMaximoEscala;
    }
    
    @Override
    public void setSomEmitido (){ 
        super.somEmitido = "Mia";
    }
    
    @Override
    public void setMesesGestacao(){
        super.mesesGestacao = 3;
    }
}
