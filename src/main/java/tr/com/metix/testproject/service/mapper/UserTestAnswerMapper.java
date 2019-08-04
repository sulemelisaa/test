package tr.com.metix.testproject.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.metix.testproject.domain.Answer;
import tr.com.metix.testproject.domain.Question;
import tr.com.metix.testproject.domain.UserTest;
import tr.com.metix.testproject.domain.UserTestAnswer;
import tr.com.metix.testproject.service.dto.UserTestAnswerDTO;
import tr.com.metix.testproject.service.dto.UserTestDTO;

@Mapper(componentModel = "spring", uses ={ UserTest.class, Answer.class})
public interface UserTestAnswerMapper {

    UserTestAnswerMapper INSTANCE = Mappers.getMapper(UserTestAnswerMapper.class);

    @Mapping(source = "usertest.id", target = "usertestid")
    @Mapping(source = "answer.id", target = "answerid")
    UserTestAnswerDTO usertestanswerTousertestanswerDTO(UserTestAnswer userTest);

    @Mapping(source = "usertestid", target = "usertest.id")
    @Mapping(source = "answerid", target = "answer.id")
    UserTestAnswer usertestanswerDTOToUsertestanswer(UserTestAnswerDTO userTestDTO);
}
