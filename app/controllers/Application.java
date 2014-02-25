package controllers;

import akka.actor.ActorRef;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.User;
import play.Routes;
import play.data.Form;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import jobs.JDActor;
import models.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import play.Logger;
import play.mvc.*;
import play.mvc.Http.Response;
import play.mvc.Http.Session;
import play.mvc.Result;


import providers.MyUsernamePasswordAuthProvider;
import providers.MyUsernamePasswordAuthProvider.MyLogin;
import providers.MyUsernamePasswordAuthProvider.MySignup;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;

import views.html.*;
import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.providers.password.UsernamePasswordAuthProvider;
import com.feth.play.module.pa.user.AuthUser;

public class Application extends Controller {
    public static final String FLASH_MESSAGE_KEY = "message";
    public static final String FLASH_ERROR_KEY = "error";
    public static final String USER_ROLE = "user";


    final static ActorRef jdActor = JDActor.instance;

    public static Result index() throws Exception {
//        WebClient webClient = new WebClient(BrowserVersion.CHROME);
//        HtmlPage page = webClient.getPage("http://item.jd.com/1028470183.html");
//        Document doc = Jsoup.parse(page.asXml());
//        System.out.println(doc.select(".spec-items>ul>li>img[src]").get(0).attr("src")+"--------");
//        System.out.println(page.getElementById("page_maprice").getTextContent()+"**************");

//        List<Collector> collList = Collector.find.all();
//        Connection conn = null;
//        Document d = null;
//        Elements elements = null;
//        CollectorItem item = null;
//        for(Collector c: collList){
//            for(int i=c.min_pages; i<=1; i++){
//                conn = Jsoup.connect(c.url.replaceAll("\\{page\\}",String.valueOf(i)));
//                d = conn.timeout(0).get();
//                elements = d.select("#plist>ul.list-h>li>div.lh-wrap>div.p-img>a");
//                for(Element e: elements){
//                    item = new CollectorItem();
//                    item.url = e.attr("href");
//                    item.coll = c;
//                    if(CollectorItem.find.where().eq("url", item.url).eq("coll.id", c.id).findUnique()==null){
//                        item.save();
//                    }
//                    jdActor.tell(item, null);
//                }
//            }
//        }


//        String url="http://item.jd.com/1049022786.html";
//        long time0 = Calendar.getInstance().getTimeInMillis();
//        WebClient webClient = new WebClient(BrowserVersion.CHROME);
//        HtmlPage page = null;
//        long time1 = Calendar.getInstance().getTimeInMillis();
//        try {
//            page = webClient.getPage(url);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            Logger.error("Error Message:"+e.getMessage());
//        }
//        Document doc = Jsoup.parse(page.asXml());
//        long time2 = Calendar.getInstance().getTimeInMillis();
//        Goods goods = Goods.find.where().eq("original_url", url).findUnique();
//        if(goods==null){
//            goods = new Goods();
//            long time3 = Calendar.getInstance().getTimeInMillis();
//            List<Attribution> attrList = Attribution.find.where().eq("coll.id", 1).isNotNull("rules").findList();
//            Brand _brand = null;
//            ProductType _type = null;
//            GoodsPic _pic = null;
//            long time4 = Calendar.getInstance().getTimeInMillis();
//            for(Attribution attr: attrList){
//                if(attr.name.equalsIgnoreCase("name")){
//                    goods.name = doc.select(attr.rules).text();
//                } else if(attr.name.equalsIgnoreCase("price")){
//                    goods.price = Double.valueOf(doc.select(attr.rules).text().replaceAll("￥",""));
//                } else if(attr.name.equalsIgnoreCase("original_price")){
//                    goods.original_price = Double.valueOf(doc.select(attr.rules).text().replaceAll("￥", ""));
//                } else if(attr.name.equalsIgnoreCase("description")){
//                    goods.description = doc.select(attr.rules).text();
//                } else if(attr.name.equalsIgnoreCase("brand")){
//                    String brand = doc.select(attr.rules).text();
//                    _brand = Brand.find.where().eq("name", brand).findUnique();
//                    if(_brand == null){
//                        _brand = new Brand();
//                        _brand.name = brand;
//                        _brand.save();
//                    }
//                    goods.brand = _brand;
//                } else if(attr.name.equalsIgnoreCase("types")){
//                    String type = doc.select(attr.rules).text();
//                    _type = ProductType.find.where().eq("name",type).findUnique();
//                    if(_type == null){
//                        _type = new ProductType();
//                        _type.name = type;
//                        _type.save();
//                    }
//                    goods.types = _type;
//                } else if(attr.name.equalsIgnoreCase("picList")){
//                    Elements pics = doc.select(attr.rules);
//                    for(Element pic: pics){
//                        _pic = GoodsPic.find.where().eq("tiny",pic.attr("src")).findUnique();
//                        if(_pic == null){
//                            _pic = new GoodsPic();
//                            _pic.tiny = pic.attr("src");
//                            _pic.small = pic.attr("src").replaceAll("/n5/","/n4/");
//                            _pic.middle = pic.attr("src").replaceAll("/n5/","/n3/");
//                            _pic.larger = pic.attr("src").replaceAll("/n5/","/n2/");
//                            _pic.huge = pic.attr("src").replaceAll("/n5/","/n1/");
//                            _pic.original = pic.attr("src").replaceAll("/n5/","/n0/");
//                            _pic.goods = goods;
//                            _pic.save();
//                        }
//                    }
//                }
//
//                goods.original_url = url;
//                goods.source = "jd.com";
//                goods.save();
//
//            }
//            long time5 = Calendar.getInstance().getTimeInMillis();
//
//            play.Logger.info("time1-time0:"+ (time1-time0));
//            play.Logger.info("time2-time1:"+ (time2-time1));
//            play.Logger.info("time3-time2:"+ (time3-time2));
//            play.Logger.info("time4-time3:"+ (time4-time3));
//            play.Logger.info("time5-time4:"+ (time5-time4));
//        }

        return ok(views.html.index.render(Goods.page(0, 10, "price/original_price", "desc"), "price/original_price","desc"));
    }

