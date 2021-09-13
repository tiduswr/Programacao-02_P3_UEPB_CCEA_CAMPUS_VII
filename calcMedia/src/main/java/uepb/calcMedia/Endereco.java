package uepb.calcMedia;

public class Endereco {
    private String pais;
    private String uf;
    private String cidade;
    private String bairro;
    private String rua;
    
    public Endereco(String sRua, String sBairro, String sUF, String sCidade, String sPais){
        this.pais = sPais;
        this.uf = sUF;
        this.cidade = sCidade;
        this.bairro = sBairro;
        this.rua = sRua;
    }
    
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
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getEnderecoCompleto(){
        String aux;
        aux = this.getRua() + ", " + this.getBairro() + ", " + this.getCidade() + 
                "-" + this.getUf() + ", " + this.getPais();
        return aux;
    }
}
