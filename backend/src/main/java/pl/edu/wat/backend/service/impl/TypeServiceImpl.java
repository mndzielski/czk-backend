package pl.edu.wat.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.backend.repository.TypeRepository;
import pl.edu.wat.backend.service.TypeService;
import pl.edu.wat.domain.entity.Type;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    private TypeRepository repository;

    @Autowired
    public TypeServiceImpl(TypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Type> findAll() {
        return repository.findAll();
    }

}
