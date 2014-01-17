package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Humin on 1/9/14.
 */
@Entity
public class CollectorItem extends Model {

    @Id
    @GeneratedValue
    public Long id;

    public String url;

    @ManyToOne
    public Collector coll;

    public int collector_count=0;

    public int disconnected_count=0;

    public String response_code;

    public String status;

    public Date create_at = new Date();

    public Date update_at = new Date();

    public static Finder<Long,CollectorItem> find = new Finder<Long,CollectorItem>(
            Long.class, CollectorItem.class
    );
}
