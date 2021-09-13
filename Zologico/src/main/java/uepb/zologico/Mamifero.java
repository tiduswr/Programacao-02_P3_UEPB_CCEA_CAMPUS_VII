package uepb.zologico;

public abstract class Mamifero extends Animal {

    private int quantidadeMamas;
    private boolean temPelos;
    protected String somEmitido;
    protected double mesesGestacao;

    public Mamifero(){
        super();
        super.setOrdem("Therapsida");
        super.setClasse("Mamiferos");
        super.setFilo("Chordata");
        super.setReino("Animalia");
    }

    public Mamifero(String familia, String genero, String especia, Localizacao localizacao){
        super();
        super.setOrdem("Therapsida");
        super.setClasse("Mamiferos");
        super.setFilo("Chordata");
        super.setReino("Animalia");
        super.setFamilia(familia);
        super.setGenero(genero);
        super.setLocalizacao(localizacao);
        super.setEspecie(especia);
    }


    public boolean temPelos() {
        return temPelos;
    }

    public void setTemPelos(boolean temPelos) {
        this.temPelos = temPelos;
    }
    
    public void setQuantidadeMamas(int quantidadeMamas){
        this.quantidadeMamas = quantidadeMamas;
    }

    public int getQuantidadeMamas(){
        return this.quantidadeMamas;
    }

    protected void setSomEmitido (){ 
        this.somEmitido = "";
    }

    public int getMesesGestacao(){
        return this.quantidadeMamas;
    }

    protected void setMesesGestacao(){
        this.quantidadeMamas = 0;
    }

    public String getSomEmitido(){
        return somEmitido;
    }

}
