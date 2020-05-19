package com.revature.data;

import com.revature.models.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import static com.revature.utility.LoggerSingleton.getLogger;
import static com.revature.utility.SQLBuilder.insertInto;
import static com.revature.utility.SQLBuilder.updateSQL;

public class DepartmentMangerDAO extends DAO<Department> {
    private static final String TABLE_NAME = "dep_manager";
    private int ID;

    @Override
    PreparedStatement extractData(PreparedStatement ps, Department department) throws SQLException {
        getLogger(DepartmentMangerDAO.class).debug("Extracting department data");
        assert (ps != null & department != null);
        ps.setInt(1, department.getId());
        ps.setInt(2, department.getHead().getId());
        return ps;
    }


    @Override
    Department setData(ResultSet rs) throws SQLException {
        getLogger(DepartmentMangerDAO.class).info("Setting department data");
        Department department = new Department();
        department.setupManger(rs);
        getLogger(DepartmentMangerDAO.class).debug(department);
        return department;
    }


    @Override
    void extractID(Department department, ResultSet rs) throws SQLException {
        if (rs.next()) {
            getLogger(DepartmentMangerDAO.class).info("Extracting department ID");
            department.setHeadsTableID(rs.getInt(3));
        }
    }


    public boolean insert(Department department) {
        String sql = insertInto(TABLE_NAME, "department", "manager");
        getLogger(DepartmentMangerDAO.class).debug("Adding Manager" + department);

        return super.insert(department, TABLE_NAME, sql);

    }


    public Department filterUsingDepID(int id) {

        getLogger(DepartmentMangerDAO.class).info("Getting department with manger using ID");
        return super.getById(id, TABLE_NAME);

    }


    public Set<Department> getAllWithManger() {
        return super.getAll(TABLE_NAME);

    }

    public Department update(Department department) {
        Department departmentNew = filterUsingDepID(department.getId());
        if (departmentNew == null) {
            this.insert(department);
            return department;
        }

        StringBuilder builder = new StringBuilder();

        String sql = updateSQL(TABLE_NAME, "id", "department", "manager");

        getLogger(DepartmentMangerDAO.class).info("Updating to " + department);

        getLogger(DepartmentMangerDAO.class).debug("My SQL statement " + sql);
        try (Connection conn = super.getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, department.getId());
            pstmt.setInt(2, department.getHead().getId());
            pstmt.setInt(3, department.getHeadsTableID());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                getLogger(DepartmentMangerDAO.class).info("Department successfully updated.");
                conn.commit();
                return department;
            } else {
                getLogger(DepartmentMangerDAO.class).info("No Department found with that id.");
                conn.rollback();
            }


        } catch (SQLException ex) {
            getLogger(DepartmentMangerDAO.class).error(ex.toString());
        }
        getLogger(DepartmentMangerDAO.class).debug("Connection Closed");
        getLogger(DepartmentMangerDAO.class).info("Department not added");
        return null;
    }

    public boolean delete(Department department) {
        return super.delete(department.getHeadsTableID(), TABLE_NAME);
    }

    public Department filterWithDepartment(Department department) {
        Set data = super.getFiltered(TABLE_NAME, "department", department.getId());
        if (data.iterator().hasNext())
            return (Department) data.iterator().next();
        return null;
    }
}
