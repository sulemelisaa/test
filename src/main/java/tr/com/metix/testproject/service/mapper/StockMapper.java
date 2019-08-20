package tr.com.metix.testproject.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.metix.testproject.domain.Stock;
import tr.com.metix.testproject.service.dto.StockDTO;

@Mapper(componentModel = "spring")
public interface StockMapper {
    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    @Mapping(source = "products.id", target = "productId")
    StockDTO toDTO(Stock stock);

    @Mapping(source = "productId" ,  target = "products.id")
    Stock toEntity(StockDTO stockDTO);
}
