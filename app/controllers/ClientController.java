package controllers;

import controllers.authentions.ClientAuthenticator;
import java.util.List;
import models.Client;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import models.utils.Clients;

@Security.Authenticated(ClientAuthenticator.class)
public class ClientController extends Controller {

  public Result getClientList() {
    List<Client> clientList = Clients.find.query().where().findList();
    return ok(clientList.toString());
  }
}
