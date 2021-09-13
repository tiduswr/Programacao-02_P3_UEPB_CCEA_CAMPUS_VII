package uepb.educad.UI;

import uepb.educad.Universidade;

import javax.swing.table.DefaultTableModel;


public class ViewEstudantes extends javax.swing.JDialog {

    public ViewEstudantes(Universidade dataModel) {
        initComponents();
        
        this.setModal(true);
        final String[] headers = {"Matricula", "Nome", "Disciplina", "Periodo", "Média", "Situação"};
        int totEstudantes = dataModel.getEstudantes().size();
        Object[][] src = new Object[totEstudantes][6];
        
        //Cria Objeto contendo o conteudo dos dados do estudante para vizualização
        for(int i = 0; i < totEstudantes; i++){
            src[i][0] = dataModel.getEstudantes().get(i).getMatricula();
            src[i][1] = dataModel.getEstudantes().get(i).getNome();
            src[i][2] = dataModel.getEstudantes().get(i).getDisciplina().getNomeDisciplina();
            src[i][3] = dataModel.getEstudantes().get(i).getDisciplina().getPeriodoLetivo();
            src[i][4] = dataModel.getEstudantes().get(i).getDisciplina().getMediaFinal();
            src[i][5] = dataModel.getEstudantes().get(i).getDisciplina().status();
        }
        
        this.tblEstudantes.setModel(new DefaultTableModel(src,headers));
        
        //Seta labels das informações da Universidade
        this.lblNomeUniversidade.setText("Universidade: " + dataModel.getNome());
        this.lblNomeCampus.setText("Campus: " + dataModel.getCampus());
        this.lblEndereco.setText("Endereco: " + dataModel.getEndereco().getEnderecoCompleto());
        this.lblEmail.setText("Email: " + dataModel.getContato().getEmail());
        this.lblTelefone.setText("Telefone: " + dataModel.getContato().getTelefone());
        this.lblRedeSocial.setText("Rede Social: " + dataModel.getContato().getRedeSocial());
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstudantes = new javax.swing.JTable();
        lblNomeUniversidade = new javax.swing.JLabel();
        lblNomeCampus = new javax.swing.JLabel();
        lblEndereco = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblRedeSocial = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TABELA DE ESTUDANTES");

        tblEstudantes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblEstudantes);

        lblNomeUniversidade.setText("NomeUniversidade");

        lblNomeCampus.setText("NomeCampus");

        lblEndereco.setText("EnderecoCompleto");

        lblTelefone.setText("Telefone");

        lblEmail.setText("Email");

        lblRedeSocial.setText("RedeSocial");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                    .addComponent(lblTelefone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRedeSocial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNomeUniversidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNomeCampus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNomeUniversidade, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNomeCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRedeSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblNomeCampus;
    private javax.swing.JLabel lblNomeUniversidade;
    private javax.swing.JLabel lblRedeSocial;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JTable tblEstudantes;
    // End of variables declaration//GEN-END:variables
}
