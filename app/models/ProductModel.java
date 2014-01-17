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
public class ProductModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    @ManyToOne
    public Brand brand;

}
