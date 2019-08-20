package tr.com.metix.testproject.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.metix.testproject.domain.Order;
import tr.com.metix.testproject.service.dto.OrderDTO;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "user.id", target = "userId")
    OrderDTO toDTO(Order order);

    @Mapping(source = "userId" ,  target = "user.id")
    Order toEntity(OrderDTO orderDTO);


}
