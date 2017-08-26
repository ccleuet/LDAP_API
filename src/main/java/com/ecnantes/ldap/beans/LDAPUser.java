/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecnantes.ldap.beans;

/**
 *
 * @author Christophe CLEUET
 */
public class LDAPUser {
    
    private String uid;
    private String cn;
    private String sn;
    private String mail;
    private String CentraleEtuCursus;
    private String CentraleEtuCycle;
    private String CentraleEtuFormation;
    private String CentraleEtuDateNaissance;
    private String CentraleEtuGroupe;
    private String CentraleEtuOption;
    private String CentraleEtuOptionPro;

    public LDAPUser() {
    }
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getCentraleEtuCursus() {
        return CentraleEtuCursus;
    }

    public void setCentraleEtuCursus(String CentraleEtuCursus) {
        this.CentraleEtuCursus = CentraleEtuCursus;
    }

    public String getCentraleEtuCycle() {
        return CentraleEtuCycle;
    }

    public void setCentraleEtuCycle(String CentraleEtuCycle) {
        this.CentraleEtuCycle = CentraleEtuCycle;
    }

    public String getCentraleEtuFormation() {
        return CentraleEtuFormation;
    }

    public void setCentraleEtuFormation(String CentraleEtuFormation) {
        this.CentraleEtuFormation = CentraleEtuFormation;
    }

    public String getCentraleEtuDateNaissance() {
        return CentraleEtuDateNaissance;
    }

    public void setCentraleEtuDateNaissance(String CentraleEtuDateNaissance) {
        this.CentraleEtuDateNaissance = CentraleEtuDateNaissance;
    }

    public String getCentraleEtuGroupe() {
		return CentraleEtuGroupe;
	}
    
    public void setCentraleEtuGroupe(String centraleEtuGroupe) {
		CentraleEtuGroupe = centraleEtuGroupe;
	}
    public String getCentraleEtuOption() {
        return CentraleEtuOption;
    }

    public void setCentraleEtuOption(String CentraleEtuOption) {
        this.CentraleEtuOption = CentraleEtuOption;
    }

    public String getCentraleEtuOptionPro() {
        return CentraleEtuOptionPro;
    }

    public void setCentraleEtuOptionPro(String CentraleEtuOptionPro) {
        this.CentraleEtuOptionPro = CentraleEtuOptionPro;
    }
    
    
}
