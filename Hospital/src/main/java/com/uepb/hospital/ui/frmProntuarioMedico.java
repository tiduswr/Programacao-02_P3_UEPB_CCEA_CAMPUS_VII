package com.uepb.hospital.ui;

import com.uepb.hospital.DataBase;
import com.uepb.hospital.Paciente;
import com.uepb.hospital.ProfissionalSaude;
import com.uepb.hospital.Prontuario;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class frmProntuarioMedico extends javax.swing.JDialog {

    private ProfissionalSaude prof;
    private DataBase db;
    private Paciente emAtendimento;
    private DefaultTableModel dm;
    
    public frmProntuarioMedico(DataBase db, ProfissionalSaude p) {
        if(db != null && p != null){
            this.db = db;
            this.prof = p;
            this.emAtendimento = null;
            
            //Desenha Formulario na Thread do EDT
            final CountDownLatch latch = new CountDownLatch(1);        
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                    inicializarComponentes();
                    fillTable();
                    latch.countDown();
                }
            });
            try{
                latch.await();
            }catch(InterruptedException e){}
        }
    }
    
    public void showForm(){
        //Desenha Formulario na Thread do EDT
        final CountDownLatch latch = new CountDownLatch(1);        
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                setVisible(true);
                latch.countDown();
            }
        });
        try{
            latch.await();
        }catch(InterruptedException e){}
    }
    
    private void clearFields(){
        this.txtbProbSaude.setText("");
        this.btGpSituacao.clearSelection();
    }
    
    private boolean fieldsAreFilled(){
        if(!this.txtbProbSaude.getText().isEmpty() && this.btGpSituacao.getSelection() != null){
            return true;
        }else{
            return false;
        }
    }
    
    private void fillTable(){
        if(this.db.getFilaMedico() != null){
            Paciente atend[] = new Paciente[this.db.getFilaMedico().size()];
            atend = this.db.getFilaMedico().toArray(atend);
            int tamanho = this.db.getFilaMedico().size();
            
            Object[][] src = new Object[tamanho][3];
        
            for(int i = 0; i < tamanho; i++){
                src[i][0] = i + 1;
                src[i][1] = atend[i].getCpf();
                src[i][2] = atend[i].getNome();
            }
            
            String [] headers = new String[] {"Posição", "CPF", "Nome"};
        
            this.dm = new DefaultTableModel(src, headers);
            this.tblEspera.setModel(this.dm);
        }
    }
    
    private boolean chamar(){
        if(this.emAtendimento != null){
            this.showMsg("Ja existe um Paciente em atendimento!");
            return false;
        }else{
            Paciente p = this.db.getFilaMedico().poll();
            
            if(p != null){
                this.lblCpfValue.setText(p.getCpf());
                this.lblNomeValue.setText(p.getNome());
                this.emAtendimento = p;
                this.fillTable();
                return true;
            }else{
                this.showMsg("Niguem esta na fila!");
                return false;
            }
        }
    }
    
    private void inicializarComponentes(){
        this.initComponents();
        
        //Seta caixas de seleção para valores padrão da Classe Prontuario
        this.chkAlta.setActionCommand("0");
        this.chkInternar.setActionCommand("1");
        
        //Configuraões da janela
        this.setTitle("Avaliar Paciente");
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
    
    private boolean registrarClassificacao(){
        Paciente p = this.emAtendimento;
        
        if(p != null){
            DefaultTableModel dtModel = (DefaultTableModel) this.tblEspera.getModel();
            
            if(dtModel.getRowCount() > 0  || this.emAtendimento != null){
                
                //Pega o prontuario do paciente da fila
                ArrayList<Prontuario> prts = this.db.getAtendimentos().getArrayList();
                Prontuario prt = null;
                
                for(Prontuario i : prts){
                    if(i.getCpf().equalsIgnoreCase(p.getCpf())){
                        prt = i;
                        break;
                    }
                }
                
                if(prt != null){
                    //Continua o cadastro
                    prt.setCpfMedico(this.prof.getCpf());
                    prt.setProblemaSaude(this.txtbProbSaude.getText());
                    int situacao = Integer.parseInt(this.btGpSituacao.getSelection().getActionCommand());
                    
                    if(situacao == 1){
                        prt.setInternado(2);
                    }else if(situacao == 0){
                        prt.setInternado(1);
                    }
                    prt.setSaida(LocalDateTime.now());
                    
                    this.lblCpfValue.setText("");
                    this.lblNomeValue.setText("");
                    this.emAtendimento = null;
                    this.fillTable();
                    return true;
                }else{
                    this.showMsg("Paciente não encontrado!");
                    return false;
                }
                
            }else{
                this.showMsg("Ninguem esta na fila!");
                return false;
            }
            
        }else{
            this.showMsg("Por favor, chame um paciente antes de tentar registrar um atendimento!");
            return false;
        }
              
    }
    
    //Mensageiro
    private void showMsg(String message){
        JOptionPane.showMessageDialog(this, message, "Cadastro de Pessoas", JOptionPane.DEFAULT_OPTION);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btGpSituacao = new javax.swing.ButtonGroup();
        lblTitle = new javax.swing.JLabel();
        lblProbSaude = new javax.swing.JLabel();
        lblSitPaciente = new javax.swing.JLabel();
        txtbProbSaude = new javax.swing.JTextField();
        chkAlta = new javax.swing.JRadioButton();
        chkInternar = new javax.swing.JRadioButton();
        lblNomeValue = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblEmAtendimento1 = new javax.swing.JLabel();
        btChamar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEspera = new javax.swing.JTable();
        lblFila = new javax.swing.JLabel();
        btRegistra = new javax.swing.JButton();
        lblCpfValue = new javax.swing.JLabel();
        lblCpf = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTitle.setText("Avaliar Paciente:");

        lblProbSaude.setText("Problema de Saude:");

        lblSitPaciente.setText("Situação do Paciente:");

        btGpSituacao.add(chkAlta);
        chkAlta.setText("Alta");

        btGpSituacao.add(chkInternar);
        chkInternar.setText("Internar");

        lblNome.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNome.setText("Nome:");

        lblEmAtendimento1.setText("Em Atendimento:");

        btChamar.setText("Chamar");
        btChamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btChamarActionPerformed(evt);
            }
        });

        tblEspera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblEspera);

        lblFila.setText("Fila de Espera:");

        btRegistra.setText("Registrar");
        btRegistra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegistraActionPerformed(evt);
            }
        });

        lblCpf.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblCpf.setText("CPF:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblProbSaude)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbProbSaude))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCpf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCpfValue, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNomeValue, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btChamar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btRegistra, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitle)
                            .addComponent(lblSitPaciente)
                            .addComponent(chkAlta)
                            .addComponent(chkInternar)
                            .addComponent(lblFila)
                            .addComponent(lblEmAtendimento1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProbSaude)
                    .addComponent(txtbProbSaude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSitPaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkAlta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkInternar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmAtendimento1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNome)
                        .addComponent(lblNomeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCpf)
                        .addComponent(lblCpfValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(13, 13, 13)
                .addComponent(lblFila, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btRegistra, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btChamar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btChamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btChamarActionPerformed
        this.chamar();
    }//GEN-LAST:event_btChamarActionPerformed

    private void btRegistraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegistraActionPerformed
        if(this.fieldsAreFilled()){
            this.registrarClassificacao();
            this.clearFields();
        }else{
            this.showMsg("Por favor, preencha todos os dados do Formulario!");
        }
    }//GEN-LAST:event_btRegistraActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(this.emAtendimento != null){
            this.showMsg("Existe uma pessoa em atendimento, termine o cadastro antes de Fechar!");
        }else{
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btChamar;
    private javax.swing.ButtonGroup btGpSituacao;
    private javax.swing.JButton btRegistra;
    private javax.swing.JRadioButton chkAlta;
    private javax.swing.JRadioButton chkInternar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblCpfValue;
    private javax.swing.JLabel lblEmAtendimento1;
    private javax.swing.JLabel lblFila;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNomeValue;
    private javax.swing.JLabel lblProbSaude;
    private javax.swing.JLabel lblSitPaciente;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblEspera;
    private javax.swing.JTextField txtbProbSaude;
    // End of variables declaration//GEN-END:variables
}
