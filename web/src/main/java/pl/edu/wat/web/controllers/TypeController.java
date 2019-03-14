package pl.edu.wat.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.backend.service.TypeService;
import pl.edu.wat.domain.entity.Type;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TypeController {

    private final TypeService service;

    @RequestMapping("/api/secure/types/find-all")
    public List<Type> findAllTypes() {
        return service.findAll();
    }
}
