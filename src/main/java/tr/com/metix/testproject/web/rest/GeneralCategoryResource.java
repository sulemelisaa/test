package tr.com.metix.testproject.web.rest;

import org.springframework.web.bind.annotation.*;
import tr.com.metix.testproject.domain.GeneralCategory;
import tr.com.metix.testproject.service.GeneralCategoryService;
import tr.com.metix.testproject.service.dto.GeneralCategoryDTO;
import tr.com.metix.testproject.service.dto.RestaurantCategoryDTO;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GeneralCategoryResource {

    private final GeneralCategoryService generalCategoryService;

    public GeneralCategoryResource(GeneralCategoryService generalCategoryService) {
        this.generalCategoryService = generalCategoryService;
    }

    @GetMapping("/generalCategories")
    public List<GeneralCategoryDTO> selectGeneralCategory() {
        return generalCategoryService.getGeneralCategory();
    }


    @PostMapping("/generalCategorycreate")
    public GeneralCategoryDTO createGeneralCategory(@RequestBody GeneralCategoryDTO generalCategoryDTO) throws URISyntaxException {

        GeneralCategoryDTO generalCategoryDTO1 = generalCategoryService.createGC(generalCategoryDTO);
        return generalCategoryDTO1;
    }

    @DeleteMapping("/generalCategorydelete/{id}")
    public void deleteGeneralCategory(@PathVariable Long id) {

        generalCategoryService.deleteGeneralCategory(id);

    }

    @PutMapping("/generalCategoryupdate")
    public GeneralCategoryDTO updateGeneralCategory (@RequestBody GeneralCategoryDTO generalCategoryDTO) throws URISyntaxException {

        GeneralCategoryDTO generalCategoryDTO1 = generalCategoryService.updateGeneralCategory(generalCategoryDTO);
        return generalCategoryDTO1;
    }

}
