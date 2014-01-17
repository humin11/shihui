package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Humin on 1/3/14.
 */
@Entity
public class Collector extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String site_name;

    public String type_name;

    @Constraints.Required
    public  String url;

    public String item_rules;

    public int min_pages;

    public int max_pages;

    public Boolean isActive = true;

    public Date create_at = new Date();

    public Date update_at = new Date();

    public static Finder<Long,Collector> find = new Finder<Long,Collector>(
            Long.class, Collector.class
    );

    public static void createAttributions(Collector coll){
        Set<String> fieldsSet = Goods.getGoodsFields().keySet();
        Attribution attribution = null;
        for(String fieldName: fieldsSet){
            attribution = Attribution.find.where().eq("name",fieldName).eq("coll.id", coll.id).findUnique();
            if(attribution==null){
                attribution = new Attribution();
                attribution.name = fieldName;
                attribution.coll = coll;
                attribution.save();
            }
        }

    }
}
