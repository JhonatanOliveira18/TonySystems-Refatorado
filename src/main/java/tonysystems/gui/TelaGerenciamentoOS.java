package tonysystems.gui;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tonysystems.dao.ManutencaoDAO;
import tonysystems.model.Manutencao;
import tonysystems.model.StatusOS;

public class TelaGerenciamentoOS extends javax.swing.JInternalFrame {

    private List<Manutencao> listaManutencoes;

    public TelaGerenciamentoOS() {
        initComponents();

        txtProblema.setEditable(false);
        jButton2.setEnabled(false);
    }

    // ---------> MÉTODOS <-----------
    private Manutencao obterManutencaoSelecionada() {

        int linhaVisual = jTable1.getSelectedRow();

        if (linhaVisual < 0 || listaManutencoes == null) {
            return null;
        }

        int linhaModelo = jTable1.convertRowIndexToModel(linhaVisual);

        int id = Integer.parseInt(
                jTable1.getModel().getValueAt(linhaModelo, 0).toString()
        );

        for (Manutencao manutencao : listaManutencoes) {
            if (manutencao.getId() != null
                    && manutencao.getId().equals(id)) {
                return manutencao;
            }
        }

        return null;
    }

    // ---------> AÇÕES <----------
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtProblema = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gerenciamento de OS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jLabel1.setText("Nome do cliente");

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jLabel2.setText("• Clique duas vezes sobre a linha para visualizar o problema relatado e poder prosseguir em gerenciar.");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Cliente", "TV", "Data Inicio", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("Problema relatado");

        txtProblema.setColumns(20);
        txtProblema.setRows(5);
        jScrollPane2.setViewportView(txtProblema);

        jButton2.setText("Gerenciar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)
                                    .addComponent(jButton1))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                                .addComponent(jScrollPane2)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nome = txtCliente.getText().trim();

        listaManutencoes = null;
        txtProblema.setText("");
        jButton2.setEnabled(false);

        DefaultTableModel model
                = (DefaultTableModel) jTable1.getModel();

        model.setRowCount(0);

        if (nome.isBlank()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Informe o nome do cliente!"
            );
            return;
        }

        try {
            ManutencaoDAO dao = new ManutencaoDAO();

            listaManutencoes = dao.buscarPorNome(nome);

            for (Manutencao m : listaManutencoes) {

                model.addRow(new Object[]{
                    m.getId(),
                    m.getTelevisao().getCliente().getNome(),
                    m.getTelevisao().getModelo(),
                    m.getDataInicio(),
                    m.getStatusOS()
                });
            }

        } catch (Exception e) {

            listaManutencoes = null;
            model.setRowCount(0);

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao pesquisar ordens de serviço: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() != 2) {
            return;
        }

        Manutencao manutencao = obterManutencaoSelecionada();

        if (manutencao == null) {
            txtProblema.setText("");
            jButton2.setEnabled(false);
            return;
        }

        txtProblema.setText(manutencao.getProblema());

        jButton2.setEnabled(
                manutencao.getStatusOS() != StatusOS.CONCLUIDO
        );
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Manutencao manutencao = obterManutencaoSelecionada();

        if (manutencao == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma ordem de serviço!"
            );
            return;
        }

        if (manutencao.getStatusOS() == StatusOS.CONCLUIDO) {
            JOptionPane.showMessageDialog(
                    this,
                    "Esta ordem de serviço já foi concluída!"
            );
            jButton2.setEnabled(false);
            return;
        }

        try {
            TelaFinalizacaoOS tela = new TelaFinalizacaoOS();

            tela.setMaximizable(true);
            tela.setClosable(true);

            tela.setManutencao(manutencao);

            getDesktopPane().add(tela);
            tela.setVisible(true);
            tela.toFront();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao abrir a finalização da OS: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextArea txtProblema;
    // End of variables declaration//GEN-END:variables
}
