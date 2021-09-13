package uepb.zologico;

public class Cachorro extends Caninos
{
    private String raca;

    public Cachorro(){
        super();
    }

    public Cachorro(String capacidadeFarejar, String familia, String genero, String especia, Localizacao localizacao) {
        super(capacidadeFarejar, familia, genero, especia, localizacao);
    }

    public void setRaca(String raca){
        this.raca=raca;
    }

    public String getRaca(){
        return raca;
    }
}
