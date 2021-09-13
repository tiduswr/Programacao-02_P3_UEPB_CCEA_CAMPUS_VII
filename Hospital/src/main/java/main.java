
import com.uepb.hospital.ServidorAtendimento;
import com.uepb.hospital.ui.DialogUI;


public class main {
    public static void main(String args[]) {
        ServidorAtendimento server = new ServidorAtendimento("Hospital Regional de Patos");
        DialogUI ask = new DialogUI(server.getTitle());
        int opt;
        
        while(true){
            opt = 0;
            int auxOpt = ask.askOptionButtons("Bem vindo ao Prontuario Eletronico de Pacientes | " + server.getTitle(), "??", 
                    new String[] {"Cadastrar/Editar Pessoas", "Enviar para Fila de Classificação", "Listagens",
                                    "Prontuarios", "Tela de Fila dos Pacientes"});
            switch(auxOpt){
                case 0 -> {
                    opt = ask.askOptionButtons("Você quer Cadastrar qual tipo de Pessoa?", "??",
                            new String[] {"Paciente", "Profissional de Saúde"});
                    
                    switch(opt){
                        case 0 -> server.cadastroPaciente();
                        case 1 -> server.cadastroProfSaude();
                        default -> ask.showMsg("Processo abortado!");
                    }
                }

                case 1 -> server.enviarParaFilaClassificacao();
                case 2 -> {
                    //Listagens
                    opt = ask.askOptionButtons("O que você quer listar?", "??",
                            new String[] {"Paciente que deram Alta", "Pacientes Cadastrados",
                                "Profissionais de Saude"});
                    switch(opt){
                        case 0 -> server.listarAltas();
                        case 1 -> server.listarPacientes();
                        case 2 -> server.listarProfSaude();
                        default -> {
                        }
                    }
                }

                case 3 -> {
                    //Prontuario
                    opt = ask.askOptionButtons("O que você quer listar?", "??",
                            new String[] {"Pacientes Internados", "Prontuario de Classificação",
                                "Parecer Médico"});
                    switch(opt){
                        case 0 -> server.listarInternados();
                        case 1 -> server.classificacaoEnfermagem();
                        case 2 -> server.parecerMedico();
                        default -> {
                        }
                    }
                }

                case 4 -> server.listarFila();
                case -1 -> {
                    server.saveData(0);
                    System.exit(0);
                }
                default -> {
                }
            }
        }
        
    }
}
