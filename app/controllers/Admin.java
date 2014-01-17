package controllers;

import play.*;
import play.mvc.*;

import views.html.admin.*;

/**
 * Created by Humin on 1/3/14.
 */
public class Admin extends Controller {

    public static Result index(){
        return ok(index.render(""));
    }

}
