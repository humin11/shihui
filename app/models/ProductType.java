package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Humin on 1/7/14.
 */
@Entity
public class ProductType extends Model {

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public String parent_name;

    @ManyToOne
    public ProductType parentType;

    public static Finder<Long,ProductType> find = new Finder<Long,ProductType>(
            Long.class, ProductType.class
    );
}
