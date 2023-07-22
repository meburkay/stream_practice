package com.cydeo.streampractice.practice;

import com.cydeo.streampractice.model.*;
import com.cydeo.streampractice.service.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Practice {

    public static CountryService countryService;
    public static DepartmentService departmentService;
    public static EmployeeService employeeService;
    public static JobHistoryService jobHistoryService;
    public static JobService jobService;
    public static LocationService locationService;
    public static RegionService regionService;

    public Practice(CountryService countryService, DepartmentService departmentService,
                    EmployeeService employeeService, JobHistoryService jobHistoryService,
                    JobService jobService, LocationService locationService,
                    RegionService regionService) {

        Practice.countryService = countryService;
        Practice.departmentService = departmentService;
        Practice.employeeService = employeeService;
        Practice.jobHistoryService = jobHistoryService;
        Practice.jobService = jobService;
        Practice.locationService = locationService;
        Practice.regionService = regionService;

    }

    // You can use the services above for all the CRUD (create, read, update, delete) operations.
    // Above services have all the required methods.
    // Also, you can check all the methods in the ServiceImpl classes inside the service.impl package, they all have explanations.

    // Display all the employees
    public static List<Employee> getAllEmployees() {
        return employeeService.readAll();
    }

    // Display all the countries
    public static List<Country> getAllCountries() {
        //TODO Implement the method +++

        return countryService.readAll();
    }

    // Display all the departments
    public static List<Department> getAllDepartments() {
        //TODO Implement the method +++
        return departmentService.readAll();
    }

    // Display all the jobs
    public static List<Job> getAllJobs() {
        //TODO Implement the method +++
        return jobService.readAll();
    }

    // Display all the locations
    public static List<Location> getAllLocations() {
        //TODO Implement the method +++
        return locationService.readAll();
    }

    // Display all the regions
    public static List<Region> getAllRegions() {
        //TODO Implement the method +++
        return regionService.readAll();
    }

    // Display all the job histories
    public static List<JobHistory> getAllJobHistories() {
        //TODO Implement the method +++
        return jobHistoryService.readAll();
    }

    // Display all the employees' first names
    public static List<String> getAllEmployeesFirstName() {
        //TODO Implement the method +++

        return employeeService.readAll().stream()
                .map(Employee::getFirstName)
                .collect(Collectors.toList());
    }

    // Display all the countries' names
    public static List<String> getAllCountryNames() {
        //TODO Implement the method +++
        return countryService.readAll().stream()
                .map(Country::getCountryName)
                .collect(Collectors.toList());
    }

    // Display all the departments' managers' first names
    public static List<String> getAllDepartmentManagerFirstNames() {
        //TODO Implement the method +++
        return departmentService.readAll().stream()
                .map(Department::getManager)
                .map(Employee::getFirstName)
                .collect(Collectors.toList());
    }

    // Display all the departments where manager name of the department is 'Steven'
    public static List<Department> getAllDepartmentsWhichManagerFirstNameIsSteven() {
        //TODO Implement the method +++
        return departmentService.readAll().stream()
                .filter(department ->
                        department.getManager().getFirstName().split(" ")[0].equals("Steven")
                )
                .collect(Collectors.toList());

    }

    // Display all the departments where postal code of the location of the department is '98199'
    public static List<Department> getAllDepartmentsWhereLocationPostalCodeIs98199() {
        //TODO Implement the method +++
        return departmentService.readAll().stream()
                .filter(department -> department.getLocation().getPostalCode().equals("98199"))
                .collect(Collectors.toList());
    }

    // Display the region of the IT department
    public static Region getRegionOfITDepartment() throws Exception {
        //TODO Implement the method +++
        return departmentService.readAll().stream()
                .filter(department -> department.getDepartmentName().equals("IT"))
                .map(department -> department.getLocation().getCountry().getRegion())
                .findFirst().get();
    }

    // Display all the departments where the region of department is 'Europe'
    public static List<Department> getAllDepartmentsWhereRegionOfCountryIsEurope() {
        //TODO Implement the method +++
        return departmentService.readAll().stream()
                .filter(department -> department.getLocation().getCountry().getRegion().getRegionName().equals("Europe"))
                .collect(Collectors.toList());
    }

    // Display if there is any employee with salary less than 1000. If there is none, the method should return true
    public static boolean checkIfThereIsNoSalaryLessThan1000() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .noneMatch(employee -> employee.getSalary()<1000);
    }

    // Check if the salaries of all the employees in IT department are greater than 2000 (departmentName: IT)
    public static boolean checkIfThereIsAnySalaryGreaterThan2000InITDepartment() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getDepartmentName().equals("IT"))
                .anyMatch(employee -> employee.getSalary()>2000);
    }

    // Display all the employees whose salary is less than 5000
    public static List<Employee> getAllEmployeesWithLessSalaryThan5000() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary()<5000)
                .collect(Collectors.toList());
    }

    // Display all the employees whose salary is between 6000 and 7000
    public static List<Employee> getAllEmployeesSalaryBetween() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary()>6000 && employee.getSalary()<7000)
                .collect(Collectors.toList());
    }

    // Display the salary of the employee Grant Douglas (lastName: Grant, firstName: Douglas)
    public static Long getGrantDouglasSalary() throws Exception {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> employee.getFirstName().equals("Douglas") && employee.getLastName().equals("Grant"))
                .map(Employee::getSalary)
                .findFirst().get();
    }

    // Display the maximum salary an employee gets
    public static Long getMaxSalary() throws Exception {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                //max(Comparator.comparing(Employee::getSalary)).get().getSalary();
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .findFirst().get().getSalary();
    }

    // Display the employee(s) who gets the maximum salary
    public static List<Employee> getMaxSalaryEmployee() {
        //TODO Implement the method +++ I did it but it is long. I think I can solve this in a short way.
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() == employeeService.readAll().stream().max(Comparator.comparing(Employee::getSalary)).get().getSalary())
                .collect(Collectors.toList());