    public static Result list(int page, String sortBy, String order){
        return ok(views.html.index.render(Goods.page(page, 10, "price/original_price", "desc"), sortBy, order));
    }


    public static Result schedule() throws IOException {
        List<Collector> collList = Collector.find.all();
        Connection conn = null;
        Document d = null;
        Elements elements = null;
        CollectorItem item = null;
        for(Collector c: collList){
            for(int i=c.min_pages; i<=c.max_pages; i++){
                conn = Jsoup.connect(c.url.replaceAll("\\{page\\}",String.valueOf(i)));
                d = conn.timeout(0).get();
                elements = d.select("#plist>ul.list-h>li>div.lh-wrap>div.p-img>a");
                for(Element e: elements){
                    item = new CollectorItem();
                    item.url = e.attr("href");
                    item.coll = c;
                    if(CollectorItem.find.where().eq("url", item.url).eq("coll.id", c.id).findUnique()==null){
                        item.save();
                    }
                    jdActor.tell(item, null);
                }
            }
        }

        return ok("");
    }

    public static User getLocalUser(final Session session) {
        final AuthUser currentAuthUser = PlayAuthenticate.getUser(session);
        final User localUser = User.findByAuthUserIdentity(currentAuthUser);
        return localUser;
    }

    @Restrict(@Group(Application.USER_ROLE))
    public static Result restricted() {
        final User localUser = getLocalUser(session());
        return ok(restricted.render(localUser));
    }

    @Restrict(@Group(Application.USER_ROLE))
    public static Result profile() {
        final User localUser = getLocalUser(session());
        return ok(profile.render(localUser));
    }

    public static Result login() {
        return ok(login.render(MyUsernamePasswordAuthProvider.LOGIN_FORM));
    }

    public static Result doLogin() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        final Form<MyLogin> filledForm = MyUsernamePasswordAuthProvider.LOGIN_FORM
                .bindFromRequest();
        if (filledForm.hasErrors()) {
            // User did not fill everything properly
            return badRequest(login.render(filledForm));
        } else {
            // Everything was filled
            return UsernamePasswordAuthProvider.handleLogin(ctx());
        }
    }

    public static Result signup() {
        return ok(signup.render(MyUsernamePasswordAuthProvider.SIGNUP_FORM));
    }

    public static Result jsRoutes() {
        return ok(
                Routes.javascriptRouter("jsRoutes",
                        controllers.routes.javascript.Signup.forgotPassword()))
                .as("text/javascript");
    }

    public static Result doSignup() {
        com.feth.play.module.pa.controllers.Authenticate.noCache(response());
        final Form<MySignup> filledForm = MyUsernamePasswordAuthProvider.SIGNUP_FORM
                .bindFromRequest();
        if (filledForm.hasErrors()) {
            // User did not fill everything properly
            return badRequest(signup.render(filledForm));
        } else {
            // Everything was filled
            // do something with your part of the form before handling the user
            // signup
            return UsernamePasswordAuthProvider.handleSignup(ctx());
        }
    }

    public static String formatTimestamp(final long t) {
        return new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(new Date(t));
    }
}
