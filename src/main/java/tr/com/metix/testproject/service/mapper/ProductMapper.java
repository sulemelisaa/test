package tr.com.metix.testproject.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.metix.testproject.domain.Product;
import tr.com.metix.testproject.service.dto.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "restaurantCategory.id", target = "restaurantCategoryId")
    ProductDTO toDTO(Product product);

    @Mapping(source = "restaurantCategoryId" ,  target = "restaurantCategory.id")
    Product toEntity(ProductDTO productDTO);

}
