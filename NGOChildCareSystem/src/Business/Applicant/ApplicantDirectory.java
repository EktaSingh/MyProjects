/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Applicant;

import java.util.ArrayList;

/**
 *
 * @author Ekta
 */
public class ApplicantDirectory {
    
    private ArrayList<Applicant> applicantList;

    public ApplicantDirectory() {
        this.applicantList = new ArrayList<>();
    }

    public ArrayList<Applicant> getApplicantList() {
        return applicantList;
    }

    public void setApplicantList(ArrayList<Applicant> ApplicantList) {
        this.applicantList = ApplicantList;
    }
    
     public Applicant createApplicant(String name){
        Applicant applicant = new Applicant();
        applicant.setName(name);
        applicantList.add(applicant);
        return applicant;
    }
    
    public void removeApplicant (Applicant applicant){
        applicantList.remove(applicant);
    }
    
    
}
