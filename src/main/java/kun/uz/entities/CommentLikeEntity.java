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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    ProfileEntity profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    CommentEntity comment;
}
