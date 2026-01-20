package kun.uz.entities;

import jakarta.persistence.*;
        import kun.uz.entities.base.BaseEntity;
import kun.uz.enums.ArticleLikeStatus;
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
    @Enumerated(EnumType.STRING)
    ArticleLikeStatus status;
    @Column(name = "profile_id")
    String profileId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",   insertable = false, updatable = false)
    ProfileEntity profile;


    @Column(name = "article_id")
    String articleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id",  insertable = false, updatable = false)
    ArticleEntity article;
}
