package tr.com.metix.testproject.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.metix.testproject.domain.Restaurant;
import tr.com.metix.testproject.service.dto.RestaurantDTO;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface RestaurantMapper
{
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(source = "user.id", target = "user")
    RestaurantDTO toDTO(Restaurant restaurant);

    @Mapping(source = "user", target = "user.id")
    Restaurant toEntity(RestaurantDTO restaurantDTO);

}
