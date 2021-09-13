package uepb.zologico;

public class Gato extends Felinos {
    private String raca;

    public Gato(){
        super();
    }

    public Gato(int metrosMaximoEscala, String familia, String genero, String especia, Localizacao localizacao) {
        super(metrosMaximoEscala,familia, genero, especia, localizacao);
    }

    public void setRaca(String raca){
        this.raca=raca;
    }
    
    public String getRaca(){
        return raca;
    }
}
