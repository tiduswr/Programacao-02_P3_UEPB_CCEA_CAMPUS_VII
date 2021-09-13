package uepb.calcMedia;

public class Diciplina {
    private String nome;
    private String professor;
    private String sala;
    private String turno;
    private UnidadeTematica ut1;
    private UnidadeTematica ut2;
    
    public Diciplina(String sNome, String sProfessor, String sSala, String sTurno, 
                        UnidadeTematica sUt1, UnidadeTematica sUt2){
        this.nome = sNome;
        this.professor = sProfessor;
        this.sala = sSala;
        this.turno = sTurno;
        this.ut1 = sUt1;
        this.ut2 = sUt2;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getProfessor() {
        return professor;
    }
    public void setProfessor(String professor) {
        this.professor = professor;
    }
    public String getSala() {
        return sala;
    }
    public void setSala(String sala) {
        this.sala = sala;
    }
    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }
    public UnidadeTematica getUt1() {
        return ut1;
    }
    public void setUt1(UnidadeTematica ut1) {
        this.ut1 = ut1;
    }
    public UnidadeTematica getUt2() {
        return ut2;
    }
    public void setUt2(UnidadeTematica ut2) {
        this.ut2 = ut2;
    }
}
