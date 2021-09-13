package com.uepb.hospital.ui;

import com.uepb.hospital.DataBase;
import com.uepb.hospital.Paciente;
import com.uepb.hospital.ProfissionalSaude;
import com.uepb.hospital.Prontuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class frmProntuarioEnfermagem extends JDialog {
    private DataBase db;
    private DefaultTableModel dm;
    private ProfissionalSaude prof;
    private Paciente emAtendimento;
    
    public frmProntuarioEnfermagem(DataBase db, ProfissionalSaude p) {
        if(p != null && p.getCpf() != null){
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
    
    public void inicializarComponentes(){
        this.initComponents();
        
        //Caixas de seleção
        this.slctRed.setActionCommand("1");
        this.slctYellow.setActionCommand("2");
        this.slctGreen.setActionCommand("3");
        this.slctBlue.setActionCommand("4");
        
        //Configurações da Janela
        this.setTitle("Classificar paciente");
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
    
    private void fillTable(){
        if(this.db.getFilaClassificacao() != null){
            Paciente a[] = new Paciente[this.db.getFilaClassificacao().size()];
            a = this.db.getFilaClassificacao().toArray(a);
            int size = a.length;

            Object[][] src = new Object[size][3];

            for(int i = 0; i < size; i++){
                src[i][0] = i + 1;
                src[i][1] = a[i].getCpf();
                src[i][2] = a[i].getNome();
            }

            String[] header = new String[] {"Posição", "CPF", "Nome"};

            this.dm = new DefaultTableModel(src, header);
            this.tblEspera.setModel(dm);
        }
    }
    
    private void clearFields(){
        this.txtbDataEntrada.setText("");
        this.btGpClassificacao.clearSelection();
    }
    
    private boolean fieldsAreFilled(){
       return !txtbDataEntrada.getText().isEmpty() && btGpClassificacao.getSelection() != null;
    }
    
    private boolean chamar(){
        if(this.emAtendimento != null){
            this.showMsg("Ja existe uma pessoa em atendimento!");
            return false;
        }else{
            
            Paciente p = this.db.getFilaClassificacao().poll();
            
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
    
    private boolean registrarClassificacao(){
        Paciente aux = this.emAtendimento;
        if(aux != null){
            //Pega a tabela para trabalhar os dados
            DefaultTableModel dataModel = (DefaultTableModel) this.tblEspera.getModel();
            
            if(dataModel.getRowCount() > 0 || this.emAtendimento != null){
                
                //Primeiro verifica se a data é valida
                Prontuario prt = new Prontuario();
                SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
                try{
                    prt.setEntrada(f.parse(this.txtbDataEntrada.getText()));
                }catch(ParseException e){
                    this.showMsg("Por favor, digite uma data Válida");
                    return false;
                }
                
                //Caso a data esteja certa, continua o cadastro
                prt.setCpf(aux.getCpf());
                aux.setPrioridade(Integer.parseInt(this.btGpClassificacao.getSelection().getActionCommand()));
                prt.setCpfProfSaude(this.prof.getCpf());
                
                //Cadastra atendimento e repassa para fila do medico
                this.db.getAtendimentos().add(this.db.gerarIdAtendimento(),prt);
                aux.iniciaContador();
                this.db.getFilaMedico().add(aux);
                
                this.lblCpfValue.setText("");
                this.lblNomeValue.setText("");
                this.fillTable();
                this.emAtendimento = null;
                return true;
            }else{
                this.showMsg("Nenhum Paciente na Fila");
                return false;
            }
        }
        this.showMsg("Chame um paciente para o atendimento!");
        return false;
    }
    
    //Mensageiro
    private void showMsg(String message){
        JOptionPane.showMessageDialog(this, message, "Cadastro de Pessoas", JOptionPane.DEFAULT_OPTION);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btGpClassificacao = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEspera = new javax.swing.JTable();
        btRegistra = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblDtEntrada = new javax.swing.JLabel();
        lblClassificacao = new javax.swing.JLabel();
        txtbDataEntrada = new javax.swing.JTextField();
        slctRed = new javax.swing.JRadioButton();
        slctYellow = new javax.swing.JRadioButton();
        slctGreen = new javax.swing.JRadioButton();
        slctBlue = new javax.swing.JRadioButton();
        btExplicacao = new javax.swing.JToggleButton();
        lblFila = new javax.swing.JLabel();
        lblCpfValue = new javax.swing.JLabel();
        lblCpf = new javax.swing.JLabel();
        lblNomeValue = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblEmAtendimento1 = new javax.swing.JLabel();
        btChamar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
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

        btRegistra.setText("Registrar");
        btRegistra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegistraActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTitle.setText("Classificar Paciente");

        lblDtEntrada.setText("Data de Entrada:");

        lblClassificacao.setText("Classificação:");

        btGpClassificacao.add(slctRed);
        slctRed.setForeground(new java.awt.Color(204, 0, 0));
        slctRed.setText("Vermelho");

        btGpClassificacao.add(slctYellow);
        slctYellow.setForeground(new java.awt.Color(153, 153, 0));
        slctYellow.setText("Amarelo");

        btGpClassificacao.add(slctGreen);
        slctGreen.setForeground(new java.awt.Color(0, 204, 0));
        slctGreen.setText("Verde");

        btGpClassificacao.add(slctBlue);
        slctBlue.setForeground(new java.awt.Color(0, 0, 255));
        slctBlue.setText("Azul");

        btExplicacao.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        btExplicacao.setText("?");
        btExplicacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExplicacaoActionPerformed(evt);
            }
        });

        lblFila.setText("Fila de Espera:");

        lblCpf.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblCpf.setText("CPF:");

        lblNome.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNome.setText("Nome:");

        lblEmAtendimento1.setText("Em Atendimento:");

        btChamar.setText("Chamar");
        btChamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btChamarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDtEntrada)
                            .addComponent(slctRed)
                            .addComponent(slctYellow))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbDataEntrada)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(slctBlue)
                                    .addComponent(slctGreen))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCpf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCpfValue, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNomeValue, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitle)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblClassificacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btExplicacao))
                            .addComponent(lblFila)
                            .addComponent(lblEmAtendimento1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btChamar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btRegistra, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDtEntrada)
                    .addComponent(txtbDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btExplicacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblClassificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(slctRed)
                    .addComponent(slctGreen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(slctYellow)
                    .addComponent(slctBlue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmAtendimento1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNome)
                        .addComponent(lblNomeValue, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCpf)
                        .addComponent(lblCpfValue, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)))
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

    private void btRegistraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegistraActionPerformed
        if(this.fieldsAreFilled()){
            this.registrarClassificacao();
            this.clearFields();
        }else{
            this.showMsg("Por favor, preencha todos os dados do Formulario!");
        }
    }//GEN-LAST:event_btRegistraActionPerformed

    private void btExplicacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExplicacaoActionPerformed
        String msg = "1. Vermelho (o atendimento é imediato); \n" +
                     "2. Amarelo (o atendimento deve acontecer em até 60 minutos); \n" +
                     "3. Verde (o atendimento deve acontecer em até 2 horas); \n" +
                     "4. Azul (Não é urgente, o atendimento pode acontecer em até 4 horas); \n";
        this.showMsg(msg);
    }//GEN-LAST:event_btExplicacaoActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void btChamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btChamarActionPerformed
        this.chamar();
    }//GEN-LAST:event_btChamarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(this.emAtendimento != null){
            this.setVisible(true);
            this.showMsg("Existe uma pessoa em atendimento, registre antes de sair!");
        }else{
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btChamar;
    private javax.swing.JToggleButton btExplicacao;
    private javax.swing.ButtonGroup btGpClassificacao;
    private javax.swing.JButton btRegistra;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClassificacao;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblCpfValue;
    private javax.swing.JLabel lblDtEntrada;
    private javax.swing.JLabel lblEmAtendimento1;
    private javax.swing.JLabel lblFila;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNomeValue;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JRadioButton slctBlue;
    private javax.swing.JRadioButton slctGreen;
    private javax.swing.JRadioButton slctRed;
    private javax.swing.JRadioButton slctYellow;
    private javax.swing.JTable tblEspera;
    private javax.swing.JTextField txtbDataEntrada;
    // End of variables declaration//GEN-END:variables
}
