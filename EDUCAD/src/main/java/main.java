
import uepb.educad.Contato;
import uepb.educad.Disciplina;
import uepb.educad.Endereco;
import uepb.educad.Estudante;
import uepb.educad.UI.UI;
import uepb.educad.UI.ViewEstudantes;
import uepb.educad.Universidade;

public class main {
     
    public static void main(String args[]) throws InterruptedException{
        
        //Variavel principal
        Universidade org = new Universidade();
        //Variaveis Auxiliars
        Estudante auxEstudante;
        Contato auxContato;
        Disciplina auxDisciplina;
        Endereco auxEndereco;
        int auxOpt = 0;
        float auxNotas[][];
        String auxStr;
        //Interfaces de Usuario
        UI ask = new UI("EduCad V1.0 by Harllem");
        ViewEstudantes tblEstudantes;
        
        //Solicita dados da Universidade
        ask.showMsg("Bem vindo ao EduCad, primeiro informe os dados da Universidade!");
        org.setNome(ask.askText("Insira o nome da Instituição:", "Não deixe vazio!"));
        org.setCampus(ask.askText("Digite o nome do Campus da Universidade:", "Não deixe vazio!"));
        //Contato da Universidade
        ask.showMsg("Agora preencha os dados referente ao Contato");
        auxContato = new Contato();
        auxContato.setTelefone(ask.askTextOnlyNumbers("Digite o numero do Telefone:", "Digite apenas numeros!"));
        auxContato.setEmail(ask.askText("Digite o E-Mail:", "Não deixe em Branco!"));
        auxContato.setRedeSocial(ask.askText("Digite a sua rede Social:", "Não deixe em branco!"));
        org.setContato(auxContato);
        auxContato = null;
        //Endereco da Universidade
        ask.showMsg("Agora preencha os dados referente ao Endereco");
        auxEndereco = new Endereco();
        auxEndereco.setTipoLogradouro(ask.askText("Digite o tipo de Logradouro:", "Não deixe em branco!"));
        auxEndereco.setLogradouro(ask.askText("Digite o nome da rua:", "Não deixe em branco!"));
        auxEndereco.setNumero(Integer.parseInt(ask.askTextOnlyNumbers("Digite o numero da Rua:", "Digite apenas numeros!")));
        auxEndereco.setBairro(ask.askText("Digite o bairro:", "Não deixe em branco!"));
        auxEndereco.setCidade(ask.askText("Digite o nome da Cidade:", "Não deixe em branco!"));
        auxEndereco.setUf(ask.askText("Digite a UF do seu Municipio:", "Não deixe em branco!"));
        auxEndereco.setPais(ask.askText("Digite o nome do pais:", "Não deixe em branco!"));
        org.setEndereco(auxEndereco);
        auxEndereco = null;
        
        //Menu principal
        while(true){
            auxOpt = ask.askOptionButtons("Bem vindo ao sistema de gerenciamento de Estudantes!", "??", 
                    new String[] {"Cadastrar Estudante", "Excluir Estudante", "Mostrar Tabela de Usuários",
                                    "Mostrar Estudante"});
            switch(auxOpt){
                case 0:
                    auxStr = ask.askText("Digite o nome do Estudante:", "Não deixe em branco");
                    
                    if(org.getEstudante(auxStr) == (Estudante) null){
                    
                        //Seta variaveis para trabalhar
                        auxEstudante = new Estudante();
                        auxContato = new Contato();
                        auxDisciplina = new Disciplina();
                        auxEndereco = new Endereco();

                        //Solicita dados
                        auxEstudante.setNome(auxStr);
                        auxEstudante.setMatricula(ask.askText("Digite a matricula do Estudante:", "Não deixe em branco"));
                        auxEstudante.setIdade(Integer.parseInt(ask.askTextOnlyNumbers("Digite a idade do Estudante:", "Não deixe em branco")));
                        auxEstudante.setSexo(ask.askOptionButtons("Qual o sexo do Estudante?", "Por favor, escolha uma opção!",
                                                new String[] {"Masculino", "Feminino"}));
                        //Solicita o Endereco
                        ask.showMsg("Agora Preencha os dados referente ao Endereço!");
                        auxEndereco.setTipoLogradouro(ask.askText("Digite o tipo de Logradouro:", "Não deixe em branco!"));
                        auxEndereco.setLogradouro(ask.askText("Digite o nome da rua:", "Não deixe em branco!"));
                        auxEndereco.setNumero(Integer.parseInt(ask.askTextOnlyNumbers("Digite o numero da Rua:", "Digite apenas numeros!")));
                        auxEndereco.setBairro(ask.askText("Digite o bairro:", "Não deixe em branco!"));
                        auxEndereco.setCidade(ask.askText("Digite o nome da Cidade:", "Não deixe em branco!"));
                        auxEndereco.setUf(ask.askText("Digite a UF do seu Municipio:", "Não deixe em branco!"));
                        auxEndereco.setPais(ask.askText("Digite o nome do pais:", "Não deixe em branco!"));
                        auxEstudante.setEndereco(auxEndereco);
                        auxEndereco = null;
                        //Solicita Contato
                        ask.showMsg("Agora preencha os dados referente ao Contato");
                        auxContato.setTelefone(ask.askTextOnlyNumbers("Digite o numero do Telefone:", "Digite apenas numeros!"));
                        auxContato.setEmail(ask.askText("Digite o E-Mail:", "Não deixe em Branco!"));
                        auxContato.setRedeSocial(ask.askText("Digite a rede Social:", "Não deixe em branco!"));
                        auxEstudante.setContato(auxContato);
                        auxContato = null;
                        //Solicita os dados da Disciplina
                        ask.showMsg("Agora preencha os dados referente a Disciplina");
                        auxDisciplina.setNomeDisciplina(ask.askText("Digite o nome da Disciplina", "Não deixe em branco!"));
                        auxDisciplina.setNomeProfessor(ask.askText("Digite o nome do Professor:", "Não deixe em branco!"));
                        auxDisciplina.setNumFaltas(Integer.parseInt(ask.askTextOnlyNumbers("Digite a quantidade de Faltas", 
                                                                    "Digite somente Numeros!")));
                        auxDisciplina.setPeriodoLetivo(Integer.parseInt(ask.askTextOnlyNumbers("Digite o Periodo do Aluno:", 
                                                    "Digite apenas numeros!")));
                        //Notas
                        auxNotas = new float[2][4];
                        for(int i = 0; i < (int) auxNotas.length; i++){
                            for(int j = 0; j < (int) auxNotas[i].length; j++){
                                auxNotas[i][j] = ask.askFloatValue("Digite a nota " + ((int) j+1) + " da Unidade Tematica " + ((int) i+1), 
                                        "Digite apenas Numeros");
                            }
                        }
                        auxDisciplina.setNotasUT(auxNotas);
                        if(auxDisciplina.getMediaTotal() < (float) 7 && auxDisciplina.getMediaTotal() > (float) 2){
                            ask.showMsg("Segundo as notas digitadas, o estudante não passou, por favor informe a Nota da Prova Final do Estudante");
                            auxDisciplina.setProvaFinal(ask.askFloatValue("Digite a Nota da Prova Final:", "Digite apenas Numeros"));
                        }else{
                            auxDisciplina.setProvaFinal(0);
                        }
                        auxEstudante.setDisciplina(auxDisciplina);
                        org.addEstudante(auxEstudante);
                        auxEstudante = null;
                        auxDisciplina = null;
                    }else{
                        ask.showMsg("Usuário ja Cadastrado!");
                    }
                    auxStr = "";
                    break;
                case 1:
                    auxStr = ask.askText("Qual o nome do Estudante?", "Não deixe o campo vazio!");
                    if(org.removeEstudante(auxStr)){
                        ask.showMsg("Estudante Removido!");
                    }else{
                        ask.showMsg("Usuário não encontrado!");
                    }
                        
                    auxStr = "";
                    break;
                case 2:
                    tblEstudantes = new ViewEstudantes(org);
                    break;
                case 3:
                    auxStr = ask.askText("Qual o nome do Estudante?", "Não deixe o campo vazio!");
                    if(org.getEstudante(auxStr) != (Estudante) null){
                        ask.showMsg("Dados do Estudante Solicitado:\n\n" +
                                    "Nome: " + org.getEstudante(auxStr).getNome() + "\n" + 
                                    "Matricula: " + org.getEstudante(auxStr).getMatricula() + "\n" +
                                    "Idade: " + org.getEstudante(auxStr).getIdade() + "\n" +
                                    "Sexo: " + org.getEstudante(auxStr).getSexo() + "\n" +
                                    "Endereco: " + org.getEstudante(auxStr).getEndereco().getEnderecoCompleto() + "\n" +
                                    "Email: " + org.getEstudante(auxStr).getContato().getEmail() + "\n" +
                                    "Rede Social: " + org.getEstudante(auxStr).getContato().getRedeSocial() + "\n" +
                                    "Telefone: " + org.getEstudante(auxStr).getContato().getTelefone() + "\n" +
                                    "\nDados da Disciplina:\n\n" + 
                                    "Nome Disciplina: " + org.getEstudante(auxStr).getDisciplina().getNomeDisciplina() + "\n" +
                                    "Professor: " + org.getEstudante(auxStr).getDisciplina().getNomeProfessor() + "\n" +
                                    "Periodo: " + org.getEstudante(auxStr).getDisciplina().getPeriodoLetivo()+ "\n" +
                                    "Professor: " + org.getEstudante(auxStr).getDisciplina().getNomeProfessor() + "\n" +
                                    "Faltas: " + org.getEstudante(auxStr).getDisciplina().getNumFaltas() + "\n" +
                                    "Média Final: " + org.getEstudante(auxStr).getDisciplina().getMediaFinal() + "\n" +
                                    "Status: " + org.getEstudante(auxStr).getDisciplina().status() + "\n");
                    }else{
                        ask.showMsg("Usuário não encontrado!");
                    }
                        
                    auxStr = "";
                    break;
                case -1:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
    
}
