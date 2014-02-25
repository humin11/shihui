package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Humin on 2/3/14.
 */
@Entity
public class Reply extends Model {

    @Id
    public Long id;

    @ManyToOne
    public User user;

    public String content;

    @ManyToOne
    public Comment comment;

    public Date create_at = new Date();

    public Date update_at = new Date();

    public static Finder<Long, Reply> find = new Finder<Long, Reply>(
            Long.class, Reply.class
    );
}
