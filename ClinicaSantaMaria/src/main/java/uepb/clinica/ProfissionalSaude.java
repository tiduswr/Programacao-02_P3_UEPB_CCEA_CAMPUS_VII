package uepb.clinica;

public class ProfissionalSaude extends Pessoa {
    private String tipoProfissional, crm_coren;

    public ProfissionalSaude(String nome, String cpf, int idade, char sexo, Endereco endereco, Contato contato,
                    String tipoProfissional, String crm_coren){
        super(nome, cpf, idade, sexo, endereco, contato);
        this.tipoProfissional = tipoProfissional;
        this.crm_coren = crm_coren;
    }
    public ProfissionalSaude(){
    }
    
    public String getTipoProfissional() {
        return tipoProfissional;
    }
    public void setTipoProfissional(String tipoProfissional) {
        this.tipoProfissional = tipoProfissional;
    }
    public String getCrm_coren() {
        return crm_coren;
    }
    public void setCrm_coren(String crm_coren) {
        this.crm_coren = crm_coren;
    }
    
}
