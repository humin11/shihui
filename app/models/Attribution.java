package models;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Humin on 1/3/14.
 */
@Entity
public class Attribution extends Model {

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public String rules;

    public String layer;

    @ManyToOne
    public Collector coll;

    public static Finder<Long,Attribution> find = new Finder<Long,Attribution>(
            Long.class, Attribution.class
    );
}

