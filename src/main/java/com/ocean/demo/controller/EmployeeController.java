package com.ocean.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ocean.demo.model.Employee;
import com.ocean.demo.model.User;
import com.ocean.demo.service.EmployeeServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin
@RestController
public class EmployeeController {
	
	@Autowired
    EmployeeServiceImpl service;
	
	//@GetMapping(produces = "application/json")
	
	/*
	 * @RequestMapping(value= "/employee/validateLogin" , method= RequestMethod.GET)
	 * public User validateLogin() { return new
	 * User("User successfully authenticated"); }
	 */
	 
 
    @RequestMapping(value= "/employee/all", method= RequestMethod.GET)
    public List<Employee> getEmployees() {
        System.out.println(this.getClass().getSimpleName() + " - Get all employees service is invoked.");
        return service.getEmployees();
    }
 
    @RequestMapping(value= "/employee/{id}", method= RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable int id) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - Get employee details by id is invoked.");
 
        Optional<Employee> emp =  service.getEmployeeById(id);
        if(!emp.isPresent())
            throw new Exception("Could not find employee with id- " + id);
 
        return emp.get();
    }
 
    @RequestMapping(value= "/employee/add", method= RequestMethod.POST)
    public Employee createEmployee(@RequestBody Employee newemp) {
        System.out.println(this.getClass().getSimpleName() + " - Create new employee method is invoked.");
        return service.addNewEmployee(newemp);
    }
 
    @RequestMapping(value= "/employee/update/{id}", method= RequestMethod.PUT)
    public Employee updateEmployee(@RequestBody Employee updemp, @PathVariable int id) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - Update employee details by id is invoked.");
 
        Optional<Employee> emp =  service.getEmployeeById(id);
        if (!emp.isPresent())
            throw new Exception("Could not find employee with id- " + id);
 
        /* IMPORTANT - To prevent the overriding of the existing value of the variables in the database, 
         * if that variable is not coming in the @RequestBody annotation object. */    
        if(updemp.getName() == null || updemp.getName().isEmpty())
            updemp.setName(emp.get().getName());
        if(updemp.getDesignation() == null || updemp.getDesignation().isEmpty())
            updemp.setDesignation(emp.get().getDesignation());
        if(updemp.getSalary() == 0)
            updemp.setSalary(emp.get().getSalary());
 
        // Required for the "where" clause in the sql query template.
        updemp.setId(id);
        return service.updateEmployee(updemp);
    }
 
    @RequestMapping(value= "/employee/delete/{id}", method= RequestMethod.DELETE)
    public void deleteEmployeeById(@PathVariable int id) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - Delete employee by id is invoked.");
 
        Optional<Employee> emp =  service.getEmployeeById(id);
        if(!emp.isPresent())
            throw new Exception("Could not find employee with id- " + id);
 
        service.deleteEmployeeById(id);
    }
 
    @RequestMapping(value= "/employee/deleteall", method= RequestMethod.DELETE)
    public void deleteAll() {
        System.out.println(this.getClass().getSimpleName() + " - Delete all employees is invoked.");
        service.deleteAllEmployees();
    }
}



/*
 * package com.ocean.demo.controller;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.DeleteMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.ocean.demo.model.Employee;
 * 
 * @CrossOrigin(origins = "http://localhost:4200")
 * 
 * @RestController //@RestController("/employees") public class
 * EmployeeController {
 * 
 * private List<Employee> employees = createList();
 * 
 * //@RequestMapping(method = RequestMethod.GET, produces = "application/json")
 * 
 * @RequestMapping(value= "/employee/all", method= RequestMethod.GET) public
 * List<Employee> firstPage() { return employees; }
 * 
 * @SuppressWarnings("unlikely-arg-type")
 * 
 * @DeleteMapping(value = "/{id}" ) public Employee delete(@PathVariable("id")
 * int id) { Employee deletedEmp = null; for (Employee emp : employees) { if
 * (emp.getId()==(id)) { employees.remove(emp); deletedEmp = emp; break; } }
 * return deletedEmp; }
 * 
 * @PostMapping public Employee create(@RequestBody Employee user) {
 * employees.add(user); System.out.println(employees); return user; }
 * 
 * 
 * private static List<Employee> createList() { List<Employee> tempEmployees =
 * new ArrayList<>(); Employee emp1 = new Employee(); emp1.setName("emp1");
 * emp1.setDesignation("manager"); emp1.setId(1); emp1.setSalary(3000);
 * 
 * Employee emp2 = new Employee(); emp2.setName("emp2");
 * emp2.setDesignation("developer"); emp2.setId(2); emp2.setSalary(3000);
 * tempEmployees.add(emp1); tempEmployees.add(emp2); return tempEmployees; }
 * 
 * }
 */
