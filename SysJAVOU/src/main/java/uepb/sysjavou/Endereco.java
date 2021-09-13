package uepb.sysjavou;

public class Endereco {
    private String pais;
    private String uf;
    private String cidade;
    private String bairro;
    private String logradouro;
    private int numero;
    private String tipoLogradouro;
    
    public Endereco(){
        
    }
    
    public Endereco(String pais, String uf, String cidade, String bairro,
                    String logradouro, int numero, String tipoLogradouro){
        this.pais = pais;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.tipoLogradouro = tipoLogradouro;
    }

    //Getters and Setters
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getTipoLogradouro() {
        return tipoLogradouro;
    }
    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }
    
    //Métodos Principais
    public String getEnderecoCompleto(){
        String aux;
        
        aux = this.getTipoLogradouro() + " " + this.getLogradouro() + " ";
        if(this.getNumero() <= 0){
            aux = aux + "S/N";
        }else{
            aux = aux + "Nº " + this.getNumero();
        }
        aux = aux + ", " + this.getBairro() + ", " + this.getCidade() + "-" + this.getUf() + ", ";
        aux = aux + this.getPais();
        
        return aux;
    }
    
}
