package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Humin on 1/7/14.
 */
@Entity
public class Brand extends Model{

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public static Finder<Long,Brand> find = new Finder<Long,Brand>(
            Long.class, Brand.class
    );
}
