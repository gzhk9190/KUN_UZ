package kun.uz.entities;

import jakarta.persistence.*;
import kun.uz.repository.ArticleTypeRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "article_article_type")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleArticleTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "article_id")
    String articleId;

    @Column(name = "article_type_id")
    String articleTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", insertable = false, updatable = false)
    ArticleEntity article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_type_id", insertable = false, updatable = false)
    ArticleTypeEntity articleType;
}
