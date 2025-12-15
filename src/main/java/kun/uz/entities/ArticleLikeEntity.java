package kun.uz.entities;

import jakarta.persistence.*;
import kun.uz.entities.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.prefs.BackingStoreException;

@Entity
@Getter
@Setter
@Table(name = "article_like")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleLikeEntity extends BaseEntity {
    @Column(name = "profile_id")
    String profileId;

    @Column(name = "article_id")
    String articleId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    ProfileEntity profile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    ArticleEntity article;
}
