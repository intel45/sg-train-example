package com.spring.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.datasource.Classroom;
 
/**
 * @author Eggsy - eggsy_at_eggsylife.co.uk
 *
 */
@Controller
public class StudentsController {
 
	public static final String STUDENTS_VIEW_KEY = "studentsView";
 
	@RequestMapping(method=RequestMethod.GET,value="/students")
	public ModelAndView showStudentsPage() {
		Classroom classroom = new Classroom("Class One");
        classroom.setRemark("this is a test remark");
		ModelAndView mav = new ModelAndView(STUDENTS_VIEW_KEY);
		mav.addObject("classRoom", classroom);
		return mav;
	}
}