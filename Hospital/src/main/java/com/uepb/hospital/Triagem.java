package com.uepb.hospital;

public interface Triagem {
    public void cadastroProfSaude();
    public void cadastroPaciente();
    public void enviarParaFilaClassificacao();
    public void classificacaoEnfermagem();
    public void parecerMedico();
    public void listarInternados();
    public void listarAltas();
    public void listarPacientes();
    public void listarProfSaude();
    public void listarFila();
}
