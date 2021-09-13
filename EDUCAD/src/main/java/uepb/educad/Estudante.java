package uepb.educad;

public class Estudante {
    private String nome;
    private int sexo;
    private int idade;
    private String matricula;
    private Endereco endereco;
    private Contato contato;
    private Disciplina disciplina;
    
    public Estudante(){
        this.nome = "";
        this.sexo = 0;
        this.idade = 0;
        this.matricula = "";
    }
    
    //Metodos Getters and Setters

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSexo() {
        if(this.sexo == 0){
            return "Masculino";
        }else if(this.sexo == 1){
            return "Feminino";
        }
        return "?";
    }
    public void setSexo(int sexo) {
        this.sexo = sexo;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public Contato getContato() {
        return contato;
    }
    public void setContato(Contato contato) {
        this.contato = contato;
    }
    public Disciplina getDisciplina() {
        return disciplina;
    }
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    
}
