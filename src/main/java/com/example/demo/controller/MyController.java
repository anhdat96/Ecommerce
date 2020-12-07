package com.example.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/students")
@Api(value = "API to search Student from a Student Repository by different search parameters",
		description = "This API provides the capability to search Student from a Student Repository", produces = "application/json")
public class MyController {
	@Autowired
	private Ifactorial ifactorial;



	@GetMapping("/sum")
	Integer sum(@RequestParam("a")Integer a,@RequestParam("b")Integer b) {
		return a+b;
	}

	@GetMapping("/tinhgiaithua")
	public Integer factorial(@RequestParam("a") Integer one){
		int result = ifactorial.countfactorial(one);
		return result;

	}
}
