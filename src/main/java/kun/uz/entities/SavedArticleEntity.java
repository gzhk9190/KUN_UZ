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
public class SavedArticleEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    ProfileEntity profile;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id",nullable = false)
    ArticleEntity article;
}
