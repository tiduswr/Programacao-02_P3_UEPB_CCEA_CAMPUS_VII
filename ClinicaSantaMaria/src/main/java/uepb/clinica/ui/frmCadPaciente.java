package uepb.clinica.ui;

import javax.swing.JDialog;
import javax.swing.JTextField;
import uepb.clinica.Contato;
import uepb.clinica.Endereco;
import uepb.clinica.Paciente;
import uepb.clinica.ProfissionalSaude;
import uepb.clinica.Servidor;

public class frmCadPaciente extends JDialog {
    
    private int retorno; // 1 Salvar; 2 Excluir
    private Paciente paciente;
    private Servidor org;
    private DialogUI ask;
    
    public frmCadPaciente(Paciente paciente, Servidor org) {
        this.initComponents();
        this.retorno = 0;
        this.paciente = paciente;
        this.org = org;
        this.ask = new DialogUI("Atenção!");
    }
    
    //Getter and Setter
    public int getRetorno() {
        return retorno;
    }
    private void setRetorno(int retorno) {
        this.retorno = retorno;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    private void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    private void setOrg(Servidor org){
        this.org = org;
    }
    public Servidor getOrg(){
        return org;
    }
    
    //Método Principal
    public void showForm(){
        Paciente p = this.getPaciente();
        
        if(p != null){
            this.txtbCPF.setText(p.getCpf());
            this.txtbNome.setText(p.getNome());
            if(p.getSexo() == 'M'){
                this.chkMasc.setSelected(true);
            }else{
                this.chkFem.setSelected(true);
            }
            this.txtbIdade.setText(String.valueOf(p.getIdade()));
            this.txtbRua.setText(p.getEndereco().getRua());
            this.txtbNumero.setText(String.valueOf(p.getEndereco().getNumero()));
            this.txtbBairro.setText(p.getEndereco().getBairro());
            this.txtbCidade.setText(p.getEndereco().getCidade());
            this.txtbEstado.setText(p.getEndereco().getEstado());
            this.txtbCelular.setText(p.getContato().getCelular());
            this.txtbEmail.setText(p.getContato().getEmail());
            this.txtbProbSaude.setText(p.getProblemaSaude());
            this.txtbPeso.setText(String.valueOf(p.getImc().getPeso()));
            this.txtbAltura.setText(String.valueOf(p.getImc().getAltura()));
        }     
        
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setTitle("Cadastrar/Editar Paciente");
        this.setVisible(true);
    }
    
    private boolean txtboxIsEmpty(JTextField t){
        if(t.getText() != null){
            if(t.getText().trim().isEmpty()){
                return true;
            }else{
                return false;
            }
        }else{
            return true;
        }
    }
    
    private boolean txtboxIsFloat(JTextField t){
        float aux;
        boolean retorno = true;
        
        try{
            aux = Float.parseFloat(t.getText());
        }catch(NumberFormatException e){
            retorno = false;
        }
        
        return retorno;
    }
    
    private void salvarDados(){
        if(this.txtboxIsEmpty(this.txtbCPF) || this.txtboxIsEmpty(this.txtbNome) ||
            this.txtboxIsEmpty(this.txtbIdade) || this.txtboxIsEmpty(this.txtbRua) ||    
            this.txtboxIsEmpty(this.txtbNumero) || this.txtboxIsEmpty(this.txtbBairro) ||    
            this.txtboxIsEmpty(this.txtbCidade) || this.txtboxIsEmpty(this.txtbEstado) || 
            this.txtboxIsEmpty(this.txtbProbSaude) || this.txtboxIsEmpty(this.txtbPeso) ||
            this.txtboxIsEmpty(this.txtbAltura) || (!this.chkFem.isSelected() && !this.chkMasc.isSelected()) ){
            ask.showMsg("Por favor, preencha todos os dados!");
        }else {
            if(!this.txtbIdade.getText().matches("[0-9]+") || !this.txtbNumero.getText().matches("[0-9]+")
                || !this.txtboxIsFloat(this.txtbAltura) || !this.txtboxIsFloat(this.txtbPeso) || !this.txtbCPF.getText().matches("[0-9]+")){
                ask.showMsg("Por favor, digite apenas numeros nos campos de CPF, Idade, Numero da Residencia, Altura e Peso!");
            }else{
                if(this.txtbCPF.getText().length() == 11){
                    if(this.getPaciente() == null){
                        this.setPaciente(new Paciente());
                    }
                    Paciente p = this.getPaciente();

                    p.setCpf(this.txtbCPF.getText());
                    p.setNome(this.txtbNome.getText());
                    p.setIdade(Integer.parseInt(this.txtbIdade.getText()));
                    if(this.chkFem.isSelected()){
                        p.setSexo('M');
                    }else{
                        p.setSexo('M');
                    }
                    p.setContato(new Contato(this.txtbEmail.getText(), this.txtbCelular.getText()));
                    p.setEndereco(new Endereco(this.txtbRua.getText(), Integer.parseInt(this.txtbNumero.getText()),
                                                this.txtbBairro.getText(), this.txtbCidade.getText(), this.txtbEstado.getText()));
                    p.setProblemaSaude(this.txtbProbSaude.getText());
                    p.setIMC(Float.parseFloat(this.txtbPeso.getText()), Float.parseFloat(this.txtbAltura.getText()));
                    this.setRetorno(1);
                    this.setVisible(false);
                }else{
                    ask.showMsg("O CPF deve conter 11 digitos!");
                }
            }
        }
    }
    
    private String buscaCPF(String cpf){
        Servidor o = this.getOrg();
        ProfissionalSaude find = o.getProfSaude(cpf);
        Paciente find2 = o.getPaciente(cpf);
        
        if(find != null){
            return find.getCpf();
        }else if(find2 != null){
            return find2.getCpf();
        }else{
            return null;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        lblTitleLine = new javax.swing.JLabel();
        lblCPF = new javax.swing.JLabel();
        txtbCPF = new javax.swing.JTextField();
        txtbNome = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        chkMasc = new javax.swing.JCheckBox();
        chkFem = new javax.swing.JCheckBox();
        txtbIdade = new javax.swing.JTextField();
        lblIdade = new javax.swing.JLabel();
        lblCelular = new javax.swing.JLabel();
        txtbCelular = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtbEmail = new javax.swing.JTextField();
        lblSubTitle1 = new javax.swing.JLabel();
        lblSubTitle2 = new javax.swing.JLabel();
        lblRua = new javax.swing.JLabel();
        txtbRua = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        txtbNumero = new javax.swing.JTextField();
        lblBairro = new javax.swing.JLabel();
        txtbBairro = new javax.swing.JTextField();
        txtbCidade = new javax.swing.JTextField();
        lblCidade = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtbEstado = new javax.swing.JTextField();
        lblSubTitle3 = new javax.swing.JLabel();
        btExclui = new javax.swing.JButton();
        btSalva = new javax.swing.JButton();
        lblProbSaude = new javax.swing.JLabel();
        txtbProbSaude = new javax.swing.JTextField();
        txtbPeso = new javax.swing.JTextField();
        lblPeso = new javax.swing.JLabel();
        txtbAltura = new javax.swing.JTextField();
        lblAltura = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 0));
        lblTitle.setText("CADASTRAR PACIENTE");

        lblTitleLine.setBackground(new java.awt.Color(0, 0, 0));
        lblTitleLine.setOpaque(true);

        lblCPF.setForeground(new java.awt.Color(0, 0, 0));
        lblCPF.setText("CPF:");

        lblNome.setForeground(new java.awt.Color(0, 0, 0));
        lblNome.setText("Nome:");

        chkMasc.setForeground(new java.awt.Color(0, 0, 0));
        chkMasc.setText("Masculino");
        chkMasc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMascActionPerformed(evt);
            }
        });

        chkFem.setForeground(new java.awt.Color(0, 0, 0));
        chkFem.setText("Feminino");
        chkFem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFemActionPerformed(evt);
            }
        });

        lblIdade.setForeground(new java.awt.Color(0, 0, 0));
        lblIdade.setText("Idade:");

        lblCelular.setForeground(new java.awt.Color(0, 0, 0));
        lblCelular.setText("Celular:");

        lblEmail.setForeground(new java.awt.Color(0, 0, 0));
        lblEmail.setText("E-Mail:");

        lblSubTitle1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblSubTitle1.setForeground(new java.awt.Color(0, 0, 0));
        lblSubTitle1.setText("Dados Pessoais:");

        lblSubTitle2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblSubTitle2.setForeground(new java.awt.Color(0, 0, 0));
        lblSubTitle2.setText("Endereço:");

        lblRua.setForeground(new java.awt.Color(0, 0, 0));
        lblRua.setText("Rua:");

        lblNumero.setForeground(new java.awt.Color(0, 0, 0));
        lblNumero.setText("Numero:");

        lblBairro.setForeground(new java.awt.Color(0, 0, 0));
        lblBairro.setText("Bairro:");

        lblCidade.setForeground(new java.awt.Color(0, 0, 0));
        lblCidade.setText("Cidade:");

        lblEstado.setForeground(new java.awt.Color(0, 0, 0));
        lblEstado.setText("Sigla Estado");

        lblSubTitle3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblSubTitle3.setForeground(new java.awt.Color(0, 0, 0));
        lblSubTitle3.setText("Dados adicionais do Paciente:");

        btExclui.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btExclui.setForeground(new java.awt.Color(0, 0, 0));
        btExclui.setText("Excluir");
        btExclui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluiActionPerformed(evt);
            }
        });

        btSalva.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btSalva.setForeground(new java.awt.Color(0, 0, 0));
        btSalva.setText("SALVAR");
        btSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvaActionPerformed(evt);
            }
        });

        lblProbSaude.setForeground(new java.awt.Color(0, 0, 0));
        lblProbSaude.setText("Problema de Saúde:");

        lblPeso.setForeground(new java.awt.Color(0, 0, 0));
        lblPeso.setText("Peso:");

        lblAltura.setForeground(new java.awt.Color(0, 0, 0));
        lblAltura.setText("Altura:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitleLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRua)
                        .addGap(18, 18, 18)
                        .addComponent(txtbRua, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lblNumero)
                        .addGap(27, 27, 27)
                        .addComponent(txtbNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btExclui, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBairro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbCidade)
                        .addGap(6, 6, 6)
                        .addComponent(lblEstado)
                        .addGap(2, 2, 2)
                        .addComponent(txtbEstado))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCelular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEmail))
                            .addComponent(lblTitle)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(chkMasc)
                                .addGap(18, 18, 18)
                                .addComponent(chkFem)
                                .addGap(18, 18, 18)
                                .addComponent(lblIdade))
                            .addComponent(lblSubTitle1)
                            .addComponent(lblSubTitle2)
                            .addComponent(lblSubTitle3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCPF)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbCPF)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNome)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbNome)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtbIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtbEmail)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblProbSaude)
                        .addGap(18, 18, 18)
                        .addComponent(txtbProbSaude))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPeso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAltura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitleLine, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubTitle1)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCPF)
                    .addComponent(txtbCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome)
                    .addComponent(txtbNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkMasc)
                    .addComponent(chkFem)
                    .addComponent(lblIdade)
                    .addComponent(txtbIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCelular)
                    .addComponent(txtbCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail)
                    .addComponent(txtbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblSubTitle2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRua)
                        .addComponent(txtbRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtbNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBairro)
                    .addComponent(txtbBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCidade)
                    .addComponent(txtbCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstado)
                    .addComponent(txtbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblSubTitle3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProbSaude)
                    .addComponent(txtbProbSaude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAltura)
                        .addComponent(txtbAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPeso)
                        .addComponent(txtbPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btExclui, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Eventos
    
    private void chkMascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMascActionPerformed
        if(this.chkFem.isSelected()){
            this.chkFem.setSelected(false);
        }
    }//GEN-LAST:event_chkMascActionPerformed

    private void chkFemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFemActionPerformed
        if(this.chkMasc.isSelected()){
            this.chkMasc.setSelected(false);
        }
    }//GEN-LAST:event_chkFemActionPerformed

    private void btSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvaActionPerformed
       
        Paciente active = this.getPaciente();
        String find = this.buscaCPF(this.txtbCPF.getText());
        
        if(find != null){
            if(active != null){
                if(active.getCpf().equalsIgnoreCase(find)){
                    this.salvarDados();
                }else{
                    ask.showMsg("Erro, esse CPF ja esta cadastrado em outra pessoa!");
                }
            }else{
                ask.showMsg("Erro, esse CPF ja esta cadastrado em outra pessoa!");
            }
        }else{
            this.salvarDados();
        }
    }//GEN-LAST:event_btSalvaActionPerformed

    private void btExcluiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluiActionPerformed
        this.setRetorno(2);
        this.setVisible(false);
    }//GEN-LAST:event_btExcluiActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExclui;
    private javax.swing.JButton btSalva;
    private javax.swing.JCheckBox chkFem;
    private javax.swing.JCheckBox chkMasc;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblAltura;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblIdade;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblProbSaude;
    private javax.swing.JLabel lblRua;
    private javax.swing.JLabel lblSubTitle1;
    private javax.swing.JLabel lblSubTitle2;
    private javax.swing.JLabel lblSubTitle3;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleLine;
    private javax.swing.JTextField txtbAltura;
    private javax.swing.JTextField txtbBairro;
    private javax.swing.JTextField txtbCPF;
    private javax.swing.JTextField txtbCelular;
    private javax.swing.JTextField txtbCidade;
    private javax.swing.JTextField txtbEmail;
    private javax.swing.JTextField txtbEstado;
    private javax.swing.JTextField txtbIdade;
    private javax.swing.JTextField txtbNome;
    private javax.swing.JTextField txtbNumero;
    private javax.swing.JTextField txtbPeso;
    private javax.swing.JTextField txtbProbSaude;
    private javax.swing.JTextField txtbRua;
    // End of variables declaration//GEN-END:variables
}
