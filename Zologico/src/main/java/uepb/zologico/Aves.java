package uepb.zologico;

public abstract class Aves extends Animal{
    protected boolean voa;
    private String plumagem;

    public Aves(){
        super();
        super.setOrdem("Passeriformes");
        super.setClasse("Aves");
        super.setFilo("Chordata");
        super.setReino("Animalia");
    }

    public Aves(String familia, String genero, String especia, Localizacao localizacao){
        super();
        super.setOrdem("Passeriformes");
        super.setClasse("Aves");
        super.setFilo("Chordata");
        super.setReino("Animalia");
        super.setFamilia(familia);
        super.setGenero(genero);
        super.setLocalizacao(localizacao);
        super.setEspecie(especia);
    }

    public boolean isVoa() {
        return voa;
    }

    protected void setVoa(boolean voa) {
        this.voa = voa;
    }

    public String getPlumagem() {
        return plumagem;
    }

    public void setPlumagem(String plumagem) {
        this.plumagem = plumagem;
    }
    
}
