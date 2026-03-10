package fieldworks.DSA.streamApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StreamApi1 {
	public static void main(String[] args) {
		////////////////////////////////////////////////////////////////////////////

		// List<Employee> employees = new ArrayList<>();
		//
		// employees.add(new Employee(25, "ram", "CS"));
		// employees.add(new Employee(30, "shyam", "Mechanical"));
		// employees.add(new Employee(55, "ashish", "IT"));
		// employees.add(new Employee(46, "karan", "IT"));
		// employees.add(new Employee(48, "ankita", "IT"));

		// List<Employee> itEmployees = new ArrayList<>();
		// for(Employee employee: employees) {
		// if(employee.getDepartment().equals("IT")) {
		// itEmployees.add(employee);
		// }
		// }

		// using Comparable
		// Collections.sort(itEmployees);
		// System.out.println(itEmployees);

		// using Comparator

		// Sort by name using Comparator
		// Collections.sort(itEmployees, new java.util.Comparator<Employee>(){
		// public int compare(Employee e1, Employee e2) {
		// return e1.getName().compareTo(e2.getName());
		// }
		// });

		// itEmployees.sort(Comparator.comparing(Employee::getName));
		//

		// // Sort by age using Comparator
		// Collections.sort(itEmployees, new Comparator<Employee>() {
		// @Override
		// public int compare(Employee e1, Employee e2) {
		// return Integer.compare(e1.getAge, e2.getAge); // Sort by age
		// }
		// });

		//
		// for(Employee employee:itEmployees) {
		// System.out.println(employee.getName());
		// }

		// using stream API

		// List<Employee> itEmployees = employees.stream()
		// .filter(employee -> employee.getDepartment().equals("IT"))
		//// .sorted((e1,e2) -> e1.getName().compareTo(e2.getName()))
		//// .sorted(Comparator.comparing(Employee::getName))
		// .sorted()
		// .collect(Collectors.toList());
		//
		// itEmployees.stream().forEach(employee->
		// System.out.println(employee.getName()));

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//////////////////////////////////////////////////////////////// Important
		//////////////////////////////////////////////////////////////// questions

		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(25, "ram", "CS", "Male", "Delhi", 20000));
		employees.add(new Employee(26, "arju", "CS", "Female", "Noida", 30000));
		employees.add(new Employee(27, "raju", "IT", "Male", "Delhi", 40000));
		employees.add(new Employee(28, "ankita", "IT", "Female", "Noida", 50000));
		employees.add(new Employee(31, "anchal", "IT", "Female", "Delhi", 60000));
		employees.add(new Employee(29, "shyam", "MA", "Male", "Noida", 80000));
		employees.add(new Employee(30, "sonam", "MA", "Female", "Delhi", 90000));

		// 1.Find list of employees whose name starts with alphabet A

		// List<Employee> empStartWithA = employees.stream().filter(emp ->
		// emp.getName().startsWith("a"))
		// .collect(Collectors.toList());
		// empStartWithA.forEach(emp -> System.out.println(emp.getName()));

		// 2.Group The employees By Department Names

		// // 2.1=> using loop
		// Map<String, List<Employee>> groupedByDept = new HashMap<>();
		// for (Employee emp : employees) {
		// if (!groupedByDept.containsKey(emp.getDepartment())) {
		// groupedByDept.put(emp.getDepartment(), new ArrayList<>());
		// }
		// groupedByDept.get(emp.getDepartment()).add(emp);
		// }

		// for (String dept : groupedByDept.keySet()) {
		// System.out.println(dept + " -> " + groupedByDept.get(dept));
		// }

		// 2.2=> Using stream
		// Map<String, List<Employee>> empgroupByDepa = employees.stream()
		//// .collect(Collectors.groupingBy(emp -> emp.getDepartment()));
		// .collect(Collectors.groupingBy(Employee::getDepartment));
		//
		// print using forEach
		//
		// for (Map.Entry<String, List<Employee>> empdepartment :
		// empgroupByDepa.entrySet()) {
		// for (Employee emp : empdepartment.getValue()) {
		// System.out.println(empdepartment.getKey() + " : " + emp.getName());
		// }
		// }
		//
		// print using stream
		// empgroupByDepa.entrySet().stream()
		// .forEach(entry->entry.getValue()
		// .forEach(emp-> System.out.println(entry.getKey()+":"+emp.getName())));
		//
		// print using stream and flatMap
		//
		// empgroupByDepa.entrySet().stream()
		// .flatMap(entry-> entry.getValue().stream()
		// .map(emp-> entry.getKey()+":"+ emp.getName()))
		// .forEach(System.out::println);

		// 3.Find the total count of employees using stream
		// Long employeecount = employees.stream().count();
		// System.out.println(employeecount);

		// 4.Find the max age of employees
		//// Optional<Employee> maxage =
		// employees.stream().max(Comparator.comparingInt(Employee::getAge));
		// Optional<Employee> maxage = employees.stream().max((e1,e2)->
		// Integer.compare(e1.getAge(), e2.getAge()));
		// maxage.ifPresent(employee-> System.out.println(employee.getAge()));
		//
		// using For loop
		//
		// int maxAge = Integer.MIN_VALUE;
		// for(Employee emp : employees){
		// maxAge = Math.max(maxAge,emp.getAge());
		// }
		// System.out.println(maxAge);

		// 5.Find all department names
		// List<String> departmentName = employees.stream().map(e->
		// e.getDepartment()).collect(Collectors.toList());
		// departmentName.stream().forEach(name-> System.out.println(name));

		// 6.Find the count of employee in each department
		// Map<String, Long> deptCountMap = employees.stream()
		// .collect(Collectors.groupingBy(Employee::getDepartment,
		// Collectors.counting()));
		//// .collect(Collectors.groupingBy(e-> e.getDepartment(),
		// Collectors.counting()));
		//
		// deptCountMap.entrySet().stream().forEach(entry ->
		// System.out.println(entry.getKey() + ":" + entry.getValue()));
		//
		// using For loop
		//
		// Map<String, Integer> deptCountMap = new HashMap<>();
		// for(Employee emp : employees){
		// deptCountMap.put(emp.getDepartment(),
		// deptCountMap.getOrDefault(emp.getDepartment(), 0)+1);
		// }

		// for(Map.Entry<String,Integer> entry : deptCountMap.entrySet()){
		// System.out.println(entry.getKey() + ":" + entry.getValue());
		// }

		// 7.Find the list of employees whose age is less than 30
		// List<Employee> lessthan30AgeEmp = employees.stream().filter(e->
		// e.getAge()<30).collect(Collectors.toList());
		// lessthan30AgeEmp.stream().forEach(e-> System.out.println(e.getAge()));

		// 8.Find the list of employees whose age is in between 26 and 31
		// List<Employee> AgeEmp = employees.stream().filter(e -> e.getAge() > 26 &&
		// e.getAge() < 31).collect(Collectors.toList());
		// AgeEmp.stream().forEach(e -> System.out.println(e.getAge()));

		// 9.Find the average age of male and female employee
		// Map<String, Double> averageAge=
		// employees.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getAge)));
		// averageAge.steam().forEach((k,v)-> System.out.println(k +" : "+ v));
		//
		// using for loop
		//
		// Map<String, Double> totalAge = new HashMap<>();
		// Map<String, Integer> countMap = new HashMap<>();

		// for (Employee emp : employees) {
		// totalAge.put(emp.getGender(), totalAge.getOrDefault(emp.getGender(), 0.0) +
		// emp.getAge());
		// countMap.put(emp.getGender(), countMap.getOrDefault(emp.getGender(), 0) + 1);
		// }
		// Map<String, Double> averageAge = new HashMap<>();
		// for (String gender : totalAge.keySet()) {
		// Double avg = totalAge.get(gender) / countMap.get(gender);
		// averageAge.put(gender, avg);
		// }
		// averageAge.forEach((k, v) -> System.out.println(k + ":" + v));

		// 10.Find the department who is having maximum number of employee
		// Map<String, Long> countdept =
		// employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
		// Collectors.counting()));
		// Optional<Map.Entry<String, Long>> maxdept =
		// countdept.entrySet().stream().max((e1,e2)-> Long.compare(e1.getValue(),
		// e2.getValue()));
		// maxdept.ifPresent(e-> System.out.println(e.getKey()+":"+e.getValue()));
		//
		// using for loop
		//
		// Map<String, Integer> countDept = new HashMap<>();
		// for (Employee emp : employees) {
		// countDept.put(emp.getDepartment(),
		// countDept.getOrDefault(emp.getDepartment(), 0) + 1);
		// }
		// String maxDept = null;
		// int maxCount = 0;
		// for (Map.Entry<String, Integer> entry : countDept.entrySet()) {
		// if (entry.getValue() > maxCount) {
		// maxDept = entry.getKey();
		// maxCount = entry.getValue();
		// }
		// }
		// System.out.println(maxDept + ":" + maxCount);

		// 11.Find the Employee who stays in Delhi and sort them by their names
		//// List<Employee> fromDelhi = employees.stream().filter(e->
		// e.getCity().equals("Delhi")).sorted(Comparator.comparing(Employee::getName)).collect(Collectors.toList());
		// List<Employee> fromDelhi = employees.stream().filter(e->
		// e.getCity().equals("Delhi")).sorted((e1,e2)->
		// e1.getName().compareTo(e2.getName())).collect(Collectors.toList());
		// fromDelhi.stream().forEach(e-> System.out.println(e.getName() + " : " +
		// e.getCity()));
		//
		// using for loop
		//
		// List<Employee> fromDelhi = new ArrayList<>();
		// for (Employee emp : employees) {
		// if (emp.getCity().equals("Delhi")) {
		// fromDelhi.add(emp);
		// }
		// }
		// fromDelhi.sort(Comparator.comparing(Employee::getName).reversed());
		// // Collections.sort(fromDelhi, new Comparator<Employee>() {
		// // @Override
		// // public int compare(Employee e1, Employee e2) {
		// // return e1.getName().compareTo(e2.getName());
		// // }
		// // });

		// fromDelhi.forEach(emp -> System.out.println(emp.getName() + ":" +
		// emp.getCity()));

		// 12. Find the average salary in all departments
		// Map<String, Double> empl =
		// employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
		// Collectors.averagingInt(Employee::getSalary)));
		// empl.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() +
		// ":" + entry.getValue()));
		//
		// using for loop
		//
		// Map<String, Double> salMap = new HashMap<>();
		// Map<String, Integer> countMap = new HashMap<>();
		// for (Employee emp : employees) {
		// salMap.put(emp.getDepartment(), salMap.getOrDefault(emp.getDepartment(), 0.0)
		// + emp.getSalary());
		// countMap.put(emp.getDepartment(), countMap.getOrDefault(emp.getDepartment(),
		// 0) + 1);
		// }
		// Map<String, Double> avgSalMap = new HashMap<>();
		// for (String dept : salMap.keySet()) {
		// Double avg = salMap.get(dept) / countMap.get(dept);
		// avgSalMap.put(dept, avg);
		// }
		// avgSalMap.forEach((e, v) -> System.out.println(e + ":" + v));

		// 13. Find the highest salary in each department
		// Map<String, Optional<Employee>> highestSalForEachDedpt =
		// employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
		// Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)))); //or
		// Collectors.maxBy((e1,e2)-> Integer.compare(e1.getSalary(), e2.getSalary()))
		// highestSalForEachDedpt.entrySet().stream().forEach(entry->
		// System.out.println(entry.getKey()+":"+entry.getValue().get().getSalary()));
		//
		// using loop
		//
		// Map<String, Integer> highSalary = new HashMap<>();
		// for (Employee emp : employees) {
		// if (highSalary.containsKey(emp.getDepartment())) {
		// if (emp.getSalary() > highSalary.get(emp.getDepartment())) {
		// highSalary.put(emp.getDepartment(), emp.getSalary());
		// }
		// } else {
		// highSalary.put(emp.getDepartment(), emp.getSalary());
		// }
		// }
		// highSalary.forEach((e, v) -> System.out.println(e + ":" + v));
		//
		// // optimized
		//
		// Map<String, Integer> highSalary = new HashMap<>();
		// for (Employee emp : employees) {
		// highSalary.merge(
		// emp.getDepartment(),
		// emp.getSalary(),
		// Integer::max);
		// }

		// highSalary.forEach((dept, salary) -> System.out.println(dept + " : " +
		// salary));

		// 14.Find the list of employee and sort them by their salary decreased order
		// List<Employee> emp =
		// employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).collect(Collectors.toList());
		// employees.stream().sorted((e1, e2) -> Integer.compare(e2.getSalary(),
		// e1.getSalary())).collect(Collectors.toList());
		// emp.stream().forEach(emp -> System.out.println(emp.getName() + ":" +
		// emp.getSalary()));
		//
		// using loop
		//
		// 1.
		// employees.sort(Comparator.comparing(Employee::getSalary).reversed());
		// // 2.
		// Collections.sort(employees, new Comparator<Employee>(){
		// @Override
		// public int compare(Employee e1, Employee e2){
		// return Integer.compare(e2.getAge(), e1.getAge());
		// }
		// });
		// employees.forEach(emp -> System.out.println(emp.getName() + ":" +
		// emp.getSalary()));

		// 15.Find the employee who has second lowest salary
		// Employee emp =
		// employees.stream().sorted(Comparator.comparing(Employee::getSalary)).skip(1).findFirst().get();
		// System.out.println(emp.getSalary());
		//
		// using loop
		//
		// 1.
		// employees.sort(Comparator.comparing(Employee::getSalary));
		// Employee emp = employees.get(1);
		// System.out.println(emp.getSalary());

		// 2.Best
		// Employee lowest = null;
		// Employee secondLowest = null;

		// for (Employee emp : employees) {
		// if (lowest == null || emp.getSalary() < lowest.getSalary()) {
		// secondLowest = lowest;
		// lowest = emp;
		// }
		// else if(secondLowest==null || emp.getSalary()<secondLowest.getSalary() &&
		// emp.getSalary() != lowest.getSalary()){
		// secondLowest=emp;
		// }
		// }
		// System.out.println(secondLowest.getSalary());

		// 16.from name="Shailendra" get more repated character
	}
}