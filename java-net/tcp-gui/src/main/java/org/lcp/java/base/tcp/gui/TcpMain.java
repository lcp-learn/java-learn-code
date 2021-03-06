/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lcp.java.base.tcp.gui;

import org.lcp.java.base.tcp.gui.modules.TcpSender;

/**
 * @author lichunpeng
 */
public class TcpMain extends javax.swing.JFrame {

  private static final long serialVersionUID = 8601121360806780308L;
  private TcpSender tcpSender;
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextField addressText;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JList<String> portList;
  private javax.swing.JButton sendBtn;
  private javax.swing.JButton setLocalBtn;

  /**
   * Creates new form TcpMain
   */
  public TcpMain() {
    initComponents();

    tcpSender = new TcpSender();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception ex) {
      java.util.logging.Logger.getLogger(TcpMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> new TcpMain().setVisible(true));
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    sendBtn = new javax.swing.JButton();
    addressText = new javax.swing.JTextField();
    setLocalBtn = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    portList = new javax.swing.JList<>();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setLocation(new java.awt.Point(800, 400));

    sendBtn.setText("send");
    sendBtn.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        sendBtnActionPerformed(evt);
      }
    });

    setLocalBtn.setText("set local");
    setLocalBtn.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        setLocalBtnActionPerformed(evt);
      }
    });

    portList.setModel(new javax.swing.AbstractListModel<String>() {
      private static final long serialVersionUID = -2607433817027151325L;
      String[] strings = {"9977", "9801", "9802", "9987"};

      @Override
      public int getSize() {
        return strings.length;
      }

      @Override
      public String getElementAt(int i) {
        return strings[i];
      }
    });
    portList.setRequestFocusEnabled(false);
    portList.setSelectedIndices(new int[]{3});
    jScrollPane1.setViewportView(portList);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        layout.createSequentialGroup().addGap(40, 40, 40).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
                    .addComponent(addressText, javax.swing.GroupLayout.PREFERRED_SIZE, 251,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(sendBtn)).addGroup(
                    layout.createSequentialGroup().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84,
                        javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(setLocalBtn)))
            .addContainerGap(58, Short.MAX_VALUE)));
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
        layout.createSequentialGroup().addGap(35, 35, 35).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(sendBtn)).addGap(22, 22, 22).addGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(setLocalBtn))
            .addContainerGap(57, Short.MAX_VALUE)));

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void sendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendBtnActionPerformed
    String address = this.addressText.getText();
    System.out.println("address = " + address);
    String[] addressDetail = address.split(":");
    String ip = addressDetail[0];
    int port = Integer.parseInt(addressDetail[1]);
    try {
      tcpSender.send(ip, port);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }//GEN-LAST:event_sendBtnActionPerformed

  private void setLocalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST
    // :event_setLocalBtnActionPerformed
    // TODO add your handling code here:
    String port = portList.getSelectedValue();
    String address = "127.0.0.1:" + port;
    addressText.setText(address);

  }//GEN-LAST:event_setLocalBtnActionPerformed
  // End of variables declaration//GEN-END:variables
}
