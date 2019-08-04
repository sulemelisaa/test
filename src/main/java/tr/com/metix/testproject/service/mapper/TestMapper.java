package tr.com.metix.testproject.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.metix.testproject.domain.Answer;
import tr.com.metix.testproject.domain.Test;
import tr.com.metix.testproject.service.dto.TestDTO;

@Mapper(componentModel = "spring")
public interface TestMapper  {

    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);

    TestDTO testToTestDTO(Test test);
    Test testDTOToTest(TestDTO testDTO);

}
