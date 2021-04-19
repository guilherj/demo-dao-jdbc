package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		/*
		System.out.println("==== TEST 1: seller findById ====");
		Department department = new Department(null, "Teste");
		departmentDao.insert(department);
		System.out.println("Inserted! Department Id:" + department.getId());
		*/
		
		System.out.println("==== TEST 1: Department findById ====");
		Department dep = departmentDao.findById(4);
		System.out.println(dep);
		
		System.out.println("\n==== TEST 2: Department findAll ====");
		List<Department> list = new ArrayList<>();
		list = departmentDao.findAll();
		for(Department d : list) {
			System.out.println(d);
		}

	}

}
