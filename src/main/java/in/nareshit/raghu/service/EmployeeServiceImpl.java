package in.nareshit.raghu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.exception.EmployeeNotFoundException;
import in.nareshit.raghu.model.Employee;
import in.nareshit.raghu.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	private EmployeeRepository repo;

	@Override
	public Integer saveEmployee(Employee e) {
		// TODO Auto-generated method stub
		return repo.save(e).getEmpId();
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Employee getOneEmployee(Integer id) {
		
		return repo.findById(id).orElseThrow(
				() -> new EmployeeNotFoundException("Employee not exists"));
		
		
		// TODO Auto-generated method stub
		/*
		 * Optional<Employee> opt = repo.findById(id); if(opt.isPresent()) { return
		 * opt.get(); } else { throw new
		 * EmployeeNotFoundException("Employee not exists"); }
		 */
	}

	@Override
	public void deleteEmployee(Integer id) {
		// TODO Auto-generated method stub
		Employee e = getOneEmployee(id);
		repo.delete(e);
	}
}
