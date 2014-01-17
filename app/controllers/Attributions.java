package controllers;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import models.Attribution;
import models.Collector;
import models.Goods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.attribution.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import static play.data.Form.form;

/**
 * Created by Humin on 1/3/14.
 */
public class Attributions extends Controller {

    final static Form<Attribution> attrForm = form(Attribution.class);

    public static Result index(){
        List<Attribution> attrList = Attribution.find.all();
        return ok(index.render(attrList));
    }

    public static Result list(Long cid){
        List<Attribution> attrList = null;
        if(cid != null ){
            attrList = Attribution.find.where().eq("coll.id",cid).findList();
        } else {
            attrList = Attribution.find.all();
        }
        return ok(index.render(attrList));
    }


    public static Result blank(Long cid){
        Collector collector = null;
        if(cid!=null)
            collector = Collector.find.byId(cid);
        return ok(blank.render(attrForm, Goods.getGoodsFields(), collector));
    }

    public static Result create(){
        Form<Attribution> filledForm = attrForm.bindFromRequest();

        Collector collector = filledForm.get().coll;

        if(filledForm.hasErrors()){
            return badRequest(blank.render(filledForm, Goods.getGoodsFields(), collector));
        } else {
            Attribution attr = filledForm.get();
            attr.save();
            return redirect(routes.Attributions.index());
        }

    }

    public static Result edit(Long cid) throws IOException {
        Attribution attr = Attribution.find.byId(cid);
        Form<Attribution> filledForm = attrForm.fill(attr);

        return ok(edit.render(filledForm));
    }

    public static Result delete(Long id){
        Attribution attr = Attribution.find.byId(id);
        attr.delete();
        return redirect(routes.Attributions.index());
    }

    public static Result save(){
        Form<Attribution> filledForm = attrForm.bindFromRequest();

        if(filledForm.hasErrors()){
            return badRequest(edit.render(filledForm));
        } else {
            Attribution attr = filledForm.get();
            attr.update();
            return redirect(routes.Attributions.index());
        }
    }

}
