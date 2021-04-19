package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		
		
		System.out.println("==== TEST 1: Department findById ====");
		Department dep = departmentDao.findById(4);
		System.out.println(dep);
		
		System.out.println("\n==== TEST 2: Department findAll ====");
		List<Department> list = new ArrayList<>();
		list = departmentDao.findAll();
		for(Department d : list) {
			System.out.println(d);
		}
		
		System.out.println("\n==== TEST 3: Department insert ====");
		Department department = new Department(null, "Teste");
		departmentDao.insert(department);
		System.out.println("Inserted! Department Id:" + department.getId());
		
		System.out.println("\n==== TEST 4: Department update ====");
		dep = departmentDao.findById(9);
		dep.setName("Teste3");
		departmentDao.update(dep);
		System.out.println("Updated!");
		

	}

}
