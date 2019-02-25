/**
 * Project Hibernate
 * Created by Shibkov Konstantin on 09.01.2019.
 */
package objects;

import java.util.Date;
import java.util.HashSet;

public class Vacation {
    private int id;
    private Employee employee;
    private int employee_id;
    private Date date_start;
    private Date date_end;
    private Employee who_approved;
    private Department departament;
    private int id_approved;
    private boolean is_approved;

    public Vacation() {
        //For Hibernate
    }

    public Vacation(Employee emp, Date start, Date end, Employee emp_approved, Department dep){
        this.employee = emp;
        this.date_start = start;
        this.date_end = end;
        this.who_approved = emp_approved;
        this.departament = dep;
        this.is_approved = true;
    }

    public boolean isIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }

      public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public Employee getWho_approved() {
        return who_approved;
    }

    public void setWho_approved(Employee who_approved) {
        this.who_approved = who_approved;
    }

    public int getId_approved() {
        return id_approved;
    }

    public void setId_approved(int id_approved) {
        this.id_approved = id_approved;
    }


    public Department getDepartament() {
        return departament;
    }

    public void setDepartament(Department departament) {
        this.departament = departament;
    }
}
