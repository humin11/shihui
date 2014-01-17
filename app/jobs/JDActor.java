package jobs;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import models.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import play.libs.Akka;

import java.io.IOException;
import java.util.List;

/**
 * Created by Humin on 1/9/14.
 */
public class JDActor extends UntypedActor {


    public static ActorRef instance = Akka.system().actorOf(Props.create(JDActor.class));

    @Override
    public void onReceive(Object message) {

        if(message instanceof CollectorItem){
            CollectorItem item = (CollectorItem)message;
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            HtmlPage page = null;
            try {
                page = webClient.getPage(item.url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Document doc = Jsoup.parse(page.asXml());

            Goods goods = Goods.find.where().eq("original_url", item.url).findUnique();
            if(goods==null){
                goods = new Goods();
                List<Attribution> attrList = Attribution.find.where().eq("coll.id", item.coll.id).isNotNull("rules").findList();
                Brand _brand = null;
                ProductType _type = null;
                GoodsPic _pic = null;
                for(Attribution attr: attrList){
                    if(attr.name.equalsIgnoreCase("name")){
                        goods.name = doc.select(attr.rules).text();
                    } else if(attr.name.equalsIgnoreCase("price")){
                        goods.price = Double.valueOf(doc.select(attr.rules).text().replaceAll("￥",""));
                    } else if(attr.name.equalsIgnoreCase("original_price")){
                        goods.original_price = Double.valueOf(doc.select(attr.rules).text().replaceAll("￥", ""));
                    } else if(attr.name.equalsIgnoreCase("description")){
                        goods.description = doc.select(attr.rules).text();
                    } else if(attr.name.equalsIgnoreCase("brand")){
                        String brand = doc.select(attr.rules).text();
                        _brand = Brand.find.where().eq("name", brand).findUnique();
                        if(_brand == null){
                            _brand = new Brand();
                            _brand.name = brand;
                            _brand.save();
                        }
                        goods.brand = _brand;
                    } else if(attr.name.equalsIgnoreCase("types")){
                        String type = doc.select(attr.rules).text();
                        _type = ProductType.find.where().eq("name",type).findUnique();
                        if(_type == null){
                            _type = new ProductType();
                            _type.name = type;
                            _type.save();
                        }
                        goods.types = _type;
                    } else if(attr.name.equalsIgnoreCase("picList")){
                        Elements pics = doc.select(attr.rules);
                        for(Element pic: pics){
                            _pic = GoodsPic.find.where().eq("tiny",pic.attr("src")).findUnique();
                            if(_pic == null){
                                _pic = new GoodsPic();
                                _pic.tiny = pic.attr("src");
                                _pic.small = pic.attr("src").replaceAll("/n5/","/n4/");
                                _pic.middle = pic.attr("src").replaceAll("/n5/","/n3/");
                                _pic.larger = pic.attr("src").replaceAll("/n5/","/n2/");
                                _pic.huge = pic.attr("src").replaceAll("/n5/","/n1/");
                                _pic.original = pic.attr("src").replaceAll("/n5/","/n0/");
                                _pic.goods = goods;
                                _pic.save();
                            }
                        }
                    }

                    goods.original_url = item.url;
                    goods.source = "jd.com";
                    goods.save();

                }
            }

        }

    }
}
