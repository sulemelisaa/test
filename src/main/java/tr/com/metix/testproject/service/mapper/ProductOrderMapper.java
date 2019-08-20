
package tr.com.metix.testproject.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.metix.testproject.domain.ProductOrder;
import tr.com.metix.testproject.service.dto.ProductOrderDTO;

@Mapper(componentModel = "spring", uses = {ProductMapper.class,OrderMapper.class})
public interface ProductOrderMapper {
    ProductOrderMapper INSTANCE = Mappers.getMapper(ProductOrderMapper.class);

    @Mapping(source = "products", target = "products")
    @Mapping(source = "orders", target = "orders")
    ProductOrderDTO toDTO(ProductOrder productOrder);

    @Mapping(source = "products" ,  target = "products")
    @Mapping(source = "orders" ,  target = "orders")
    ProductOrder toEntity(ProductOrderDTO productOrderDTO);

}