//                employeeService.readAll().stream()
//                .sorted(Comparator.comparing(Employee::getSalary).reversed())
//                .findFirst().get().getSalary()
    }

    // Display the max salary employee's job
    public static Job getMaxSalaryEmployeeJob() throws Exception {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                //.max(Comparator.comparing(Employee::getSalary)).get().getJob();
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .findFirst().get().getJob();
    }

    // Display the max salary in Americas Region
    public static Long getMaxSalaryInAmericasRegion() throws Exception {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getLocation().getCountry().getRegion().getRegionName().equals("Americas"))
                .max(Comparator.comparing(Employee::getSalary)).get().getSalary();

    }

    // Display the second maximum salary an employee gets
    public static Long getSecondMaxSalary() throws Exception {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map(Employee::getSalary)
                .distinct()
                .collect(Collectors.toList()).get(1);
    }

    // Display the employee(s) who gets the second maximum salary
    public static List<Employee> getSecondMaxSalaryEmployee() {
                //TODO Implement the method +++
       return employeeService.readAll().stream()
               /*.filter(employee -> Objects.equals(employeeService.readAll().stream()
                       .sorted(Comparator.comparing(Employee::getSalary).reversed())
                       .map(Employee::getSalary)
                       .distinct()
                       .collect(Collectors.toList()).get(1), employee.getSalary()))*/
                .filter(employee -> employee.getSalary() == employeeService.readAll().stream()
                        .sorted(Comparator.comparing(Employee::getSalary).reversed())
                        .map(Employee::getSalary)
                        .distinct()
                        .collect(Collectors.toList()).get(1).longValue())//we use longValue because == is working with primitive types correctly. If we did not use long instead of Long. It look at the objects and filter only that object.
                .collect(Collectors.toList());
    }

    // Display the minimum salary an employee gets
    public static Long getMinSalary() throws Exception {
        //TODO Implement the method +++ I need to study sorted and comparator topics. I did not understand the exact logic.
        return employeeService.readAll().stream().min(Comparator.comparing(Employee::getSalary)).orElseThrow().getSalary();


    }

    // Display the employee(s) who gets the minimum salary
    public static List<Employee> getMinSalaryEmployee() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() == employeeService.readAll().stream()
                        .map(Employee::getSalary).min(Comparator.naturalOrder()).orElseThrow().longValue())
                .collect(Collectors.toList());
    }

    // Display the second minimum salary an employee gets
    public static Long getSecondMinSalary() throws Exception {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList()).get(1);
    }

    // Display the employee(s) who gets the second minimum salary
    public static List<Employee> getSecondMinSalaryEmployee() {
        //TODO Implement the method +++
        /*return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() == employeeService.readAll().stream()
                        .map(Employee::getSalary)
                        .distinct()
                        .sorted(Comparator.naturalOrder())
                        .collect(Collectors.toList()).get(1).longValue())
                .collect(Collectors.toList());*/
        return employeeService.readAll().stream()
                .filter(employee -> {
                    try {
                        return employee.getSalary().longValue() == Practice.getSecondMinSalary().longValue();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    // Display the average salary of the employees
    public static Double getAverageSalary() {
        //TODO Implement the method +++
        return  employeeService.readAll().stream()
                        .map(Employee::getSalary)
                        //.reduce(Long::sum)
                        .reduce((a,b) -> (a+b))
                        .orElseThrow().doubleValue() //I change long to double before dividing.
                        /
                        //(long) employeeService.readAll().size());
                        employeeService.readAll().stream()
                        .count();
    }

    // Display all the employees who are making more than average salary
    public static List<Employee> getAllEmployeesAboveAverage() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() > Practice.getAverageSalary())
                .collect(Collectors.toList());
    }

    // Display all the employees who are making less than average salary
    public static List<Employee> getAllEmployeesBelowAverage() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() < Practice.getAverageSalary())
                .collect(Collectors.toList());
    }

    // Display all the employees separated based on their department id number
    public static Map<Long, List<Employee>> getAllEmployeesForEachDepartment() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment().getId()));

                }


    // Display the total number of the departments
    public static Long getTotalDepartmentsNumber() {
        //TODO Implement the method +++
        return (long) departmentService.readAll().size();
    }

    // Display the employee whose first name is 'Alyssa' and manager's first name is 'Eleni' and department name is 'Sales'
    public static Employee getEmployeeWhoseFirstNameIsAlyssaAndManagersFirstNameIsEleniAndDepartmentNameIsSales() throws Exception {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> employee.getFirstName().equals("Alyssa") && employee.getManager().getFirstName().equals("Eleni") && employee.getDepartment().getDepartmentName().equals("Sales"))
                .collect(Collectors.toList()).get(0);
    }

    // Display all the job histories in ascending order by start date
    public static List<JobHistory> getAllJobHistoriesInAscendingOrder() {
        //TODO Implement the method +++
        return jobHistoryService.readAll().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate))
                .collect(Collectors.toList());
    }

    // Display all the job histories in descending order by start date
    public static List<JobHistory> getAllJobHistoriesInDescendingOrder() {
        //TODO Implement the method +++
        return jobHistoryService.readAll().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate).reversed())
                .collect(Collectors.toList());
    }

    // Display all the job histories where the start date is after 01.01.2005
    public static List<JobHistory> getAllJobHistoriesStartDateAfterFirstDayOfJanuary2005() {
        //TODO Implement the method +++
        return jobHistoryService.readAll().stream()
                .filter(jobHistory -> jobHistory.getStartDate().isAfter(LocalDate.of(2005,1,1)) )
                .collect(Collectors.toList());
    }

    // Display all the job histories where the end date is 31.12.2007 and the job title of job is 'Programmer'
    public static List<JobHistory> getAllJobHistoriesEndDateIsLastDayOfDecember2007AndJobTitleIsProgrammer() {
        //TODO Implement the method +++
        return jobHistoryService.readAll().stream()
                .filter(jobHistory -> jobHistory.getEndDate().equals(LocalDate.of(2007,12,31)) && jobHistory.getJob().getJobTitle().equals("Programmer"))
                .collect(Collectors.toList());
    }

    // Display the employee whose job history start date is 01.01.2007 and job history end date is 31.12.2007 and department's name is 'Shipping'
    public static Employee getEmployeeOfJobHistoryWhoseStartDateIsFirstDayOfJanuary2007AndEndDateIsLastDayOfDecember2007AndDepartmentNameIsShipping() throws Exception {
        //TODO Implement the method +++
        return jobHistoryService.readAll().stream()
                .filter(jobHistory -> jobHistory.getStartDate().equals(LocalDate.of(2007,1,1)) && jobHistory.getEndDate().equals(LocalDate.of(2007,12,31)) && jobHistory.getDepartment().getDepartmentName().equals("Shipping"))
                .map(jobHistory -> jobHistory.getEmployee())
                .collect(Collectors.toList()).get(0);
    }

    // Display all the employees whose first name starts with 'A'
    public static List<Employee> getAllEmployeesFirstNameStartsWithA() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> employee.getFirstName().startsWith("A"))
                .collect(Collectors.toList());
    }

    // Display all the employees whose job id contains 'IT'
    public static List<Employee> getAllEmployeesJobIdContainsIT() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> employee.getJob().getId().contains("IT"))
                .collect(Collectors.toList());
    }

    // Display the number of employees whose job title is programmer and department name is 'IT'
    public static Long getNumberOfEmployeesWhoseJobTitleIsProgrammerAndDepartmentNameIsIT() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> employee.getJob().getJobTitle().equals("Programmer") && employee.getDepartment().getDepartmentName().equals("IT"))
                .count();
    }

    // Display all the employees whose department id is 50, 80, or 100
    public static List<Employee> getAllEmployeesDepartmentIdIs50or80or100() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getId().longValue() == 50 || employee.getDepartment().getId().longValue() == 80 || employee.getDepartment().getId().longValue() == 100)
                .collect(Collectors.toList());
    }

    // Display the initials of all the employees
    // Note: You can assume that there is no middle name
    public static List<String> getAllEmployeesInitials() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .map(employee -> employee.getFirstName().charAt(0)+""+employee.getLastName().charAt(0))
                .collect(Collectors.toList());
    }

    // Display the full names of all the employees
    public static List<String> getAllEmployeesFullNames() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .map(employee -> employee.getFirstName()+" "+employee.getLastName())
                .collect(Collectors.toList());
    }

    // Display the length of the longest full name(s)
    public static Integer getLongestNameLength() throws Exception {
        //TODO Implement the method +++
        return Practice.getAllEmployeesFullNames().stream()
                .map(String::length)
                .max(Comparator.naturalOrder()).orElseThrow();

    }

    // Display the employee(s) with the longest full name(s)
    public static List<Employee> getLongestNamedEmployee() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> {
                    try {
                        return (employee.getFirstName()+" "+employee.getLastName()).length() == Practice.getLongestNameLength();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    // Display all the employees whose department id is 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIs90or60or100or120or130() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().getId() == 90 || employee.getDepartment().getId() == 60 || employee.getDepartment().getId() == 100 || employee.getDepartment().getId() == 120 || employee.getDepartment().getId() == 130)
                .collect(Collectors.toList());
    }

    // Display all the employees whose department id is NOT 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIsNot90or60or100or120or130() {
        //TODO Implement the method +++
        return employeeService.readAll().stream()
                .dropWhile(employee -> employee.getDepartment().getId() == 90 || employee.getDepartment().getId() == 60 || employee.getDepartment().getId() == 100 || employee.getDepartment().getId() == 120 || employee.getDepartment().getId() == 130)
                .collect(Collectors.toList());
    }

}
