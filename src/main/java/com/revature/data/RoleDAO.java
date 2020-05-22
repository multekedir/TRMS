package com.revature.data;

import com.revature.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import static com.revature.utility.LoggerSingleton.getLogger;
import static com.revature.utility.SQLBuilder.insertInto;
import static com.revature.utility.SQLBuilder.updateSQL;

public class RoleDAO extends DAO<Role> {

    private static final String TABLE_NAME = "role";
    private static final RoleSuperDAO roleSuperDAO = new RoleSuperDAO();

    @Override
    PreparedStatement extractData(PreparedStatement ps, Role role) throws SQLException {
        getLogger(RoleDAO.class).debug("Extracting role data");
        assert (ps != null & role != null);
        ps.setString(1, role.getName());
        return ps;
    }


    @Override
    Role setData(ResultSet rs) throws SQLException {
        getLogger(RoleDAO.class).info("Setting role data");
        Role role = new Role(rs);
        getLogger(RoleDAO.class).debug(role);
        return role;
    }


    @Override
    void extractID(Role role, ResultSet rs) throws SQLException {
        if (rs.next()) {
            getLogger(RoleDAO.class).info("Extracting role ID");
            role.setId(rs.getInt(1));
        }
    }


    public boolean insert(Role role) {
        String sql = insertInto(TABLE_NAME, "role_name");
        getLogger(RoleDAO.class).debug("Adding " + role);

        boolean result = super.insert(role, TABLE_NAME, sql);
        if (result && role.hasSuper()) {
            return roleSuperDAO.insert(role);
        }
        return result;
    }


    public Role getRoleByID(int id) {

        getLogger(RoleDAO.class).info("Getting role using ID");
        return super.getById(id, TABLE_NAME);

    }


    public Set<Role> getAll() {
        return super.getAll(TABLE_NAME);

    }

//    public Set<Role> filterWithRole(Role role) {
//        try (Connection conn = cu.getConnection()) {
//            return super.getFiltered(TABLE_NAME, "role", String.valueOf(role), conn);
//        } catch (SQLException ex) {
//            getLogger(RoleDAO.class).error(ex);
//
//        }
//
//        return null;
//    }


    public Role update(Role role) {
        StringBuilder builder = new StringBuilder();
        if (role.hasSuper()) {
            getLogger(DepartmentDAO.class).info("Checking for supervisor");
            roleSuperDAO.update(role);
        }
        String sql = updateSQL(TABLE_NAME, "id", "role_name");
        getLogger(RoleDAO.class).info("Updating to " + role);
        getLogger(RoleDAO.class).debug("My SQL statement " + sql);
        try (Connection conn = super.getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, role.getName());
            pstmt.setInt(2, role.getId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                getLogger(RoleDAO.class).info("Role successfully updated.");
                conn.commit();
                return role;
            } else {
                getLogger(RoleDAO.class).info("No Role found with that id.");
                conn.rollback();
            }


        } catch (SQLException ex) {
            getLogger(RoleDAO.class).error(ex.toString());
        }
        getLogger(RoleDAO.class).debug("Connection Closed");
        getLogger(RoleDAO.class).info("Role not added");
        return null;
    }

    public boolean delete(Role role) {
        return super.delete(role.getId(), TABLE_NAME);
    }

    public Role filterWithName(String name) {
        Set data = super.getFiltered(TABLE_NAME, "role_name", name);
        if (data.iterator().hasNext())
            return (Role) data.iterator().next();
        return null;
    }


    public RoleSuperDAO getRoleSuperDAO() {
        return roleSuperDAO;
    }
}
