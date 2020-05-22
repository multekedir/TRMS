package com.revature.utility;

import com.revature.data.DAOFactory;
import com.revature.models.Department;
import com.revature.models.Role;

import static com.revature.services.EmployeeService.*;

public class Setup {
    static Role dev = new Role("Developer");
    static Role benco = new Role("BenCo");
    static Role manager = new Role("Manager");
    static Role supervisor = new Role("Supervisor");
    static Role hr = new Role("HR");
    static Role accountant = new Role("Accountant");

    static Department GIS = new Department("Global Information System");


    public static void clear() {
        DAOFactory.geEmployeeDAO().TRUNCATE("EMPLOYEES");
        DAOFactory.getRoleDAO().TRUNCATE("ROLE");
        DAOFactory.getRoleDAO().TRUNCATE("FORMS");
        DAOFactory.getRoleDAO().TRUNCATE("DEP_MANAGER");
        DAOFactory.getRoleDAO().TRUNCATE("ROLE_SUPER");
        DAOFactory.getRoleDAO().TRUNCATE("DEPARTMENTS");
        DAOFactory.getRoleDAO().TRUNCATE("FORMS");
    }

    public static void addEmployees() {
        addEmployee("mkedir", "Multezem", "Kedir", "password", dev, GIS);
        addEmployee("jfreeman", "Josephine", "Freeman", "password", benco, GIS);
        addEmployee("ardalatonicooper", "Ardala", "Cooper", "password", manager, GIS);
        addEmployee("almondybriana", "Briana", "Gutierrez", "password", supervisor, GIS);
        addEmployee("jfreeman", "Josephine", "Freeman", "password", hr, GIS);
        addEmployee("ardalatonicooper", "Ardala", "Cooper", "password", accountant, GIS);
        addEmployee("almondybriana", "Briana", "Gutierrez", "password", dev, GIS);
        addEmployee("nickwalsh_86", "Nick", "Walsh", "password", dev, GIS);
        addSupervisor(dev, getEmployeeByUserName("almondybriana"));
        addDepartmentHead(GIS, getEmployeeByUserName("ardalatonicooper"));

    }

//    public static void main(String[] args) {
//        clear();
//        addEmployees();
//    }

}
