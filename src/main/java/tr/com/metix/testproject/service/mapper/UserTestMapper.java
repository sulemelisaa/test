package tr.com.metix.testproject.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.metix.testproject.domain.*;
import tr.com.metix.testproject.service.dto.AnswerDTO;
import tr.com.metix.testproject.service.dto.UserTestDTO;

@Mapper(componentModel = "spring", uses = {Test.class, User.class})

public interface UserTestMapper
{

    UserTestMapper INSTANCE = Mappers.getMapper(UserTestMapper.class);

    @Mapping(source = "user.id", target = "userid")
    @Mapping(source = "test.id", target = "testid")
    UserTestDTO userTestToUserTestDTO(UserTest userTest);

    @Mapping(source = "userid", target = "user.id")
    @Mapping(source = "testid", target = "test.id")
    UserTest userTestDTOToUserTest(UserTestDTO userTestDTO);
}
