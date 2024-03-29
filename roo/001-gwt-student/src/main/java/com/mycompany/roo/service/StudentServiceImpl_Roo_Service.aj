// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.roo.service;

import com.mycompany.roo.domain.Student;
import com.mycompany.roo.repository.StudentRepo;
import com.mycompany.roo.service.StudentServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StudentServiceImpl_Roo_Service {
    
    declare @type: StudentServiceImpl: @Service;
    
    declare @type: StudentServiceImpl: @Transactional;
    
    @Autowired
    StudentRepo StudentServiceImpl.studentRepo;
    
    public long StudentServiceImpl.countAllStudents() {
        return studentRepo.count();
    }
    
    public void StudentServiceImpl.deleteStudent(Student student) {
        studentRepo.delete(student);
    }
    
    public Student StudentServiceImpl.findStudent(Long id) {
        return studentRepo.findOne(id);
    }
    
    public List<Student> StudentServiceImpl.findAllStudents() {
        return studentRepo.findAll();
    }
    
    public List<Student> StudentServiceImpl.findStudentEntries(int firstResult, int maxResults) {
        return studentRepo.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void StudentServiceImpl.saveStudent(Student student) {
        studentRepo.save(student);
    }
    
    public Student StudentServiceImpl.updateStudent(Student student) {
        return studentRepo.save(student);
    }
    
}
