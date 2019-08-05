package tr.com.metix.testproject.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.metix.testproject.domain.Question;
import tr.com.metix.testproject.domain.Test;
import tr.com.metix.testproject.service.dto.QuestionDTO;


@Mapper(componentModel = "spring", uses = Test.class)
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Mapping(source = "test.id", target = "testId")
    QuestionDTO questionToQuestionDTO(Question question);

    @Mapping(source = "testId", target = "test.id")
    Question questionDTOToQuestion(QuestionDTO questionDTO);

}
