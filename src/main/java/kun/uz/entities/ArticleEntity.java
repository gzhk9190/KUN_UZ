package kun.uz.entities;

import jakarta.persistence.*;
import kun.uz.entities.base.BaseEntity;
import kun.uz.enums.ArticleStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;



@Entity
@Getter
@Setter
@Table(name = "article")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleEntity extends BaseEntity {
    @Column(nullable = false,length = 50)
    String title;
    @Column(length = 200)
    String description;
    @Column(nullable = false, length = 5000)
    String content;
    @Column(name = "shared_count")
    Integer sharedCount;
    @Column(name = "image_id")
    String imageId;

    @Column(name = "region_id")
    String regionId;

    @Column(name = "category_id")
    String categoryId;

    @Column(name = "moderator_id")
    String moderatorId;

    @Column(name = "publisher_id")
    String publisherId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    RegionEntity region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id", nullable = false)
    ProfileEntity moderator;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", nullable = false)
    ProfileEntity publisher;
    ArticleStatus status;
}
