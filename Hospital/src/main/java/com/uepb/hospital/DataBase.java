package com.uepb.hospital;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class DataBase {
    private MyHash<String, ProfissionalSaude> profissionais;
    private MyHash<String, Paciente> pacientes;
    private MyHash <Integer, Prontuario> atendimentos;
    private PriorityQueue<Paciente> filaMedico, filaClassificacao;
    private int codGerados;
    
    public DataBase(){
        this.profissionais = new MyHash<>();
        this.pacientes = new MyHash<>();
        this.atendimentos = new MyHash<>();
        this.filaClassificacao = new PriorityQueue<>();
        this.filaMedico = new PriorityQueue<>();
        this.codGerados = 0;
    }
    
    public int gerarIdAtendimento(){
        this.codGerados++;
        return this.codGerados;
    }
    public PriorityQueue<Paciente> getFilaMedico() {
        return filaMedico;
    }
    public void setFilaMedico(PriorityQueue<Paciente> filaMedico) {
        this.filaMedico = filaMedico;
    }
    public PriorityQueue<Paciente> getFilaClassificacao() {
        return filaClassificacao;
    }
    public void setFilaClassificacao(PriorityQueue<Paciente> filaClassificacao) {
        this.filaClassificacao = filaClassificacao;
    }
    public MyHash<String, ProfissionalSaude> getProfissionais() {
        return profissionais;
    }
    public void setProfissionais(MyHash<String, ProfissionalSaude> profissionais) {
        this.profissionais = profissionais;
    }
    public MyHash<String, Paciente> getPacientes() {
        return pacientes;
    }
    public void setPacientes(MyHash<String, Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    public MyHash<Integer, Prontuario> getAtendimentos() {
        return atendimentos;
    }
    public void setAtendimentos(MyHash<Integer, Prontuario> atendimentos) {
        this.atendimentos = atendimentos;
    }
    
    //Busca por nome
    public String[] getHeaderStringContainsObjectList(){
        return new String[] {"CPF", "NOME"};
    }
    public Object[][] getProfissionaisStringContainsObjectList(String arg){
        ArrayList<ProfissionalSaude> aux = this.getProfissionais().getArrayList();
        ArrayList<ProfissionalSaude> busca = new ArrayList();
        
        //Monta arraylist da busca
        for(ProfissionalSaude i : aux){
            if(i.getNome().contains(arg)){
                busca.add(i);
            }
        }
        
        //Verifica se tem retorno para montar tabela
        int size = busca.size();
        
        if(size > 0){
            Object[][] src = new Object[size][2];

            for(int i = 0; i < size; i++){
                src[i][0] = busca.get(i).getCpf();
                src[i][1] = busca.get(i).getNome();
            }
            return src;
        }else{
            return null;
        }
    }
    public Object[][] getPacienteContainsObjectList(String arg){
        ArrayList<Paciente> aux = this.getPacientes().getArrayList();
        ArrayList<Paciente> busca = new ArrayList();
        
        //Monta arraylist da busca
        for(Paciente i : aux){
            if(i.getNome().contains(arg)){
                busca.add(i);
            }
        }
        
        //Verifica se tem retorno para montar tabela
        int size = busca.size();
        
        if(size > 0){
            Object[][] src = new Object[size][2];

            for(int i = 0; i < size; i++){
                src[i][0] = busca.get(i).getCpf();
                src[i][1] = busca.get(i).getNome();
            }
            return src;
        }else{
            return null;
        }
    }
    
    public boolean saveData(int databaseIndex){
        final String[] arquivosNome = {"/DataBaseAtendimentos.db","/DataBasePacientes.db","/DataBaseProfissionais.db",
                                        "/DataBaseFilaClassificacao.db", "/DataBaseFilaMedico.db"};
        
        try{
            if(databaseIndex == 0){
                for(int i = 0; i<5; i++){
                    String dir = "/home/tiduswr/NetBeansProjects/Hospital/src/main/java/com/uepb/hospital";
                    FileOutputStream arq = new FileOutputStream(dir + arquivosNome[i]);
                    ObjectOutputStream writer = new ObjectOutputStream(arq);

                    switch (arquivosNome[i]){
                        case "/DataBaseAtendimentos.db" -> writer.writeObject(this.atendimentos);
                        case "/DataBasePacientes.db" -> writer.writeObject(this.pacientes);
                        case "/DataBaseProfissionais.db" -> writer.writeObject(this.profissionais);
                        case "/DataBaseFilaClassificacao.db" -> writer.writeObject(this.filaClassificacao);
                        case "/DataBaseFilaMedico.db" -> writer.writeObject(this.filaMedico);
                    }
                    writer.close();
                    arq.close();  
                }
                return true;
            }else{
                String dir = "/home/tiduswr/NetBeansProjects/Hospital/src/main/java/com/uepb/hospital";
                FileOutputStream arq = new FileOutputStream(dir + arquivosNome[databaseIndex]);
                ObjectOutputStream writer = new ObjectOutputStream(arq);

                switch (arquivosNome[databaseIndex]){
                    case "/DataBaseAtendimentos.db" -> writer.writeObject(this.atendimentos);
                    case "/DataBasePacientes.db" -> writer.writeObject(this.pacientes);
                    case "/DataBaseProfissionais.db" -> writer.writeObject(this.profissionais);
                    case "/DataBaseFilaClassificacao.db" -> writer.writeObject(this.filaClassificacao);
                    case "/DataBaseFilaMedico.db" -> writer.writeObject(this.filaMedico);
                }

                writer.close();
                arq.close();
                return true;
            }
        }catch(IOException e){
            System.out.println("Erro em: " + e.getMessage());
            return false;
        }
    }
    public boolean readData(int databaseIndex){
        final String[] arquivosNome = {"/DataBaseAtendimentos.db","/DataBasePacientes.db","/DataBaseProfissionais.db",
                                        "/DataBaseFilaClassificacao.db", "/DataBaseFilaMedico.db"};
        
        try{
            if(databaseIndex == 0){
                for(int i = 0; i<5; i++){
                    String dir = "/home/tiduswr/NetBeansProjects/Hospital/src/main/java/com/uepb/hospital";
                    FileInputStream arq = new FileInputStream(dir + arquivosNome[i]);
                    ObjectInputStream reader = new ObjectInputStream(arq);

                    switch (arquivosNome[i]){
                        case "/DataBaseAtendimentos.db" -> this.atendimentos = (MyHash <Integer, Prontuario>) reader.readObject();
                        case "/DataBasePacientes.db" -> this.pacientes = (MyHash<String, Paciente>) reader.readObject();
                        case "/DataBaseProfissionais.db" -> this.profissionais = (MyHash<String, ProfissionalSaude>) reader.readObject();
                        case "/DataBaseFilaClassificacao.db" -> this.filaClassificacao = (PriorityQueue<Paciente>) reader.readObject();
                        case "/DataBaseFilaMedico.db" -> {
                            this.filaMedico = (PriorityQueue<Paciente>) reader.readObject();
                            this.restartContadores();
                        }
                    }

                    reader.close();
                    arq.close();                    
                }
                return true;
            }else{
                String dir = "/home/tiduswr/NetBeansProjects/Hospital/src/main/java/com/uepb/hospital";
                FileInputStream arq = new FileInputStream(dir + arquivosNome[databaseIndex]);
                ObjectInputStream reader = new ObjectInputStream(arq);

                switch (arquivosNome[databaseIndex]){
                    case "/DataBaseAtendimentos.db" -> this.atendimentos = (MyHash <Integer, Prontuario>) reader.readObject();
                    case "/DataBasePacientes.db" -> this.pacientes = (MyHash<String, Paciente>) reader.readObject();
                    case "/DataBaseProfissionais.db" -> this.profissionais = (MyHash<String, ProfissionalSaude>) reader.readObject();
                    case "/DataBaseFilaClassificacao.db" -> this.filaClassificacao = (PriorityQueue<Paciente>) reader.readObject();
                    case "/DataBaseFilaMedico.db" -> {
                        this.filaMedico = (PriorityQueue<Paciente>) reader.readObject();
                        this.restartContadores();
                    }
                }
                reader.close();
                arq.close();
                this.restartContadores();
                return true;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro em: " + e.getMessage());
            return false;
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Erro em: " + e.getMessage());
            return false;
        }
    }
    //Essa função é usada exclusivamente no método readData() para reiniciar as Threads do Cronomêtro
    private void restartContadores(){
        Paciente[] ps = new Paciente[this.getFilaMedico().size()];
        ps = this.getFilaMedico().toArray(ps);
        for (Paciente p : ps) {
            p.getContador().start();
        }
    }
    
    //Object Lists para usar em userforms
    public Object[][] getProfissionaisObjectList(){
        Object[][] src = new Object[this.getProfissionais().size()][4];
        ArrayList<ProfissionalSaude> aux = this.getProfissionais().getArrayList();
        SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
        
        for(int i = 0; i < this.getProfissionais().size(); i ++){
            src[i][0] = aux.get(i).getCpf();
            src[i][1] = aux.get(i).getNome();
            src[i][2] = f.format(aux.get(i).getDtNasc());
            src[i][3] = aux.get(i).getCargo();
        }
        return src;
    }
    public String[] getProfissionaisObjectListHeaders(){
        return new String[] {"CPF", "NOME", "DT.NASC", "CARGO"};
    }
    
    public Object[][] getPacientesObjectList(){
        Object[][] src = new Object[this.getPacientes().size()][4];
        ArrayList<Paciente> aux = this.getPacientes().getArrayList();
        SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
        
        for(int i = 0; i < this.getPacientes().size(); i ++){
            src[i][0] = aux.get(i).getCpf();
            src[i][1] = aux.get(i).getNome();
            src[i][2] = f.format(aux.get(i).getDtNasc());
            src[i][3] = aux.get(i).getContato().getCelularSemFormatacao();
        }
        return src;
    }
    public String[] getPacientesObjectListHeaders(){
        return new String[] {"CPF", "NOME", "DT.NASC", "CONTATO"};
    }
    
    public Object[][] getProntuarioObjectList(){
        Object[][] src = new Object[this.getAtendimentos().size()][8];
        ArrayList<Prontuario> aux = this.getAtendimentos().getArrayList();
        
        for(int i = 0; i < this.getAtendimentos().size(); i ++){
            src[i][0] = aux.get(i).getCpf();
            src[i][1] = this.getPacientes().get(aux.get(i).getCpf()).getNome();
            src[i][2] = aux.get(i).getdtClassificacaoFormatado();
            src[i][3] = aux.get(i).getSaidaFormatado();
            if(this.getProfissionais().get(aux.get(i).getCpfMedico()) != null){
                src[i][4] = this.getProfissionais().get(aux.get(i).getCpfMedico()).getNome();
            }else{
                src[i][4] = "";
            }
            if(this.getProfissionais().get(aux.get(i).getCpfProfSaude()) != null){
                src[i][5] = this.getProfissionais().get(aux.get(i).getCpfProfSaude()).getNome();
            }else{
                src[i][5] = "";
            }    
                switch (aux.get(i).getInternado()) {
                    case 2:
                        src[i][6] = "Internado";
                        break;
                    case 1:
                        src[i][6] = "Teve Alta";
                        break;
                    default:
                        src[i][6] = "";
                        break;
                }
            src[i][7] = aux.get(i).getEntradaFormatado();
        }
        return src;
    }
    public String[] getProntuarioObjectListHeaders(){
        return new String[] {"CPF", "NOME", "DT.Classificação", "DT.Saida", "NOME MEDICO", 
            "NOME ENFERMEIRO", "Status Paciente", "Data.Entrada"};
    }
    
    public Object[][] getFilaClassificacaoObjectList(){
        Object[][] src = new Object[this.getFilaClassificacao().size()][1];
        Paciente[] aux = new Paciente[this.getFilaClassificacao().size()];
        aux = this.getFilaClassificacao().toArray(aux);
        
        for(int i = 0; i < this.getFilaClassificacao().size(); i ++){
            src[i][0] = aux[i].getNome();
        }
        return src;
    }
    public String[] getFilaClassificacaoObjectListHeaders(){
        return new String[] {"NOME DA PESSOA NA FILA"};
    }
    
    public Object[][] getFilaMedicoObjectList(){
        Object[][] src = new Object[this.getFilaMedico().size()][4];
        Paciente[] aux = new Paciente[this.getFilaMedico().size()];
        aux = this.getFilaMedico().toArray(aux);
        
        for(int i = 0; i < this.getFilaMedico().size(); i ++){
            src[i][0] = aux[i].getPrioridadeFormatada();
            src[i][1] = aux[i].getNome();
            if(aux[i].getPrioridade() != 1){
                src[i][2] = aux[i].getTempoFormatado();
                src[i][3] = aux[i].getTempoTotalEspera();
            }else{
                src[i][2] = "Atendimento Imediato";
                src[i][3] = "Atendimento Imediato";
            }
        }
        return src;
    }
    public String[] getFilaMedicoObjectListHeaders(){
        return new String[] {"PRIORIDADE","NOME", "TEMPO ESPERANDO", "TEMPO MAXIMO DE ESPERA"};
    }
    
    public Object[][] getInternadosObjectList(boolean arg){
        Object[][] src = this.getProntuarioObjectList();
        int size = 0;
        Object[][] srcFilter;
        
        if(src != null){
            if(arg){
                //Conta as ocorrencias de Internados
                for (Object[] i : src) {
                    if(String.valueOf(i[6]).equalsIgnoreCase("Internado")){
                        size++;
                    }
                }
                
                //Cria o array
                srcFilter = new Object[size][7];
                int ln = 0;
                
                //Adiciona as linhas ao Objeto
                for(int i = 0; i < src.length; i++){
                    if(String.valueOf(src[i][6]).equalsIgnoreCase("Internado")){
                        srcFilter[ln] = src[i];
                        ln++;
                    }
                }
                return srcFilter;
                
            }else{
                //Conta as ocorrencias de Altas
                for (Object[] i : src) {
                    if(String.valueOf(i[6]).equalsIgnoreCase("Teve Alta")){
                        size++;
                    }
                }
                
                //Cria o array
                srcFilter = new Object[size][7];
                int ln = 0;
                
                //Adiciona as linhas ao Objeto
                for(int i = 0; i < src.length; i++){
                    if(String.valueOf(src[i][6]).equalsIgnoreCase("Teve Alta")){
                        srcFilter[ln] = src[i];
                        ln++;
                    }
                }
                
                return srcFilter;
            }
        }else{
            return null;
        }
    }
}
