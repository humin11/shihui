package models;

import com.avaje.ebean.Page;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.i18n.Messages;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Humin on 1/6/14.
 */
@Entity
public class Baoliao extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String url;

    @Constraints.Required
    @Lob
    public String content;

    @ManyToOne
    public User user;

    public Boolean is_read=false;

    public Date create_at = new Date();

    public Date update_at = new Date();

    public Date release_at;

    public static Map<String, String> getGoodsFields(){
        Field[] goodsFields =  Baoliao.class.getDeclaredFields();
        Map<String, String> fieldsMap = new LinkedHashMap<String, String>();
        for(Field field: goodsFields){
            if(!field.getName().equalsIgnoreCase("id") && !field.getName().contains("_at") && !field.getName().equalsIgnoreCase("is_release") && !field.getName().equalsIgnoreCase("comment")){
                fieldsMap.put(field.getName(), Messages.get(field.getName()));
            }
        }
        return  fieldsMap;
    }

    public static Finder<Long, Baoliao> find = new Finder<Long, Baoliao>(
            Long.class, Baoliao.class
    );

    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     */
    public static Page<Baoliao> page(int page, int pageSize, String sortBy, String order) {
        return
                find.where()
                        .orderBy(sortBy + " " + order)
                        .findPagingList(pageSize)
                        .setFetchAhead(false)
                        .getPage(page);
    }

}
