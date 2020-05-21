package com.revature.data;


import com.revature.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import static com.revature.utility.LoggerSingleton.getLogger;
import static com.revature.utility.SQLBuilder.insertInto;
import static com.revature.utility.SQLBuilder.updateSQL;

public class EmployeeDAO extends DAO<Employee> {

    private static final String TABLE_NAME = "employees";


    @Override
    PreparedStatement extractData(PreparedStatement ps, Employee employee) throws SQLException {
        getLogger(EmployeeDAO.class).debug("Extracting employee data");
        assert (ps != null & employee != null);
        ps.setString(1, employee.getUsername());
        ps.setString(2, employee.getFirstName());
        ps.setString(3, employee.getLastName());
        ps.setString(4, employee.hashed());
        ps.setInt(5, employee.getDepartment().getId());
        ps.setInt(6, employee.getRole().getId());
        return ps;
    }


    @Override
    Employee setData(ResultSet rs) throws SQLException {
        getLogger(EmployeeDAO.class).info("Setting employee data");
        Employee employee = new Employee(rs);
        getLogger(EmployeeDAO.class).debug(employee);
        return employee;
    }

    @Override
    void extractID(Employee employee, ResultSet rs) throws SQLException {
        if (rs.next()) {
            getLogger(EmployeeDAO.class).info("Extracting employee ID");
            employee.setId(rs.getInt(1));
        }
    }


    public boolean insert(Employee employee) {
        String sql = insertInto(TABLE_NAME, "username", "first_name", "last_name", "password", "department", "role");
        getLogger(EmployeeDAO.class).debug("Adding " + employee);
        return super.insert(employee, TABLE_NAME, sql);
    }


    public Employee getEmployeeByID(int id) {

        getLogger(EmployeeDAO.class).info("Getting employee using ID");
        return super.getById(id, TABLE_NAME);

    }


    public Set<Employee> getAll() {
        return super.getAll(TABLE_NAME);

    }

    public Employee getUserByUserName(String username) {
        Set data = super.getFiltered(TABLE_NAME, "username", username);
        if (data.iterator().hasNext())
            return (Employee) data.iterator().next();
        return null;
    }

//    public Set<Employee> filterWithRole(Role role) {
//        try (Connection conn = cu.getConnection()) {
//            return super.getFiltered(TABLE_NAME, "role", String.valueOf(role), conn);
//        } catch (SQLException ex) {
//            getLogger(EmployeeDAO.class).error(ex);
//
//        }
//
//        return null;
//    }


    public Employee update(Employee employee) {
        StringBuilder builder = new StringBuilder();

        String sql = updateSQL(TABLE_NAME, "id", "employee_name", "first_name", "last_name", "password", "role");
        getLogger(EmployeeDAO.class).info("Updating to " + employee);
        getLogger(EmployeeDAO.class).debug("My SQL statement " + sql);
        try (Connection conn = super.getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, employee.getEmployeename());
//            pstmt.setString(2, employee.getFirstName());
//            pstmt.setString(3, employee.getLastName());
//            pstmt.setString(4, employee.getPassword());
//            pstmt.setString(5, String.valueOf(employee.getRole()));
//            pstmt.setInt(6, employee.getID());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                getLogger(EmployeeDAO.class).info("Employee successfully updated.");
                conn.commit();
                return employee;
            } else {
                getLogger(EmployeeDAO.class).info("No Employee found with that id.");
                conn.rollback();
            }


        } catch (SQLException ex) {
            getLogger(EmployeeDAO.class).error(ex.toString());
        }
        getLogger(EmployeeDAO.class).debug("Connection Closed");
        getLogger(EmployeeDAO.class).info("Employee not added");
        return null;
    }

    public boolean delete(Employee employee) {
        return super.delete(employee.getId(), TABLE_NAME);
    }

}
