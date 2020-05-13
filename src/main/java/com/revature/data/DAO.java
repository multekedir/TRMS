package com.revature.data;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static com.revature.utility.LoggerSingleton.getLogger;
import static com.revature.utility.SQLBuilder.deleteSQL;
import static com.revature.utility.SQLBuilder.selectWhere;

public abstract class DAO<T> {
    // TODO needs testing
    abstract PreparedStatement exctractData(PreparedStatement ps, T t) throws SQLException;

    abstract T setData(ResultSet rs) throws SQLException;

    abstract void exctractID(T t, ResultSet rs) throws SQLException;

    public boolean insert(T t, String tableName, String sql, Connection conn) throws SQLException {
        Integer key = 0;
        conn.setAutoCommit(false);
        String[] keys = {"id"};
        getLogger(DAO.class).debug("My SQL statement " + sql);
        PreparedStatement ps = exctractData(conn.prepareStatement(sql, keys), t);
        //should return number of rows affected
        int i = ps.executeUpdate();
        if (i == 1) {
            ResultSet rs = ps.getGeneratedKeys();
            exctractID(t, rs);
            conn.commit();
            getLogger(DAO.class).debug("Added " + t);
            return true;
        }
        getLogger(DAO.class).error("Not added successfully.");
        conn.rollback();
        return false;
    }

    protected T getById(int id, String tableName, Connection conn) throws SQLException {

        String sql = selectWhere(tableName, "", "id");
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            getLogger(DAO.class).debug("Object with id " + id + " found. ");
            return setData(rs);
        }
        getLogger(DAO.class).info("Not Found " + id);
        return null;
    }

    protected Set<T> getFiltered(String tableName, String column, String lookingFor, Connection conn) throws SQLException {
        getLogger(DAO.class).info("Getting filtered data " + column + " we are looking for " + lookingFor + " in " + tableName);
        Set<T> out = new HashSet<T>();
        String sql = selectWhere(tableName, "", column);
        getLogger(DAO.class).debug("My SQL statement " + sql);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, lookingFor);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            getLogger(DAO.class).debug("Object with" + column + lookingFor + " found. ");
            out.add(setData(rs));
        }
        getLogger(DAO.class).info("Not Found " + lookingFor);


        return out;
    }

    protected Set<T> getFiltered(String tableName, String column, int lookingFor, Connection conn) throws SQLException {
        getLogger(DAO.class).info("Getting filtered data " + column + " we are looking for " + lookingFor + " in " + tableName);
        Set<T> collections = new HashSet<T>();
        String sql = selectWhere(tableName, "", column);
        getLogger(DAO.class).debug("My SQL statement " + sql);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, lookingFor);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            getLogger(DAO.class).debug("Object with" + column + lookingFor + " found. ");
            collections.add(setData(rs));
        }
        getLogger(DAO.class).info("Not Found" + lookingFor);


        return collections;
    }


    public Set<T> getAll(String tableName, Connection conn) throws SQLException {
        getLogger(DAO.class).info("Getting all");
        Set<T> collections = new HashSet<T>();

        String sql = selectAll(tableName);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            collections.add(setData(rs));
        }

        return collections;

    }

    protected abstract String selectAll(String tableName);

    abstract T update(T t) throws SQLException;

    boolean delete(int id, String tableName, Connection conn) throws SQLException {
        conn.setAutoCommit(false);
        String sql = deleteSQL(tableName);
        PreparedStatement ps = conn.prepareStatement(sql);
        getLogger(DAO.class).info("Removing ...");
        getLogger(DAO.class).debug("My SQL statement " + sql);
        ps.setInt(1, id);
        if (ps.executeUpdate() > 0) {
            getLogger(DAO.class).info("Removed object successfully.");
            conn.commit();
        } else {
            getLogger(DAO.class).info("No Object found with that id.");
            conn.rollback();
        }

        return true;
    }

}