package models;

import com.avaje.ebean.Page;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.i18n.Messages;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Humin on 1/6/14.
 */
@Entity
public class Goods extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String source;

    @Constraints.Required
    public Double price;

    public Double original_price;

    @Lob
    public String description;

    public String original_url;

    @ManyToOne
    public Brand brand;

    @ManyToOne
    public ProductModel model;

    @ManyToOne
    public ProductType types;

    public Boolean is_release=false;

    @OneToMany
    public List<GoodsPic> picList;

    @Lob
    public String comment;

    public Date create_at = new Date();

    public Date update_at = new Date();

    public Date release_at;

    public static Map<String, String> getGoodsFields(){
        Field[] goodsFields =  Goods.class.getDeclaredFields();
        Map<String, String> fieldsMap = new LinkedHashMap<String, String>();
        for(Field field: goodsFields){
            if(!field.getName().equalsIgnoreCase("id") && !field.getName().contains("_at") && !field.getName().equalsIgnoreCase("is_release") && !field.getName().equalsIgnoreCase("comment")){
                fieldsMap.put(field.getName(), Messages.get(field.getName()));
            }
        }
        return  fieldsMap;
    }

    public static Finder<Long,Goods> find = new Finder<Long,Goods>(
            Long.class, Goods.class
    );

    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     */
    public static Page<Goods> page(int page, int pageSize, String sortBy, String order) {
        return
                find.where()
                        .orderBy(sortBy + " " + order)
                        .findPagingList(pageSize)
                        .setFetchAhead(false)
                        .getPage(page);
    }

}
