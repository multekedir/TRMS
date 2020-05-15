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

public class DepartmentDAO extends DAO<Department> {
    private static final String TABLE_NAME = "departments";
    //private static final ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    @Override
    PreparedStatement extractData(PreparedStatement ps, Department department) throws SQLException {
        getLogger(DepartmentDAO.class).debug("Extracting department data");
        assert (ps != null & department != null);
        ps.setString(1, department.getName());
        return ps;
    }


    @Override
    Department setData(ResultSet rs) throws SQLException {
        getLogger(DepartmentDAO.class).info("Setting department data");
        Department department = new Department(rs);
        getLogger(DepartmentDAO.class).debug(department);
        return department;
    }


    @Override
    void extractID(Department department, ResultSet rs) throws SQLException {
        if (rs.next()) {
            getLogger(DepartmentDAO.class).info("Extracting department ID");
            department.setId(rs.getInt(1));
        }
    }


    public boolean insert(Department department) {
        String sql = insertInto(TABLE_NAME, "name");
        getLogger(DepartmentDAO.class).debug("Adding " + department);
        return super.insert(department, TABLE_NAME, sql);
    }


    public Department getDepartmentByID(int id) {

        getLogger(DepartmentDAO.class).info("Getting department using ID");
        return super.getById(id, TABLE_NAME);

    }


    public Set<Department> getAll() {
        return super.getAll(TABLE_NAME);

    }

    public Department update(Department department) {
        StringBuilder builder = new StringBuilder();

        String sql = updateSQL(TABLE_NAME, "id", "name");
        getLogger(DepartmentDAO.class).info("Updating to " + department);
        getLogger(DepartmentDAO.class).debug("My SQL statement " + sql);
        try (Connection conn = super.getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, department.getName());
            pstmt.setInt(2, department.getId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                getLogger(DepartmentDAO.class).info("Department successfully updated.");
                conn.commit();
                return department;
            } else {
                getLogger(DepartmentDAO.class).info("No Department found with that id.");
                conn.rollback();
            }


        } catch (SQLException ex) {
            getLogger(DepartmentDAO.class).error(ex.toString());
        }
        getLogger(DepartmentDAO.class).debug("Connection Closed");
        getLogger(DepartmentDAO.class).info("Department not added");
        return null;
    }

    public boolean delete(Department department) {
        return super.delete(department.getId(), TABLE_NAME);
    }

    public Department filterWithName(String name) {
        Set data = super.getFiltered(TABLE_NAME, "name", name);
        if (data.iterator().hasNext())
            return (Department) data.iterator().next();
        return null;
    }
}

