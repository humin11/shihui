package controllers;

import models.Baoliao;
import models.Goods;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import static play.data.Form.form;


/**
 * Created by Humin on 1/3/14.
 */
public class BaoliaoAdmin extends Controller {
    final static Form<Goods> goodsForm = form(Goods.class);

    public static Result index(int page, String sortBy, String order){
        return ok(views.html.admin.baoliao.index.render(Baoliao.page(page, 10, "is_read", "desc"), sortBy, order));
    }

    public static Result edit(Long id){
        Baoliao baoliao = Baoliao.find.byId(id);
        return ok(views.html.admin.baoliao.edit.render(goodsForm,baoliao));
    }

}
