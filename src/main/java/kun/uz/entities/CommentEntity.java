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
@Table(name = "comment")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentEntity extends BaseEntity {
    @Column(name = "updated_date")
    LocalDateTime updatedDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    ProfileEntity profile;
    @Column(nullable = false, length = 200)
    String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    ArticleEntity article;
    @Column(name = "reply_id")
    String replyId;
}
