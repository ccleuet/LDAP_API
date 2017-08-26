package com.ecnantes.ldap.utils;

/**
 *
 * @author Christophe CLEUET
 */

public class PropertiesManager {

    private static final String LDAPHOST = "ldaps://ldaps.nomade.ec-nantes.fr:636";
    private static final String LDAPBASEDN = "ou=people,dc=ec-nantes,dc=fr";
    private static final String LDAPSECURITYPROTOCOL = "ssl";

    public static String getLDAPHOST() {
        return LDAPHOST;
    }

    public static String getLDAPBASEDN() {
        return LDAPBASEDN;
    }

    public static String getLDAPSECURITYPROTOCOL() {
        return LDAPSECURITYPROTOCOL;
    }
}
