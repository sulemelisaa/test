package tr.com.metix.testproject.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.metix.testproject.domain.Answer;
import tr.com.metix.testproject.domain.UserTest;
import tr.com.metix.testproject.domain.UserTestAnswer;
import tr.com.metix.testproject.service.dto.UserTestAnswerDTO;

@Mapper(componentModel = "spring", uses ={ UserTest.class, Answer.class})
public interface UserTestAnswerMapper {

    UserTestAnswerMapper INSTANCE = Mappers.getMapper(UserTestAnswerMapper.class);

    @Mapping(source = "usertest.id", target = "userTestId")
    @Mapping(source = "answer.id", target = "answerId")
    UserTestAnswerDTO usertestanswerTousertestanswerDTO(UserTestAnswer userTest);

    @Mapping(source = "userTestId", target = "usertest.id")
    @Mapping(source = "answerId", target = "answer.id")
    UserTestAnswer usertestanswerDTOToUsertestanswer(UserTestAnswerDTO userTestDTO);
}
