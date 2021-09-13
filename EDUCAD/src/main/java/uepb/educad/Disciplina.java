package uepb.educad;

public class Disciplina {
    private float notasUT[][];
    private float provaFinal;
    private String nomeDisciplina;
    private int numFaltas;
    private String nomeProfessor;
    private int periodoLetivo;
            
    public Disciplina(){
        this.provaFinal = 0f;
        this.nomeDisciplina = "";
        this.numFaltas = 0;
        this.nomeProfessor = "";
        this.periodoLetivo = 0;
    }
    
    //Metodos Getters and Setters
    public float[][] getNotasUT() {
        return notasUT;
    }
    public void setNotasUT(float[][] notasUT) {    
        this.notasUT = notasUT;
    }
    public float getProvaFinal() {
        return provaFinal;
    }
    public void setProvaFinal(float provaFinal) {
        this.provaFinal = provaFinal;
    }
    public String getNomeDisciplina() {
        return nomeDisciplina;
    }
    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }
    public int getNumFaltas() {
        return numFaltas;
    }
    public void setNumFaltas(int numFaltas) {
        this.numFaltas = numFaltas;
    }
    public String getNomeProfessor() {
        return nomeProfessor;
    }
    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }
    public int getPeriodoLetivo() {
        return periodoLetivo;
    }
    public void setPeriodoLetivo(int periodoLetivo) {
        this.periodoLetivo = periodoLetivo;
    }
    //Métodos principais
    public float getNotaUT(int ut, int nota){
        //É considerado o inicio de ut e nota sendo > 0
        if(ut > 0 && ut <= this.notasUT.length){
            if(nota > 0 && nota <= this.notasUT[ut-1].length){
                return (float) this.notasUT[ut-1][nota-1];
            }else{
                return (float) -1;
            }
        }
        return (float) -1;
    }
    public float getMedia(int ut){
        //É considerado o inicio de ut e nota sendo > 0
        if(ut > 0 && ut <= this.notasUT.length){
            float aux = 0f;
            for(int i = 0; i < (this.notasUT[ut-1].length); i++){
                aux = Float.sum(aux, this.notasUT[ut-1][i]);
            }
            return (float) aux/(float)(this.notasUT[ut-1].length);
        }
        return (float) -1;
    }
    public float getMediaTotal(){
        float aux = 0;
        
        for(int i = 0; i < this.notasUT.length; i++){
            aux = Float.sum(aux, this.getMedia(i+1));
        }
        return aux/(float) this.notasUT.length;
    }
    public float getMediaFinal(){
        float aux = this.getMediaTotal();
        if(aux > (float) 2 && aux < (float) 7){
            return (float) Float.sum(aux, this.provaFinal)/(float) 2.0;
        }
        return aux;
    }
    public String status(){
        if(this.getMediaFinal() >= (float) 7){
            return "APROVADO";
        }
        return "REPROVADO";
    }
}
