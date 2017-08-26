/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecnantes.ldap.utils;

import com.ecnantes.ldap.beans.Credential;
import com.ecnantes.ldap.beans.LDAPUser;
import com.ecnantes.ldap.mapper.UserMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Christophe CLEUET
 */
public class LDAPConnection {

	private static final String LDAPHOST = PropertiesManager.getLDAPHOST();
	private static final String LDAPBASEDN = PropertiesManager.getLDAPBASEDN();

	private final static Logger logger = LoggerFactory.getLogger(LDAPConnection.class);

	private static DirContext LDAPConnection(String login, String password) {

		Properties env = new Properties();

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, LDAPHOST);
		env.put(Context.SECURITY_PRINCIPAL, "uid=" + login + "," + LDAPBASEDN);
		env.put(Context.SECURITY_CREDENTIALS, password);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put("java.naming.ldap.factory.socket", "com.ecnantes.ldap.utils.MySSLSocketFactory");

		try {
			DirContext context = new InitialDirContext(env);
			logger.info("Connexion au LDAP de l'utilisateur :" + login);
			return context;
		} catch (NamingException e) {
			logger.info(e.getMessage());
			logger.error("Echec de la connexion au serveur");
		}
		return null;
	}

	public static LDAPUser getUser(Credential cred, String userId) {
		DirContext context = LDAPConnection(cred.getLogin(), cred.getPassword());
		LDAPUser user = null;
		try {
			logger.info("Recuperation des informations de l'utilisateur : " + userId);
			Attributes attrs = context.getAttributes("uid=" + userId + ",ou=people,dc=ec-nantes,dc=fr");
			user = UserMapper.toUser(attrs, userId);
		} catch (NamingException e) {
			logger.error("Echec de la récupération des informations de l'utilisateur");
		}
		return user;
	}

	public static List<LDAPUser> getUsersbyOption(Credential cred, String option) {
		DirContext context = LDAPConnection(cred.getLogin(), cred.getPassword());
		List<LDAPUser> userlist = new ArrayList();
		try {
			logger.info("Recuperation des étudiants de l'option : " + option);
			ArrayList<String> studentIdList = new ArrayList();
			NamingEnumeration e = context.search("ou=people,dc=ec-nantes,dc=fr", getOptionFilter(option), getControls());
			userlist=getUsersFromSearch(cred,e);
		} catch (Exception e) {
			logger.error("Echec de la recuperation des utilisateurs");
		}
		return userlist;
	}
	
	public static List<LDAPUser> getUsersbyGroup(Credential cred, String group) {
		DirContext context = LDAPConnection(cred.getLogin(), cred.getPassword());
		List<LDAPUser> userlist = new ArrayList();
		try {
			logger.info("Recuperation des étudiants du groupe : " + group);
			ArrayList<String> studentIdList = new ArrayList();
			NamingEnumeration e = context.search("ou=people,dc=ec-nantes,dc=fr", getGroupFilter(group), getControls());
			userlist=getUsersFromSearch(cred,e);
		} catch (Exception e) {
			logger.error("Echec de la recuperation des utilisateurs");
		}
		return userlist;
	}

	private static List<LDAPUser> getUsersFromSearch(Credential cred, NamingEnumeration e) throws NamingException{
		List<LDAPUser> userlist = new ArrayList();
		ArrayList<String> studentIdList = new ArrayList();
		try {
		while (e.hasMore()) {
			SearchResult entry = (SearchResult) e.next();
			logger.info(entry.getName());
			String studentId = entry.getName().replaceAll("uid=", "");
			studentIdList.add(studentId);
		}
		for (int k = 0; k < studentIdList.size(); k++) {
			LDAPUser user = getUser(cred, studentIdList.get(k));
			if (null != user) {
				userlist.add(user);
			}
		} 
		}catch (Exception err) {
			logger.error("Something wrong happened :"+err.getMessage());
		}
		return userlist;
	}
	private static String getOptionFilter(String option) {
		return "(&(eduPersonPrimaryAffiliation=student)(CentraleEtuOption=" + option + ")(CentraleEtuCycle=2016-2017" + "))";
	}

	private static String getGroupFilter(String group) {
		return "(&(eduPersonPrimaryAffiliation=student)(CentraleEtuGroupe=" + group + ")(CentraleEtuCycle=2016-2017" + "))";

	}

	private static SearchControls getControls() {
		String[] attrIDs = {"cn", "CentraleEtuOption", "CentraleEtuGroupe", "CentraleEtuCycle", "eduPersonPrimaryAffiliation"};
		SearchControls ctls = new SearchControls();
		ctls.setReturningAttributes(attrIDs);
		return ctls;
	}
}
