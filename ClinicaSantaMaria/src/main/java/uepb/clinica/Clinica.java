package uepb.clinica;

public interface Clinica {
    public void cadastrarPaciente(Paciente paciente);
    public void cadastrarProfSaude(ProfissionalSaude profissional);
    public void buscarPaciente();
    public void buscarProfSaude();
    public void listarPacientes();
    public void listarProfSaude();
    public void registrarAtendimento();
}
