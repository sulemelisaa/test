package tr.com.metix.testproject.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.metix.testproject.domain.*;
import tr.com.metix.testproject.service.dto.UserTestDTO;

@Mapper(componentModel = "spring", uses = {Test.class})

public interface UserTestMapper
{

    UserTestMapper INSTANCE = Mappers.getMapper(UserTestMapper.class);

 //   @Mapping(source = "user", target = "userDTOS")
    @Mapping(source = "test", target = "testDTOS")
    UserTestDTO userTestToUserTestDTO(UserTest userTest);

 //   @Mapping(source = "userDTOS", target = "user")
    @Mapping(source = "testDTOS", target = "test")
    UserTest userTestDTOToUserTest(UserTestDTO userTestDTO);
}
