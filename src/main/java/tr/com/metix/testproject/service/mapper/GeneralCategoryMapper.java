package tr.com.metix.testproject.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.metix.testproject.domain.GeneralCategory;
import tr.com.metix.testproject.service.dto.GeneralCategoryDTO;

@Mapper(componentModel = "spring", uses = RestaurantMapper.class)
public interface GeneralCategoryMapper {
    GeneralCategoryMapper INSTANCE = Mappers.getMapper(GeneralCategoryMapper.class);

    @Mapping(source = "restaurants", target = "restaurants")
    GeneralCategoryDTO toDTO(GeneralCategory generalCategory);

    @Mapping(source = "restaurants" ,  target = "restaurants")
    GeneralCategory toEntity(GeneralCategoryDTO generalCategoryDTO);
}
