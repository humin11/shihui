package controllers;

import models.Baoliao;
import play.*;
import play.mvc.*;

import views.html.admin.*;

import java.util.List;

/**
 * Created by Humin on 1/3/14.
 */
public class Admin extends Controller {

    public static Result index(){
        return ok(index.render(""));
    }

}
