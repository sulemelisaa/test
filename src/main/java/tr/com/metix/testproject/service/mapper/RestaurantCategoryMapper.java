package tr.com.metix.testproject.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.metix.testproject.domain.RestaurantCategory;
import tr.com.metix.testproject.service.dto.RestaurantCategoryDTO;

@Mapper(componentModel = "spring")
public interface RestaurantCategoryMapper {
    RestaurantCategoryMapper INSTANCE = Mappers.getMapper(RestaurantCategoryMapper.class);

    @Mapping(source = "restaurant.id",target = "restaurantId")
    RestaurantCategoryDTO toDTO(RestaurantCategory  restaurantCategory);

    @Mapping(source = "restaurantId",target = "restaurant.id")
    RestaurantCategory toEntity(RestaurantCategoryDTO restaurantCategoryDTO);


}
