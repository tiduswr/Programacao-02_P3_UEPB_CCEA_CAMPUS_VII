package uepb.zologico;

import java.io.Serializable;

public abstract class Animal implements Serializable{
    private String reino;
    private String filo;
    private String classe;
    private String ordem;
    private String familia;
    private String genero;
    private String especie;
    private Localizacao localizacao;   

    public Animal(){}

    public Animal(String reino, String filo, String classe, String ordem, String familia, 
            String genero, String especie, Localizacao localizacao) {
        this.reino = reino;
        this.filo = filo;
        this.classe = classe;
        this.ordem = ordem;
        this.familia = familia;
        this.genero = genero;
        this.especie = especie;
        this.localizacao = localizacao;
    }

    public String getReino() {
        return reino;
    }

    protected void setReino(String reino) {
        this.reino = reino;
    }

    public String getFilo() {
        return filo;
    }

    protected void setFilo(String filo) {
        this.filo = filo;
    }

    public String getClasse() {
        return classe;
    }

    protected void setClasse(String classe) {
        this.classe = classe;
    }

    public String getOrdem() {
        return ordem;
    }

    protected void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getFamilia() {
        return familia;
    }

    protected void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
    
}
