package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Humin on 1/6/14.
 */
@Entity
public class GoodsPic extends Model {

    @Id
    @GeneratedValue
    public Long id;

    public String original;

    public String tiny;

    public String small;

    public String middle;

    public String larger;

    public String huge;

    public Boolean is_cover;

    @ManyToOne
    public Goods goods;

    public static Finder<Long,GoodsPic> find = new Finder<Long,GoodsPic>(
            Long.class, GoodsPic.class
    );
}
