package uepb.educad;

import java.util.ArrayList;

public class Universidade {
    private String nome;
    private String campus;
    private Endereco endereco;
    private Contato contato;
    private ArrayList<Estudante> estudantes;

    public Universidade(){
        this.nome = "";
        this.campus = "";
        estudantes = new ArrayList();
    }

    //Metodos getters and Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCampus() {
        return campus;
    }
    public void setCampus(String campus) {
        this.campus = campus;
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
    //Métodos principais
    public ArrayList<Estudante> getEstudantes(){
        return this.estudantes;
    }
    public void addEstudante(Estudante estudante){
        this.estudantes.add(estudante);
    }
    public Estudante getEstudante(String nomeEstudante){
        if(!nomeEstudante.equals("")){
            for(int i = 0; i < (int) this.estudantes.size(); i++){
                if(this.estudantes.get(i).getNome().equalsIgnoreCase(nomeEstudante)){
                    return this.estudantes.get(i);
                }
            }
        }
        return (Estudante) null;
    }
    public boolean removeEstudante(String nomeEstudante){
        if(!nomeEstudante.equals("")){
            for(int i = 0; i < (int) this.estudantes.size(); i++){
                if(this.estudantes.get(i).getNome().equalsIgnoreCase(nomeEstudante)){
                    this.estudantes.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    public int lenghtEstudantes(){
        return (int) this.estudantes.size();
    }
}
