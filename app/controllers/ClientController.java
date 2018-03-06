package controllers;

import authentions.ClientAuthenticator;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(ClientAuthenticator.class)
public class ClientController extends Controller {

  public Result getClientList() {
    //List<Client> clientList = Client.find.query().where().findList();
    return ok();
  }
}
