package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MomController{
	
	@RequestMapping(value="/")
	public String myTable() {
		return "myTable";
	}	
	
	@RequestMapping(value="/myProject.do")
	public String myProject() {
		return "myProject";
	}
	
	@RequestMapping(value="/teamProject.do")
	public String teamProject() {
		return "teamProject";
	}
	
	@RequestMapping(value="/insert.do")
	public String insert() {
		return "insert";
	}
	
	@RequestMapping(value="/update.do")
	public String update() {
		return "update";
	}
	
	
}