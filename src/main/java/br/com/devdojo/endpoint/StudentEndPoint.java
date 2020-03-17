package br.com.devdojo.endpoint;

import br.com.devdojo.error.CustomErrorType;
import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("students")
public class StudentEndPoint {
    private final StudentRepository dao;

    @Autowired
    public StudentEndPoint(StudentRepository dao) {
        this.dao = dao;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        verifyIfStudentExists(id);
        Student std = dao.findById(id).get();
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> findStudentsByName(@PathVariable String name) {
        return new ResponseEntity<>(dao.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student std) {
        return new ResponseEntity<>(dao.save(std), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        verifyIfStudentExists(id);
        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student std) {
        verifyIfStudentExists(std.getId());
        dao.save(std);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfStudentExists(Long id) {
        try {
            if (dao.findById(id).get() == null) { }
        }catch(NoSuchElementException err){
            throw new ResourceNotFoundException("ID not found for: " + id);
        }
    }
}

