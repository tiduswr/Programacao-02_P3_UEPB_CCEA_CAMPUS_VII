import java.text.DecimalFormat;
import uepb.calcMedia.Contato;
import uepb.calcMedia.Diciplina;
import uepb.calcMedia.Endereco;
import uepb.calcMedia.Estudante;
import uepb.calcMedia.UnidadeTematica;
import javax.swing.JOptionPane;

public class main {
    public static void clearData(Contato c, Diciplina d, Endereco e, UnidadeTematica u,
                            String str1, String str2, char ch, int int1, int int2, boolean bool1){
        c = new Contato("","");
        d = new Diciplina("","","","",new UnidadeTematica(0,0), new UnidadeTematica(0,0));
        e = new Endereco("","","","","");
        u = new UnidadeTematica((double) 0, (double) 0);
        str1 = null;
        str2 = null;
        ch = ' ';
        int1 = 0;
        int2 = 0;
        bool1 = false;
    }
    
    public static void main(String args[]) {
        
        //Constantes
        final String TITLE = "CalcMédia - 1.0";
        final String SEXO_OPCOES[] = {"Masculino", "Feminino"};
        final String TURNO_OPT[] = {"MANHA", "TARDE", "NOITE"};
        final String MENU_OPT[] = {"Cadastrar Estudante", "Mostrar dados","Sair"};
        final double DA_PARA_PASSAR = (double) 7;
        
        //Variaveis Auxiliares
        Contato auxContato = new Contato("","");
        Diciplina auxDiciplina = new Diciplina("","","","",new UnidadeTematica(0,0), new UnidadeTematica(0,0));
        Endereco auxEndereco = new Endereco("","","","","");
        UnidadeTematica auxUT = new UnidadeTematica((double) 0, (double) 0);
        String auxNome = null;
        String auxStr = null;
        char auxSexo = ' ';
        int auxIdade = 0;
        int auxOpt = 0;
        boolean auxValid = false;
        DecimalFormat df = new DecimalFormat("#.00");

        //Variaveis Principais
        Estudante estudante = null;
        
        //Variaveis de Controle
        boolean userDataIsFilled = (boolean) true;
        boolean print = false;
        
        //Menu Principal
        JOptionPane.showInternalMessageDialog(null, "Bem vindo a Calculadora de Médias para Estudantes!",
                                                        TITLE, JOptionPane.INFORMATION_MESSAGE);
        clearData(auxContato, auxDiciplina, auxEndereco, auxUT, auxNome, auxStr, auxSexo, auxIdade, auxOpt, auxValid);
        while(true){
            auxOpt = JOptionPane.showOptionDialog(null, "Escolha uma das opções abaixo para continuar:", TITLE, JOptionPane.YES_NO_OPTION, 
                                                    JOptionPane.QUESTION_MESSAGE, null, MENU_OPT, "Cadastrar Estudante");
            switch(auxOpt){
                case 0:
                    if(estudante != null){
                        auxOpt = JOptionPane.showConfirmDialog(null, "Dados anteriores serão perdidos, deseja continuar?", 
                                                                TITLE, JOptionPane.INFORMATION_MESSAGE);
                        if(auxOpt == 0){
                            userDataIsFilled = false;
                            clearData(auxContato, auxDiciplina, auxEndereco, auxUT, auxNome, auxStr, auxSexo, auxIdade, auxOpt, auxValid);
                        }else{
                            JOptionPane.showMessageDialog(null, "Processo abortado!", TITLE, JOptionPane.OK_OPTION);
                            userDataIsFilled = true;
                        }
                    }else{
                        userDataIsFilled = false;
                        clearData(auxContato, auxDiciplina, auxEndereco, auxUT, auxNome, auxStr, auxSexo, auxIdade, auxOpt, auxValid);
                    }
                    break;
                case 1:
                    if(estudante != null){
                        print = true;
                    }else{
                        JOptionPane.showInternalConfirmDialog(null, "Sem dados para mostrar!", TITLE, 
                                                                JOptionPane.DEFAULT_OPTION);
                        print = false;
                    }
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    break;
            }
            auxOpt = 0;
            if(print){
                auxStr = "DADOS PESSOAIS:\n\n";
                auxStr = auxStr + "Nome: " + estudante.getNome() + "\n";
                auxStr = auxStr + "Idade: " + estudante.getIdade() + "\n";
                auxStr = auxStr + "Sexo: " + estudante.getSexo() + "\n";
                auxStr = auxStr + "Celular: " + estudante.getContato().getNumeroFormatado() + "\n";
                auxStr = auxStr + "E-Mail: " + estudante.getContato().getEmail() + "\n";
                auxStr = auxStr + "Endereco: " + estudante.getEndereco().getEnderecoCompleto() + "\n";
                auxStr = auxStr + "\nDADOS DA DICIPLINA:\n\n";
                auxStr = auxStr + "Nome da Diciplina: " + estudante.getDiciplina().getNome() + "\n";
                auxStr = auxStr + "Professor: " + estudante.getDiciplina().getProfessor() + "\n";
                auxStr = auxStr + "Sala: " + estudante.getDiciplina().getSala() + "\n";
                auxStr = auxStr + "Turno: " + estudante.getDiciplina().getTurno() + "\n";
                auxStr = auxStr + "Média Final: " + String.valueOf(df.format(estudante.getMediaFinal())) + "\n";
                if(estudante.getMediaFinal() >= DA_PARA_PASSAR){
                    auxStr = auxStr + "Satus da Diciplina: APROVADO";
                }else{
                    auxStr = auxStr + "Satus da Diciplina: REPROVADO";
                }
                
                JOptionPane.showInternalConfirmDialog(null, auxStr, TITLE, JOptionPane.DEFAULT_OPTION);
                auxStr = "";
                print = false;
            }
            //Pega os dados do Estudante
            if(!userDataIsFilled){
                JOptionPane.showInternalMessageDialog(null, "Primeiros vamos solicitar os Dados Pessoais!", 
                                                        TITLE, JOptionPane.INFORMATION_MESSAGE);
                //************************************Solicita o Nome
                do{
                    auxNome = JOptionPane.showInputDialog(null, "Digite seu nome:", TITLE, JOptionPane.QUESTION_MESSAGE);
                    if(auxNome == null || auxNome.isBlank()){
                        JOptionPane.showInternalMessageDialog(null, "Por favor, Digite seu Nome!", TITLE, JOptionPane.ERROR_MESSAGE);
                    }
                }while(auxNome == null || auxNome.isBlank());
                //********************************Solicita o Sexo
                do{
                    auxOpt = JOptionPane.showOptionDialog(null, "Informe seu Sexo", TITLE, JOptionPane.YES_NO_OPTION, 
                                                            JOptionPane.QUESTION_MESSAGE, null, SEXO_OPCOES, "Masculino");
                    switch(auxOpt){
                        case 0:
                            auxSexo = 'M';
                            break;
                        case 1:
                            auxSexo = 'F';
                            break;
                        default:
                            break;
                    }
                    if(auxOpt != 0 && auxOpt != 1){
                        JOptionPane.showInternalMessageDialog(null, "Por favor, selecione uma Opção!", TITLE, 
                                                                JOptionPane.ERROR_MESSAGE);
                    }
                }while(auxOpt != 0 && auxOpt != 1);
                //**********************************Solicita Idade
                do{
                    auxStr = JOptionPane.showInputDialog(null, "Por favor digite sua idade:", TITLE, JOptionPane.QUESTION_MESSAGE);

                    if(auxStr != null){
                        if(!auxStr.matches("[0-9]+")){
                            JOptionPane.showInternalMessageDialog(null, "Por favor, digite somente numeros!", TITLE, 
                                                                    JOptionPane.ERROR_MESSAGE);
                            auxStr = null;
                        }else{
                            auxIdade = Integer.parseInt(auxStr);
                        }
                    }else{
                        JOptionPane.showInternalMessageDialog(null, "Por favor, digite somente numeros!", TITLE, 
                                                                    JOptionPane.ERROR_MESSAGE);
                    }
                }while(auxStr == null);
                //*********************************Solicita Contato
                //Celular
                do{
                    auxStr = JOptionPane.showInputDialog(null, "Por favor digite seu celular com o DDD:", 
                                                            TITLE, JOptionPane.QUESTION_MESSAGE);

                    if(auxStr != null){
                        if(!auxStr.matches("[0-9]+") || auxStr.length() != 11){
                            if(auxStr.length() != 11 && !auxStr.matches("[0-9]+")){
                                JOptionPane.showInternalMessageDialog(null, "Por favor, digite os 11 digitos do seu telefone!", TITLE, 
                                                                        JOptionPane.ERROR_MESSAGE);
                                auxStr = null;
                            }else{
                                JOptionPane.showInternalMessageDialog(null, "Por favor, digite somente numeros!", TITLE, 
                                                                        JOptionPane.ERROR_MESSAGE);
                                auxStr = null;
                            }
                        }else{
                            auxContato.setNumero(auxStr);
                        }
                    }else{
                        JOptionPane.showInternalMessageDialog(null, "Por favor, digite somente numeros!", TITLE, 
                                                                    JOptionPane.ERROR_MESSAGE);
                    }
                }while(auxStr == null);
                //Email
                do{
                    auxStr = JOptionPane.showInputDialog(null, "Digite seu E-Mail:", TITLE, JOptionPane.QUESTION_MESSAGE);
                    if(auxStr == null || auxStr.isBlank()){
                        JOptionPane.showInternalMessageDialog(null, "Por favor, Digite seu E-Mail!", TITLE, JOptionPane.ERROR_MESSAGE);
                    }else{
                        auxContato.setEmail(auxStr);
                    }
                }while(auxStr == null || auxStr.isBlank());
                //**************************************Solicita Endereço
                JOptionPane.showInternalMessageDialog(null, "Agora informe seu Endereço!", 
                                                        TITLE, JOptionPane.INFORMATION_MESSAGE);
                //Rua
                do{
                    auxStr = JOptionPane.showInputDialog(null, "Digite o nome da sua Rua:", TITLE, JOptionPane.QUESTION_MESSAGE);
                    if(auxStr == null || auxStr.isBlank()){
                        JOptionPane.showInternalMessageDialog(null, "Por favor, digite o nome da Rua!", TITLE, JOptionPane.ERROR_MESSAGE);
                    }else{
                        auxEndereco.setRua(auxStr);
                    }
                }while(auxStr == null || auxStr.isBlank());
                //Bairro
                do{
                    auxStr = JOptionPane.showInputDialog(null, "Digite o nome do seu Bairro:", TITLE, JOptionPane.QUESTION_MESSAGE);
                    if(auxStr == null || auxStr.isBlank()){
                        JOptionPane.showInternalMessageDialog(null, "Por favor, digite o nome do Bairro!", TITLE, JOptionPane.ERROR_MESSAGE);
                    }else{
                        auxEndereco.setBairro(auxStr);
                    }
                }while(auxStr == null || auxStr.isBlank());
                //Cidade
                do{
                    auxStr = JOptionPane.showInputDialog(null, "Digite o nome da sua Cidade:", TITLE, JOptionPane.QUESTION_MESSAGE);
                    if(auxStr == null || auxStr.isBlank()){
                        JOptionPane.showInternalMessageDialog(null, "Por favor, digite o nome da sua Cidade!", TITLE, JOptionPane.ERROR_MESSAGE);
                    }else{
                        auxEndereco.setCidade(auxStr);
                    }
                }while(auxStr == null || auxStr.isBlank());
                //UF
                do{
                    auxStr = JOptionPane.showInputDialog(null, "Digite a Sigla do seu Estado:", TITLE, JOptionPane.QUESTION_MESSAGE);
                    if(auxStr == null || auxStr.isBlank()){
                        JOptionPane.showInternalMessageDialog(null, "Por favor, digite Sigla do seu Estado!", TITLE, JOptionPane.ERROR_MESSAGE);
                    }else{
                        auxEndereco.setUf(auxStr);
                    }
                }while(auxStr == null || auxStr.isBlank());
                //Pais
                do{
                    auxStr = JOptionPane.showInputDialog(null, "Digite o nome do seu Pais:", TITLE, JOptionPane.QUESTION_MESSAGE);
                    if(auxStr == null || auxStr.isBlank()){
                        JOptionPane.showInternalMessageDialog(null, "Por favor, digite o nome do seu Pais!", TITLE, JOptionPane.ERROR_MESSAGE);
                    }else{
                        auxEndereco.setPais(auxStr);
                    }
                }while(auxStr == null || auxStr.isBlank());
                //******************************************Solicita informações sobre a Diciplina
                JOptionPane.showInternalMessageDialog(null, "Agora informe as Informações sobre a Diciplina!", 
                                                        TITLE, JOptionPane.INFORMATION_MESSAGE);
                //Nome da Diciplina
                do{
                    auxStr = JOptionPane.showInputDialog(null, "Digite o nome da Diciplina:", TITLE, JOptionPane.QUESTION_MESSAGE);
                    if(auxStr == null || auxStr.isBlank()){
                        JOptionPane.showInternalMessageDialog(null, "Por favor, digite o nome da Diciplina!", TITLE, JOptionPane.ERROR_MESSAGE);
                    }else{
                        auxDiciplina.setNome(auxStr);
                    }
                }while(auxStr == null || auxStr.isBlank());
                //Professor
                do{
                    auxStr = JOptionPane.showInputDialog(null, "Digite o nome do seu Professor:", TITLE, JOptionPane.QUESTION_MESSAGE);
                    if(auxStr == null || auxStr.isBlank()){
                        JOptionPane.showInternalMessageDialog(null, "Por favor, digite o nome do seu Professor!", TITLE, JOptionPane.ERROR_MESSAGE);
                    }else{
                        auxDiciplina.setProfessor(auxStr);
                    }
                }while(auxStr == null || auxStr.isBlank());
                //Sala
                do{
                    auxStr = JOptionPane.showInputDialog(null, "Digite o nome da Sala:", TITLE, JOptionPane.QUESTION_MESSAGE);
                    if(auxStr == null || auxStr.isBlank()){
                        JOptionPane.showInternalMessageDialog(null, "Por favor, digite o nome da Sala!", TITLE, JOptionPane.ERROR_MESSAGE);
                    }else{
                        auxDiciplina.setSala(auxStr);
                    }
                }while(auxStr == null || auxStr.isBlank());
                //Turno
                do{
                    auxOpt = JOptionPane.showOptionDialog(null, "Informe seu Turno", TITLE, JOptionPane.YES_NO_OPTION, 
                                                            JOptionPane.QUESTION_MESSAGE, null, TURNO_OPT, "MANHA");
                    if(auxOpt == 0 || auxOpt == 1 || auxOpt == 2){
                        auxDiciplina.setTurno(TURNO_OPT[auxOpt]);
                    }
                    if(auxOpt != 0 && auxOpt != 1 && auxOpt != 2){
                        JOptionPane.showInternalMessageDialog(null, "Por favor, selecione uma Opção!", TITLE, 
                                                                JOptionPane.ERROR_MESSAGE);
                    }
                }while(auxOpt != 0 && auxOpt != 1 && auxOpt != 2);
                //Unidade Tematica
                for(int i = 0; i < 2; i++){
                    auxUT = new UnidadeTematica((double) 0, (double) 0);
                    for(int j = 0; j < 2; j++){
                        do{
                            auxStr = JOptionPane.showInputDialog(null, "Por favor digite a nota " + (j+1) + " da Unidade Tematica" + (i+1) + ":", 
                                                                    TITLE + "(Unidade Tematica "+ (i+1) +")", JOptionPane.QUESTION_MESSAGE);

                            try{
                                if(j == 0){
                                   auxUT.setNota1(Double.parseDouble(auxStr));
                                   auxValid = true;
                                }else{
                                   auxUT.setNota2(Double.parseDouble(auxStr));
                                   auxValid = true;
                                }
                            } catch(NumberFormatException e){
                                JOptionPane.showInternalMessageDialog(null, "Por favor, digite um valor válido!", TITLE, 
                                                                        JOptionPane.ERROR_MESSAGE);
                                auxValid = false;
                            }
                        }while(!auxValid);
                    }
                    if(i == 0){
                        auxDiciplina.setUt1(auxUT);
                    }else{
                        auxDiciplina.setUt2(auxUT);
                    }
                    auxUT = null;
                }
                //Salva os dados do estudante
                estudante = new Estudante(auxNome, auxIdade, auxSexo, auxContato, auxDiciplina, auxEndereco);

                //Altera variavel de controle da entrada de dados
                userDataIsFilled = true;
            }
        }
    }
}
