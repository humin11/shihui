package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Baoliao;
import models.Flower;
import models.Goods;
import models.User;
import org.apache.xpath.operations.Bool;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import securesocial.core.Identity;
import securesocial.core.java.SecureSocial;

import views.html.goodses.*;

import java.util.Calendar;
import java.util.Date;

import static play.data.Form.form;

/**
 * Created by Humin on 1/15/14.
 */
public class Goodses extends Controller {

    final static Form<Goods> goodsForm = form(Goods.class);
    final static Form<Baoliao> baoliaoForm = form(Baoliao.class);

    public static Result index(){
        return ok(views.html.goodses.index.render(Goods.page(0, 10, "price/original_price", "desc"), "name", "desc"));
    }

    public static Result list(int page, String sortBy, String order){
        return ok(views.html.goodses.index.render(Goods.page(page, 10, "price/original_price", "desc"), sortBy, order));
    }


    public static Result edit(Long id){
        Goods goods = Goods.find.byId(id);
        Form<Goods> filledForm = goodsForm.fill(goods);
        return ok(views.html.goodses.edit.render(filledForm));
    }

    public static Result save(){
        Form<Goods> filledForm = goodsForm.bindFromRequest();

        if(filledForm.hasErrors()){
            return badRequest(edit.render(filledForm));
        } else {
            Goods goods = filledForm.get();
            goods.update();
            return redirect(routes.Goodses.index());
        }
    }

    public static Result addFlower(Long id){
        Goods goods = Goods.find.byId(id);

        String userId = "guest";
        Flower flower = Flower.find.where().eq("user.id", userId).eq("goods.id", id).findUnique();
        ObjectNode json = Json.newObject();
        if(flower==null){
            json.put("status", true);
            flower = new Flower();

            flower.user = null;
            flower.goods = goods;
            flower.save();
            json.put("number", goods.flowerList.size());
        }else {
            //Some body have flowered
            json.put("status", false);
        }

        return ok(json);
    }

    public static Result addEgg(){
        return ok();
    }

    public static Result addComment(){
        return ok();
    }

    @Restrict(@Group(Application.USER_ROLE))
    public static Result doBaoliao(){
        final User localUser = Application.getLocalUser(session());
        Baoliao baoliao = baoliaoForm.bindFromRequest().get();
        baoliao.user = localUser;
        Calendar c = Calendar.getInstance();
        baoliao.create_at = c.getTime();
        baoliao.update_at = c.getTime();
        baoliao.save();
        return ok();
    }

    public static Result checkURL(){
        String[] values = request().body().asFormUrlEncoded().get("url");
        Boolean result = true;
        for(String value: values){
            Baoliao b = Baoliao.find.where().eq("url", value).findUnique();
            if(b!=null){
                result = false;
            }
        }
        return ok(result.toString());
    }

}
