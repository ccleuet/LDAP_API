package com.ecnantes.ldap.api;

import com.ecnantes.ldap.beans.Credential;
import com.ecnantes.ldap.beans.LDAPUser;
import com.ecnantes.ldap.utils.LDAPConnection;
import java.util.List;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Christophe CLEUET
 */
@Path("/ldap")
public class LdapApi {

    private final static Logger logger = LoggerFactory.getLogger(LdapApi.class);

    @POST
    @Produces("application/json")
    @Path("/connection")
    public Response getloggedUser(Credential cred) {
        logger.info("LDAP API Request : Connexion au LDAP de l'Ecole");
        LDAPUser user = LDAPConnection.getUser(cred, cred.getLogin());
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @POST
    @Produces("application/json")
    @Path("/option/{option}")
    public Response getStudentOption(@PathParam("option") String option,Credential cred) {
        logger.info("LDAP API Request : Connexion au LDAP de l'Ecole");
        List<LDAPUser> userlist=LDAPConnection.getUsersbyOption(cred,option);
        if (userlist.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Users not found").build();
        }
        return Response.status(Response.Status.OK).entity(userlist).build();
    }
    
    @POST
    @Produces("application/json")
    @Path("/group/{group}")
    public Response getStudentGroup(@PathParam("group") String group,Credential cred) {
        logger.info("LDAP API Request : Connexion au LDAP de l'Ecole");
        List<LDAPUser> userlist=LDAPConnection.getUsersbyGroup(cred,group);
        if (userlist.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Users not found").build();
        }
        return Response.status(Response.Status.OK).entity(userlist).build();
    }
}
