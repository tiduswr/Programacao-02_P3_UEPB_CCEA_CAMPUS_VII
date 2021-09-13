package uepb.calcMedia;

public class Estudante {
    private String nome;
    private int idade;
    private char sexo;
    private Contato contato;
    private Diciplina diciplina;
    private Endereco endereco;

    public Estudante(String nome, int idade, char sexo, Contato contato, 
                        Diciplina diciplina, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.contato = contato;
        this.diciplina = diciplina;
        this.endereco = endereco;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public char getSexo() {
        return sexo;
    }
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    public Contato getContato() {
        return contato;
    }
    public void setContato(Contato contato) {
        this.contato = contato;
    }
    public Diciplina getDiciplina() {
        return diciplina;
    }
    public void setDiciplina(Diciplina diciplina) {
        this.diciplina = diciplina;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public double getMediaFinal(){
        return Double.sum(this.getDiciplina().getUt1().getSoma(), 
                                    (double) this.getDiciplina().getUt2().getSoma())/(double) 4;
    }
}
