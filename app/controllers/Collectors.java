package controllers;

import models.Collector;
import play.data.Form;
import static play.data.Form.*;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.collector.*;

import java.util.List;

/**
 * Created by Humin on 1/3/14.
 */
public class Collectors extends Controller {
    final static Form<Collector> collectorForm = form(Collector.class);

    public static Result index(){
        List<Collector> collectorList = Collector.find.all();
        return ok(index.render(collectorList));
    }

    public static Result blank(){
        return ok(blank.render(collectorForm));
    }

    public static Result create(){
        Form<Collector> filledForm = collectorForm.bindFromRequest();

        if(filledForm.hasErrors()){
            return badRequest(blank.render(filledForm));
        } else {
            Collector collector = filledForm.get();
            collector.save();
            Collector.createAttributions(collector);
            return redirect(routes.Collectors.index());
        }

    }

    public static Result edit(Long id){
        Collector collector = Collector.find.byId(id);
        Form<Collector> filledForm = collectorForm.fill(collector);
        return ok(edit.render(filledForm));
    }

    public static Result delete(Long id){
        Collector collector = Collector.find.byId(id);
        collector.delete();
        return redirect(routes.Collectors.index());
    }

    public static Result save(){
        Form<Collector> filledForm = collectorForm.bindFromRequest();

        if(filledForm.hasErrors()){
            return badRequest(edit.render(filledForm));
        } else {
            Collector collector = filledForm.get();
            collector.update();
            Collector.createAttributions(collector);
            return redirect(routes.Collectors.index());
        }
    }

}
