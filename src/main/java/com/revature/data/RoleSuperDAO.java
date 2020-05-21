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

public class RoleSuperDAO extends DAO<Role> {
    private static final String TABLE_NAME = "role_super";
    private int ID;

    @Override
    PreparedStatement extractData(PreparedStatement ps, Role role) throws SQLException {
        getLogger(RoleSuperDAO.class).debug("Extracting role data");
        assert (ps != null & role != null);
        ps.setInt(1, role.getId());
        ps.setInt(2, role.getSupervisor().getId());
        return ps;
    }


    @Override
    Role setData(ResultSet rs) throws SQLException {
        getLogger(RoleSuperDAO.class).info("Setting role data");
        Role role = new Role();
        role.setupManger(rs);
        getLogger(RoleSuperDAO.class).debug(role);
        return role;
    }


    @Override
    void extractID(Role role, ResultSet rs) throws SQLException {
        if (rs.next()) {
            getLogger(RoleSuperDAO.class).info("Extracting role ID");
            role.setHeadsTableID(rs.getInt(3));
        }
    }


    public boolean insert(Role role) {
        String sql = insertInto(TABLE_NAME, "role", "supervisor");
        getLogger(RoleSuperDAO.class).debug("Adding Manager" + role);

        return super.insert(role, TABLE_NAME, sql);

    }


    public Role filterUsingSupID(int id) {

        getLogger(RoleSuperDAO.class).info("Getting role with manger using ID");
        return super.getById(id, TABLE_NAME);

    }


    public Set<Role> getAllWithManger() {
        return super.getAll(TABLE_NAME);

    }

    public Role update(Role role) {
        Role roleNew = filterUsingSupID(role.getId());
        if (roleNew == null) {
            this.insert(role);
            return role;
        }

        StringBuilder builder = new StringBuilder();

        String sql = updateSQL(TABLE_NAME, "id", "role", "supervisor");

        getLogger(RoleSuperDAO.class).info("Updating to " + role);

        getLogger(RoleSuperDAO.class).debug("My SQL statement " + sql);
        try (Connection conn = super.getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, role.getId());
            pstmt.setInt(2, role.getSupervisor().getId());
            pstmt.setInt(3, role.getSupervisorsTableID());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                getLogger(RoleSuperDAO.class).info("Role successfully updated.");
                conn.commit();
                return role;
            } else {
                getLogger(RoleSuperDAO.class).info("No Role found with that id.");
                conn.rollback();
            }


        } catch (SQLException ex) {
            getLogger(RoleSuperDAO.class).error(ex.toString());
        }
        getLogger(RoleSuperDAO.class).debug("Connection Closed");
        getLogger(RoleSuperDAO.class).info("Role not added");
        return null;
    }

    public boolean delete(Role role) {
        return super.delete(role.getSupervisorsTableID(), TABLE_NAME);
    }

    public Role filterWithRole(Role role) {
        Set data = super.getFiltered(TABLE_NAME, "role", role.getId());
        if (data.iterator().hasNext())
            return (Role) data.iterator().next();
        return null;
    }
}
