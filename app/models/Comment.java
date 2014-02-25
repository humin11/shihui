package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Humin on 2/3/14.
 */
@Entity
public class Comment extends Model {

    @Id
    public Long id;

    @ManyToOne
    public User user;

    @ManyToOne
    public Goods goods;

    public String content;

    public Date create_at = new Date();

    public Date update_at = new Date();

    @OneToMany
    public List<Reply> replyList = new ArrayList<Reply>();

    public static Finder<Long, Comment> find = new Finder<Long, Comment>(
            Long.class, Comment.class
    );
}
