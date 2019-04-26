package view;

import control.PrincipalControl;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;


public class TelaAjuda extends javax.swing.JInternalFrame {

    public static final int ordemFrame = 2;
    PrincipalControl control;
    JInternalFrame frame;

    public TelaAjuda() {
        initComponents();
    }

    public TelaAjuda(PrincipalControl aThis) {
        control = aThis;
        initComponents();
        frame = this;

          addInternalFrameListener(new InternalFrameListener() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                control.fecharAction(frame, ordemFrame);
            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
            }

        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfManualLink = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jtfManualLink.setText("Manual");
        jtfManualLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfManualLinkMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtfManualLinkMouseReleased(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Dica: Para qualquer ajuda, leia o manual (Acesso rápido: CTRL + F5)");
        jLabel2.setToolTipText("");

        jButton1.setText("Fechar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jtfManualLink))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(31, 31, 31)
                .addComponent(jtfManualLink)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfManualLinkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfManualLinkMouseReleased
        jtfManualLink.setBackground(Color.blue);
    }//GEN-LAST:event_jtfManualLinkMouseReleased

    private void jtfManualLinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfManualLinkMouseClicked
        control.acessarLink("C:/Users/johnatan.souza/Desktop/MercadoSeuQuinkas/src/manual/Manual.pdf");
    }//GEN-LAST:event_jtfManualLinkMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        control.fecharAction(this);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jtfManualLink;
    // End of variables declaration//GEN-END:variables
}
