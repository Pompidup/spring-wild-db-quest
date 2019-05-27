package com.wildcodeschool.myProjectWithDB.controllers;

        import com.wildcodeschool.myProjectWithDB.entities.Schools;
        import com.wildcodeschool.myProjectWithDB.repositories.SchoolsRepository;
        import org.springframework.http.HttpStatus;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;




        import java.sql.Date;
        import java.util.List;

@Controller
@ResponseBody
public class SchoolsController {

    @PostMapping("/api/schools")
    @ResponseStatus(HttpStatus.CREATED)
    public Schools store(
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam String country
    ) {
        int idGeneratedByInsertion = SchoolsRepository.insert(
                name,
                capacity,
                country
        );
        return SchoolsRepository.selectById(idGeneratedByInsertion);
    }

    @PutMapping("/api/schools/{id}")
    public Schools update(
            @PathVariable int id,
            @RequestParam String name,
            @RequestParam Integer capacity,
            @RequestParam String country
    ) {
        Schools schools = SchoolsRepository.selectById(id);
        SchoolsRepository.update(
                id,
                name != null ? name : schools.getName(),
                capacity != null ? capacity : schools.getCapacity(),
                country != null ?country : schools.getCountry()
        );
        return SchoolsRepository.selectById(id);
    }
}
