package objects;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by Danya on 26.10.2015.
 */
public class Department
{
    private Integer id;
    private Integer headId;
    private String name;
    private String description;
    private HashSet<Employee> employees = new HashSet<>(0);
    private HashSet<Vacation> vacations = new HashSet<>(0);
    private Employee employeeHead;

    public Department() {
        //Used by Hibernate
    }

    public void print(){
        System.out.println( name + " " + description);
    }

    public Department(String name)
    {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHeadId() {
        return headId;
    }

    public void setHeadId(Integer headId) {
        this.headId = headId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashSet<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees.addAll(employees);
    }

    public HashSet<Vacation> getVacations() {
        return vacations;
    }

    public void setVacations(Collection<Vacation> vacations) {
        this.vacations.addAll(vacations);
    }

    public Employee getEmployeeHead() {
        return employeeHead;
    }

    public void setEmployeeHead(Employee employeeHead) {
        this.employeeHead = employeeHead;
    }


}
