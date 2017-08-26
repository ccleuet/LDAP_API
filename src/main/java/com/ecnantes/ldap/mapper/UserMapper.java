/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecnantes.ldap.mapper;

import com.ecnantes.ldap.beans.LDAPUser;
import javax.naming.directory.Attributes;

/**
 *
 * @author Christophe CLEUET
 */
public class UserMapper {

    public static LDAPUser toUser( Attributes src, String uid) {
        LDAPUser dest = new LDAPUser();
        if (src != null) {
            dest.setUid(uid);
            dest.setCn(src.get("cn").toString().replaceAll("cn:", ""));
            dest.setSn(src.get("sn").toString().replaceAll("sn:", ""));
            dest.setMail(src.get("mail").toString().replaceAll("mail:", ""));
            dest.setCentraleEtuCursus(src.get("centraleetucursus").toString().replaceAll("CentraleEtuCursus:", ""));  
            dest.setCentraleEtuCycle(src.get("centraleetucycle").toString().replaceAll("CentraleEtuCycle:", ""));
            dest.setCentraleEtuFormation(src.get("centraleetuformation").toString().replaceAll("CentraleEtuFormation:", ""));
            dest.setCentraleEtuDateNaissance(src.get("centraleetudatenaissance").toString().replaceAll("CentraleEtuDateNaissance:", ""));
            dest.setCentraleEtuOption(src.get("centraleetuoption").toString().replaceAll("CentraleEtuOption:", ""));
            dest.setCentraleEtuOptionPro(src.get("centraleEtuoptionpro").toString().replaceAll("CentraleEtuOptionPro:", ""));
        }
        return dest;
    }
}
