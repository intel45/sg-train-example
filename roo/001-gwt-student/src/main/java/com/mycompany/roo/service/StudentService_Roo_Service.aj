// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.roo.service;

import com.mycompany.roo.domain.Student;
import com.mycompany.roo.service.StudentService;
import java.util.List;

privileged aspect StudentService_Roo_Service {
    
    public abstract long StudentService.countAllStudents();    
    public abstract void StudentService.deleteStudent(Student student);    
    public abstract Student StudentService.findStudent(Long id);    
    public abstract List<Student> StudentService.findAllStudents();    
    public abstract List<Student> StudentService.findStudentEntries(int firstResult, int maxResults);    
    public abstract void StudentService.saveStudent(Student student);    
    public abstract Student StudentService.updateStudent(Student student);    
}
