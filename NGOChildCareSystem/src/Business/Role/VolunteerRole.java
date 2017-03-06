/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import UserInterface.VolunteerWorkArea.VolunteerHomeJPanel;
import javax.swing.JPanel;

/**
 *
 * @author Ekta
 */
public class VolunteerRole extends Role {

    public VolunteerRole(){
        super(Role.RoleType.Volunteer.getValue());
    }
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, EcoSystem business, DB4OUtil dB4OUtil, UserAccount userAccount, Organization organization, Enterprise enterprise) {
        return new VolunteerHomeJPanel(userProcessContainer, business, dB4OUtil, userAccount, organization, enterprise);
    }
    
}
