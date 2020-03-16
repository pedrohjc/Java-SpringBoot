package br.com.devdojo.endpoint;

import br.com.devdojo.error.CustomErrorType;
import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path="/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        Student std = dao.findById(id).get();
        if (std == null) {
            return new ResponseEntity<>(new CustomErrorType("Student Not Found!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dao, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student std) {
        return new ResponseEntity<>(dao.save(std), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        dao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student std) {
        dao.save(std);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

