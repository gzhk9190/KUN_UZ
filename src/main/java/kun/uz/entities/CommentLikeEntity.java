package kun.uz.entities;

import jakarta.persistence.*;
import kun.uz.entities.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Table(name = "comment_like")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentLikeEntity  extends BaseEntity {

    @Column(name = "profile_id")
    String profileId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",  insertable = false, updatable = false)
    ProfileEntity profile;

    @Column(name = "comment_id")
    String commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id",   insertable = false, updatable = false)
    CommentEntity comment;
}
