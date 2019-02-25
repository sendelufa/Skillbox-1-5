package objects;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by Danya on 26.10.2015.
 */
public class Employee
{
    private Integer id;
    private Date hireDate;
    private Integer salary;
    private String name;
    private Department department;
    private Integer dep_id;
    private HashSet<Vacation> vacations = new HashSet<>(0);

    public Employee(){
        //Used by Hibernate
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getDep_id() {
        return dep_id;
    }

    public void setDep_id(Integer dep_id) {
        this.dep_id = dep_id;
    }

    public HashSet<Vacation> getVacations() {
        return vacations;
    }

    public void setVacations(Collection<Vacation> vacs) {
        this.vacations.addAll(vacs);
    }


}
