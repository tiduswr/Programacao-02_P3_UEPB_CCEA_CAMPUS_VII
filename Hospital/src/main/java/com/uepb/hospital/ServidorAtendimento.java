package com.uepb.hospital;

import com.uepb.hospital.ui.DialogUI;
import com.uepb.hospital.ui.frmCadPessoa;
import com.uepb.hospital.ui.frmListarPessoas;
import com.uepb.hospital.ui.frmProntuarioEnfermagem;
import com.uepb.hospital.ui.frmProntuarioMedico;
import java.util.ArrayList;

//Import do teste de cadastro de usuarios no Metodo Construtor da Classe
import java.util.Calendar;

public class ServidorAtendimento implements Triagem{
    
    private DataBase db; 
    private DialogUI ask;
    private String title;
    
    public ServidorAtendimento(String title){
        this.db = new DataBase();
        this.ask = new DialogUI(title);
        this.title = title;
        this.db.readData(0);
        
        //Para realizar testes
        /*Endereco testeEnd = new Endereco("Rua", 1,"Bairro", "Cidade", "Estado");
        Contato testeContato = new Contato("Email@", "3421");
        this.db.getPacientes().add("12345678910", new Paciente("Harllem", "12345678910", Calendar.getInstance().getTime(),'M', 
                testeEnd, testeContato, 1));
        this.db.getPacientes().add("12345678911", new Paciente("Neto", "12345678911", Calendar.getInstance().getTime(),'M', 
                testeEnd, testeContato, 1));
        this.db.getProfissionais().add("12345678933", new ProfissionalSaude("Maria", "12345678933", 
                Calendar.getInstance().getTime(),'M', testeEnd, testeContato, 1));
        this.db.getProfissionais().add("12345678934", new ProfissionalSaude("Jose", "12345678934", 
                Calendar.getInstance().getTime(),'M', testeEnd, testeContato, 2));*/
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public void saveData(int databaseIndex){
        this.db.saveData(databaseIndex);
    }
    @Override
    public void cadastroProfSaude() {
        ProfissionalSaude p = this.buscaContemProfSaude();
        int opt;
        boolean edit;
        
        //Verifica se encontrou algo
        if(p == null){
            p = new ProfissionalSaude();
        }
        
        //Verifica se esta editando
        if(p.getCpf() == null){
            edit = false;
        }else{
            edit = true;
        }
        
        //Cadastra Profissional Saude
        frmCadPessoa<ProfissionalSaude> cad = new frmCadPessoa<>(p, db);
        cad.showForm();

        switch(cad.getRetorno()){
            case 1:
                this.db.getProfissionais().add(cad.getPessoa().cpf, cad.getPessoa());
                //Pega o cargo
                if(edit == true){
                    opt = ask.askOptionButtons("Quer alterar o cargo?", "Por favor, escolha a opção!", 
                                        new String [] {"Sim", "Não"});
                }else{
                    opt = 0;
                }
                
                if(opt == 0){
                    opt = ask.askOptionButtons("Escolha o cargo!", "Por favor, escolha a opção!", 
                                        new String [] {"Enfermeiro(a)", "Médico"});
                    if(opt == 0){
                        cad.getPessoa().setCargo(1);
                    }else if(opt == 1){
                        cad.getPessoa().setCargo(2);
                    }
                }
                break;
            case 2:
                this.db.getProfissionais().remove(cad.getPessoa().cpf);
                break;
            default:
                break;
        }
        cad.dispose();
        cad = null;
    }

    @Override
    public void cadastroPaciente() {
        //Busca a Pessoa
        Paciente p = this.buscaContemNomePaciente();
        
        //Verifica se encontrou algo
        if( p == null){
            p = new Paciente();
        }
        
        //Cadastra paciente
        frmCadPessoa<Paciente> cad = new frmCadPessoa<>(p, db);
        cad.showForm();

        switch(cad.getRetorno()){
            case 1:
                db.getPacientes().add(cad.getPessoa().cpf, cad.getPessoa());
                break;
            case 2:
                db.getPacientes().remove(cad.getPessoa().getCpf());
                break;
            default:
                break;
        }
        cad.dispose();
        cad = null;
    }

    @Override
    public void enviarParaFilaClassificacao() {
        //Realiza a busca do paciente
        Paciente buscaBD = this.buscaContemNomePaciente();
        Paciente buscaFila = null;
        Paciente pacientes[] = new Paciente[this.db.getFilaClassificacao().size()];
        pacientes = this.db.getFilaClassificacao().toArray(pacientes);
        
        for(Paciente i : pacientes){
            if(i.getCpf().equalsIgnoreCase(buscaBD.getCpf())){
                buscaFila = i;
            }
        }
        
        if(buscaBD != null){
            if(buscaFila == null){
                if(!this.db.getFilaClassificacao().add(buscaBD)){
                    ask.showMsg("A fila esta cheia");
                }else{
                    ask.showMsg("Enviado para a fila de Classificação");
                }
            }else{
                ask.showMsg("Essa pessoa ja esta na fila!");
            }
        }else{
            ask.showMsg("Paciente não encontrado!");
        }
    }
    
    @Override
    public void classificacaoEnfermagem() {
        if(this.db.getFilaClassificacao().size() > 0){
            ProfissionalSaude p = this.buscaContemProfSaude();
            if(p != null){
                if(p.getCargo().equalsIgnoreCase("Enfermeiro")){
                    frmProntuarioEnfermagem ui = new frmProntuarioEnfermagem(this.db, p);
                    ui.showForm();
                }else{
                    ask.showMsg("Este profissional não é um Enfermeiro!");
                }
            }else{
                ask.showMsg("Profissional Não encontrado");
            }
        }else{
            ask.showMsg("Não tem ninguem na fila para classificação!");
        }
    }

    @Override
    public void parecerMedico() {
        if(this.db.getFilaMedico().size() > 0){
            ProfissionalSaude p = this.buscaContemProfSaude();
            if(p != null){
                if(p.getCargo().equalsIgnoreCase("Médico")){
                    frmProntuarioMedico ui = new frmProntuarioMedico(this.db, p);
                    ui.showForm();
                }else{
                    ask.showMsg("Esse profissional de Saude não é um médico!");
                }
            }else{
                ask.showMsg("Pessoa não encontrada!");
            }
            
        }else{
            ask.showMsg("Não tem ninguem na fila para Avaliação médica!");
        }
    }

    @Override
    public void listarInternados() {
        Object[][] src = this.db.getInternadosObjectList(true);
        String[] headers = this.db.getProntuarioObjectListHeaders();
        
        frmListarPessoas listar = new frmListarPessoas(src, headers, "Pacientes", "Listando todos os Pacientes", 0);
        listar.showForm();
        
        if(listar.getRetorno() != null){
            ArrayList<Prontuario> items = this.db.getAtendimentos().getArrayList();
            Prontuario p = null;
            
            for(Prontuario i : items){
                if(i.getCpf().equalsIgnoreCase(listar.getRetorno())){
                    p = i;
                    break;
                }
            }
            if(p != null){
                p.setInternado(1);
                listar.dispose();
            }else{
                ask.showMsg("Paciente não encontrado");
            }
            
        }
        
    }

    @Override
    public void listarAltas() {
        Object[][] src = this.db.getInternadosObjectList(false);
        String[] headers = this.db.getProntuarioObjectListHeaders();
        
        frmListarPessoas listar = new frmListarPessoas(src, headers, "Pacientes", "Listando todos os Pacientes");
        listar.showForm();
    }

    @Override
    public void listarPacientes() {
        Object[][] src = this.db.getPacientesObjectList();
        String[] headers = this.db.getPacientesObjectListHeaders();
        
        frmListarPessoas listar = new frmListarPessoas(src, headers, "Pacientes", "Listando todos os Pacientes");
        listar.showForm();
    }

    @Override
    public void listarProfSaude() {
        Object[][] src = this.db.getProfissionaisObjectList();
        String[] headers = this.db.getProfissionaisObjectListHeaders();
        
        frmListarPessoas listar = new frmListarPessoas(src, headers, "Pacientes", "Listando todos os Pacientes");
        listar.showForm();
    }

    private Paciente buscaContemNomePaciente(){
        //Busca a Pessoa
        String keyWord = ask.askText("Por favor digite o nome para Buscar", "Digite um nome por favor!");
        Object[][] resultado = this.db.getPacienteContainsObjectList(keyWord);
        Paciente p;
        frmListarPessoas busca;
        
        if(resultado != null){
            busca = new frmListarPessoas(resultado, this.db.getHeaderStringContainsObjectList(), 
                    "Pacientes", "Resultados da Busca", 0);
            busca.showForm();
            if(busca.getRetorno() != null){
                p = db.getPacientes().get(busca.getRetorno());
                if(p == null){
                    ask.showMsg("Pessoa não encontrada!");
                    return null;
                }else{
                    return p;
                }
            }else{
                ask.showMsg("Processo abortado!");
                return null;
            }
        }else{
            ask.showMsg("Ninguem com um nome parecido foi encontrado!");
            return null;
        }
    }
    
    @Override
    public void listarFila() {
        int opt = ask.askOptionButtons("Qual fila você quer ver?", "??", 
                                            new String[] {"Fila para Enfermeira", "Fila par o Médico"});
        Object[][] src;
        String[] headers;
        frmListarPessoas listar;
        
        switch(opt){
            case 0:
                src = this.db.getFilaClassificacaoObjectList();
                headers = this.db.getFilaClassificacaoObjectListHeaders();

                listar = new frmListarPessoas(src, headers, "Pacientes", "Listando todos os Pacientes");
                listar.showForm();
                break;
            case 1:
                src = this.db.getFilaMedicoObjectList();
                headers = this.db.getFilaMedicoObjectListHeaders();

                listar = new frmListarPessoas(src, headers, "Pacientes", "Listando todos os Pacientes");
                listar.showForm();
                break;
            default:
                break;
        }
    }
    
    private ProfissionalSaude buscaContemProfSaude(){
        //Busca a Pessoa
        String keyWord = ask.askText("Por favor digite o nome para Buscar", "Digite um nome por favor!");
        Object[][] resultado = this.db.getProfissionaisStringContainsObjectList(keyWord);
        ProfissionalSaude p;
        frmListarPessoas busca;
        int opt;
                
        if(resultado != null){
            busca = new frmListarPessoas(resultado, this.db.getHeaderStringContainsObjectList(), 
                    "Profissionais de Saude", "Resultados da Busca", 0);
            busca.showForm();
            if(busca.getRetorno() != null){
                p = db.getProfissionais().get(busca.getRetorno());
                if(p == null){
                    ask.showMsg("Pessoa não encontrada!");
                    return null;
                }
                return p;
            }else{
                ask.showMsg("Processo abortado!");
                return null;
            }
        }else{
            ask.showMsg("Ninguem com um nome parecido foi encontrado, abrindo tela de cadastro");
            return null;
        }
    }
}
