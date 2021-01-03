package entity;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    private Date createdDate;
    private Date lastModifiedDate;

    public BaseEntity() {
        this.createdDate = new Date();
        this.lastModifiedDate = new Date();
    }
}
