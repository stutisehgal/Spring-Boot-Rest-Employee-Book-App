package com.trg.boot.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.trg.model.AppRes;
import com.trg.model.Employee;

@RestController //creating a controller class with this annotation
@RequestMapping("/employees") // controller class request mapping for servlet URLs
public class EmployeeController {

	Map<Integer, Employee> data;
	public EmployeeController() {
		
		// data flows into the MVC Spring application from the Controller class and gets displayed by JSP
		data = new TreeMap<Integer,Employee>();
		
		data.put(100, new Employee(100,"Abhishek",23000));
		data.put(200, new Employee(200,"Birbal",34000));
		data.put(300, new Employee(300,"Charlie",13000));
		data.put(400, new Employee(400,"Deepak",14000));
		
	}
	
	//@RequestMapping(value= "{eid}", method = RequestMethod.GET) // so that URL Empid = 101 will automatically enter here
	@GetMapping("{eid}")
	public ResponseEntity<?> getEmployee(@PathVariable("eid") int id)
	{
		Employee e = data.get(id);
		//return e;
		if(e==null) {
			return new ResponseEntity<AppRes>(new AppRes("SAVEFAILED", "Employee cannot be saved" + e),HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Employee> ( e,HttpStatus.ACCEPTED);
		}
	}
	
	@RequestMapping
	public Collection<Employee> getAllEmployee()
	{
		Collection<Employee> col = data.values();
		return col;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveEmployee(@RequestBody Employee e ){
		if(data.containsKey(e.getEmpId()))
		{
			//return "Cant Save";
			return new ResponseEntity<AppRes>(new AppRes("SAVEFAILED", "Employee cannot be saved" + e.getEmpId()),HttpStatus.BAD_REQUEST);
	}
		else{
			data.put(e.getEmpId(), e);
	//return "Done";
	//return new AppRes("SAVED", "Employee saved" + e.getId());
			return new ResponseEntity<AppRes>(new AppRes("SAVEFAILED", "Employee saved" + e.getEmpId()),HttpStatus.CREATED);
	}
		
	
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> uptEmployee(@RequestBody Employee e ){
		if(data.containsKey(e.getEmpId()))
	{
			data.put(e.getEmpId(), e);
			return new ResponseEntity<String>("Updated",HttpStatus.ACCEPTED);
	}
		else {
	//data.put(e.getId(), e);
	//return "Does not exist";
			return new ResponseEntity<String>("Does not exist",HttpStatus.CONFLICT);
			}

	}
	
	@RequestMapping(value= "{eid}", method = RequestMethod.DELETE)
	public String deleteEmployee(@PathVariable("eid") int id)
	{
		int em = id;
		if(data.containsKey(em)){
			data.remove(em);
			return "Done "+ em;
			}
		else{
			return "Not done";
			}
	}

		//
		//@RequestMapping("save")
		//public ModelAndView saveEmpform(@RequestParam("empid") int id,@RequestParam("name") String name,@RequestParam("salary") int salary)
		//{
		// if(data.containsKey(id))
		// {
		//
		// return new ModelAndView("response","mess","Emp Not Added. Already Existed");
		// }
		// else
		// {
		//
		// data.put(id, new Employee(id,name,salary));
		// return new ModelAndView("response","mess","Emp Saved");
		// }
		//
		//}
	
	@RequestMapping("id={eid}/name={name}/salary={salary}")
	public String saveEmp(@PathVariable("eid") int id,@PathVariable("name") String name,@PathVariable("salary") int salary)
	{
	//
	//
	if(data.containsKey(id))
	{

	return "Emp Already Exists";
	}
	else
	{

	data.put(id, new Employee(id,name,salary));
	//return new ModelAndView("response","mess","Emp Saved");
	return "Emp Saved";
	}
	
}
}
