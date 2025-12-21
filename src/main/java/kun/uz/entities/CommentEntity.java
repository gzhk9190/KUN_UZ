package kun.uz.entities;

import jakarta.persistence.*;
import kun.uz.entities.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentEntity extends BaseEntity {
    @Column(nullable = false, length = 200)
    String content;

    @Column(name = "reply_id")
    String replyId;

    @Column(name = "updated_date")
    LocalDateTime updatedDate;

    @Column(name = "profile_id")
    String profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",   insertable = false, updatable = false)
    ProfileEntity profile;

    @Column(name = "article_id")
    String articleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id",   insertable = false, updatable = false)
    ArticleEntity article;

}
