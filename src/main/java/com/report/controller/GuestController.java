package com.report.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.report.dto.Department;
import com.report.dto.Professor;
import com.report.dto.Student;
import com.report.dto.User;
import com.report.mapper.DepartmentMapper;
import com.report.mapper.ProfessorMapper;
import com.report.mapper.StudentMapper;
import com.report.mapper.TaMapper;

//아직 로그인 하지 않은 사용자를 위한 페이지를 구현한다.
@Controller
@RequestMapping("guest")
public class GuestController {

	@Autowired ProfessorMapper professorMapper;
	@Autowired TaMapper taMapper;
	@Autowired DepartmentMapper departmentMapper;
	@Autowired StudentMapper studentMapper;


	@RequestMapping({"/", "index"})
	public String index() {
		return "guest/index"; //로그인 하지 않은 사용자를 위한 첫 페이지 URL
	}

	@RequestMapping("login")
	public String login(Model model, HttpServletRequest request) {

		return "guest/login"; //로그인 페이지 UR
	}

	@RequestMapping("option")
	public static String option() {
		return "guest/option";
	}

	//교수회원가입
	@GetMapping(value="professorsignup")
	public String professorcreate(Model model) {
		Professor professor = new Professor();
		List<Department> departments = departmentMapper.findAll();
		model.addAttribute("professor", professor);
		model.addAttribute("departments", departments);
		return "professor/signup";
	}

	@PostMapping(value="professorsignup")
	public String professorcreate(Model model, Professor professor) {
		professorMapper.insert(professor);
		return "guest/login";
	}


	//학생회원가입
	@GetMapping(value="studentsignup")
	public String studentcreate(Model model) {
		Student student = new Student();
		User user = new User();
		List<Department> departments = departmentMapper.findAll();
		model.addAttribute("student", student);
		model.addAttribute("departments", departments);
		return "student/signup";
	}

	@PostMapping(value="studentsignup")
	public String studentcreate(Model model, Student student) {
		studentMapper.insert(student);
		return "guest/login";
	}

}

