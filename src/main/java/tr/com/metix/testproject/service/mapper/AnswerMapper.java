package tr.com.metix.testproject.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.metix.testproject.domain.Answer;
import tr.com.metix.testproject.domain.Question;
import tr.com.metix.testproject.service.dto.AnswerDTO;
import tr.com.metix.testproject.service.dto.QuestionDTO;

@Mapper(componentModel = "spring", uses = Question.class)

public interface AnswerMapper {

    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    @Mapping(source = "question", target = "questionDTOS")
    AnswerDTO answerToAnswerDTO(Answer answer);

    @Mapping(source = "questionDTOS", target = "question")
    Answer answerDTOToAnswer(AnswerDTO answerDTO);
}
