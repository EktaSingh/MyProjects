/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.GuestWorkArea;

import Business.Applicant.Applicant;
import Business.Applicant.ApplicantDirectory;
import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Job.Job;
import Business.Organization.AdminOrganization;
import Business.Organization.Organization;
import Business.Role.ApplicantRole;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.JobWorkRequest;
import UserInterface.ApplicantWorkArea.ApplicantHomeJPanel;
import java.awt.CardLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author sachd
 */
public class SignUpJPanel extends javax.swing.JPanel {

    /**
     * Creates new form SignUpJPanel
     */
    private JPanel userProcessContainer;
    private EcoSystem system;
    private Job job;
    private Enterprise enterprise;
    private DB4OUtil dB4OUtil;

    public SignUpJPanel(JPanel userProcessContainer, EcoSystem system, DB4OUtil dB4OUtil, Enterprise enterprise, Job job) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.system = system;
        this.job = job;
        this.enterprise = enterprise;
        this.dB4OUtil = dB4OUtil;
//        this.userAccount = userAccount;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHeader = new javax.swing.JLabel();
        btnAddAccount = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtUserName = new javax.swing.JTextField();
        lblUserName = new javax.swing.JLabel();
        lblPassword1 = new javax.swing.JLabel();
        lblUserName1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        passFld = new javax.swing.JPasswordField();
        confirmPassFld = new javax.swing.JPasswordField();
        lblPassword2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 204));
        setMaximumSize(new java.awt.Dimension(700, 700));
        setMinimumSize(new java.awt.Dimension(700, 700));
        setPreferredSize(new java.awt.Dimension(700, 700));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHeader.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeader.setText("Sign Up");
        add(lblHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 290, 26));

        btnAddAccount.setText("Add Account");
        btnAddAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAccountActionPerformed(evt);
            }
        });
        add(btnAddAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, -1, -1));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 150, -1));

        lblUserName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUserName.setText("User Name");
        add(lblUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 90, -1));

        lblPassword1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPassword1.setText("Confirm Password");
        add(lblPassword1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 140, -1));

        lblUserName1.setText("Name");
        add(lblUserName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, -1));
        add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 150, -1));
        add(passFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 150, -1));
        add(confirmPassFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 150, -1));

        lblPassword2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPassword2.setText("Password");
        add(lblPassword2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 90, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAccountActionPerformed
        // TODO add your handling code here:

        try {
//            enterprise = (Enterprise) cbEnterpriseType.getSelectedItem();

            String name = txtName.getText();
            String userName = txtUserName.getText();

            //Getting the password
            char[] passwordCharArray = passFld.getPassword();
            String password = String.valueOf(passwordCharArray);

            char[] confirmPasswordCharArray = confirmPassFld.getPassword();
            String confirmPassword = String.valueOf(confirmPasswordCharArray);
            if (userName.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter values for user name", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (password.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter values for password", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (confirmPassword.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter values for confirm password", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // validate username
            String regex = "^[a-z A-Z]+$";
            Pattern namePattern = Pattern.compile(regex);
            Matcher nameMatcher = namePattern.matcher(userName);

            if (!nameMatcher.matches()) {
                JOptionPane.showMessageDialog(null, "Invalid characters in user name", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // check if password and confirmPassword are same
            if (!(password.equals(confirmPassword))) {
                JOptionPane.showMessageDialog(null, "Please enter same password in both password fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // check if password length is between 4-10 or give error
            if (password.length() > 10) {
                JOptionPane.showMessageDialog(null, "Password should not have more than 10 characters", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (password.length() < 4) {
                JOptionPane.showMessageDialog(null, "Password should have 4 to 10 characters", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (system.getUserAccountDirectory().checkIfUsernameIsUnique(userName)) {
                UserAccount userAccount = system.getUserAccountDirectory().createUserAccount(userName, password, null, new ApplicantRole());
                Applicant applicant = system.getApplicantDirectory().createApplicant(name);
                userAccount.setApplicant(applicant);
                JOptionPane.showMessageDialog(null, "User account added successfully", "Information", JOptionPane.INFORMATION_MESSAGE);

                int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to apply for the job?", "Information", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    sendWorkRequest(userAccount);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username already exists. Kindly use another username", "Error", JOptionPane.ERROR_MESSAGE);
            }
//                           }
//                        }
//                   }
//                }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unhandled Exception", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAddAccountActionPerformed

    private void sendWorkRequest(UserAccount userAccount) {

        JobWorkRequest request = new JobWorkRequest();

        request.setSender(userAccount);
        request.setJob(job);
        request.setStatus("Sent");

        Organization org = null;
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof AdminOrganization) {
                org = organization;
                break;
            }
        }
        if (org != null) {
            org.getWorkQueue().getWorkRequestList().add(request);
            userAccount.getWorkQueue().getWorkRequestList().add(request);
        }

        JOptionPane.showMessageDialog(null, "Job applied Successfully", "Information", JOptionPane.INFORMATION_MESSAGE);

        CardLayout cardLayout = (CardLayout) userProcessContainer.getLayout();
        ApplicantHomeJPanel ahjp = new ApplicantHomeJPanel(userProcessContainer, system, dB4OUtil, userAccount);
        userProcessContainer.add("ahjp", ahjp);
        cardLayout.next(userProcessContainer);

    }


    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAccount;
    private javax.swing.JButton btnBack;
    private javax.swing.JPasswordField confirmPassFld;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblPassword1;
    private javax.swing.JLabel lblPassword2;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblUserName1;
    private javax.swing.JPasswordField passFld;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
