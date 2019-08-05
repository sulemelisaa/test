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

    @Mapping(source = "test", target = "testDTOS")
    QuestionDTO questionToQuestionDTO(Question question);

    @Mapping(source = "testDTOS" ,  target = "test")
    Question questionDTOToQuestion(QuestionDTO questionDTO);

}
