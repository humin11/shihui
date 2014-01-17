package controllers;

import models.Collector;
import models.Goods;
import play.data.Form;
import play.mvc.Result;

import views.*;

import views.html.goodses.*;

import static play.data.Form.form;

/**
 * Created by Humin on 1/15/14.
 */
public class Goodses extends Collectors {

    final static Form<Goods> goodsForm = form(Goods.class);

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

}
