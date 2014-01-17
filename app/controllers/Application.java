package controllers;

import akka.actor.ActorRef;
import jobs.JDActor;
import models.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import play.mvc.*;

import java.io.IOException;
import java.util.List;

public class Application extends Controller {

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
//            for(int i=c.min_pages; i<=c.max_pages; i++){
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
}
