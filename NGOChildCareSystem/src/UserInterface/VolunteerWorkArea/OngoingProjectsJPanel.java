/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.VolunteerWorkArea;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.PostTaskWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nikita
 */
public class OngoingProjectsJPanel extends javax.swing.JPanel {

    /**
     * Creates new form OngoingProjectsJPanel
     */
    private JPanel userProcessContainer;
    private EcoSystem system;
    private UserAccount userAccount;
    private Organization organization;
    private Enterprise enterprise;

    public OngoingProjectsJPanel(JPanel userProcessContainer, EcoSystem system, UserAccount userAccount, Organization organization, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.system = system;
        this.userAccount = userAccount;
        this.organization = organization;
        this.enterprise = enterprise;
        populateOngoingProjectsTable();
    }

    public void populateOngoingProjectsTable() {
        DefaultTableModel model = (DefaultTableModel) tblOngoingProjects.getModel();
        model.setRowCount(0);
        for (WorkRequest request : organization.getWorkQueue().getWorkRequestList()) {
            if (request instanceof PostTaskWorkRequest) {
                Object[] row = new Object[5];
                row[0] = (PostTaskWorkRequest)request;
                row[1] = ((PostTaskWorkRequest) request).getTask();
                row[2] = request.getSender().getEmployee().getName();
                row[3] = request.getReceiver() == null ? null : request.getReceiver().getEmployee().getName();
                row[4] = request.getStatus();
                model.addRow(row);
            }
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

        btnAssign = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnProcess = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOngoingProjects = new javax.swing.JTable();
        lblHeader = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 204));
        setMaximumSize(new java.awt.Dimension(700, 700));
        setMinimumSize(new java.awt.Dimension(700, 700));
        setPreferredSize(new java.awt.Dimension(700, 700));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAssign.setText("Assign to me");
        btnAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignActionPerformed(evt);
            }
        });
        add(btnAssign, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, -1, -1));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnProcess.setText("Process");
        btnProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessActionPerformed(evt);
            }
        });
        add(btnProcess, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, 90, -1));

        tblOngoingProjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Project Name", "Task", "Sender", "Receiver", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblOngoingProjects);
        if (tblOngoingProjects.getColumnModel().getColumnCount() > 0) {
            tblOngoingProjects.getColumnModel().getColumn(0).setResizable(false);
            tblOngoingProjects.getColumnModel().getColumn(1).setResizable(false);
            tblOngoingProjects.getColumnModel().getColumn(2).setResizable(false);
            tblOngoingProjects.getColumnModel().getColumn(3).setResizable(false);
            tblOngoingProjects.getColumnModel().getColumn(4).setResizable(false);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 580, 230));

        lblHeader.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeader.setText("Ongoing Projects");
        add(lblHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 270, 26));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblOngoingProjects.getSelectedRow();
        
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a row","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        WorkRequest request = (WorkRequest)tblOngoingProjects.getValueAt(selectedRow, 0);
        request.setReceiver(userAccount);
        request.setStatus("Pending");
        populateOngoingProjectsTable();
    }//GEN-LAST:event_btnAssignActionPerformed

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblOngoingProjects.getSelectedRow();
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(null, "Please select a row","Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        PostTaskWorkRequest request = (PostTaskWorkRequest)tblOngoingProjects.getValueAt(selectedRow, 0);
        if(request.getStatus()=="Pending")
        {
        request.setStatus("Processing");
        
        ProcessWorkRequestJPanel processWorkRequestJPanel = new ProcessWorkRequestJPanel(userProcessContainer, request);
        userProcessContainer.add("processWorkRequestJPanel", processWorkRequestJPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
        }
        else {
            JOptionPane.showMessageDialog(null, "Please assign a task to yourself and then process","Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnProcessActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAssign;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnProcess;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JTable tblOngoingProjects;
    // End of variables declaration//GEN-END:variables
}
