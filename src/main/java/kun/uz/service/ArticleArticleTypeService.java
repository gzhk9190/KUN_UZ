package kun.uz.service;

import kun.uz.dto.response.ArticleArticleTypeResponseDTO;
import kun.uz.entities.ArticleArticleTypeEntity;
import kun.uz.repository.ArticleArticleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ArticleArticleTypeService {
    private final ArticleArticleTypeRepository articleArticleTypeRepository;


    public List<ArticleArticleTypeResponseDTO> getArticleArticleTypesByArticleTypeId(String id) {
        return articleArticleTypeRepository.getAllByArticleTypeId(id).stream().map(this::toDTO).toList();
    }

    private ArticleArticleTypeResponseDTO toDTO(ArticleArticleTypeEntity articleArticleTypeEntity) {
        ArticleArticleTypeResponseDTO articleArticleTypeResponseDTO = new ArticleArticleTypeResponseDTO();
        articleArticleTypeResponseDTO.setId(articleArticleTypeEntity.getId());
        articleArticleTypeResponseDTO.setArticleTypeId(articleArticleTypeEntity.getArticleTypeId());
        articleArticleTypeResponseDTO.setArticleId(articleArticleTypeEntity.getArticleId());
        return articleArticleTypeResponseDTO;
    }
}
