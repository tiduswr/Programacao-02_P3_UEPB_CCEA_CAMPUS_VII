package uepb.zologico;

public class Galinha extends Aves{
    private String nome;
    private float peso;
    private int idadeMeses;
    
    public Galinha(){
        super();
        super.setFamilia("Phasianidae");
        super.setVoa(false);
    }

    public Galinha(String nome, float peso, int idadeMeses, String genero, String plumagem, 
                            String especie, Localizacao localizacao) {
        super("Phasianidae", genero, especie, localizacao);
        super.setVoa(false);
        super.setPlumagem(nome);
        
        this.nome = nome;
        this.idadeMeses = idadeMeses;
        this.peso = peso;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getIdadeMeses() {
        return idadeMeses;
    }

    public void setIdadeMeses(int idadeMeses) {
        this.idadeMeses = idadeMeses;
    }
}
