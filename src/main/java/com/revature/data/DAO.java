package com.revature.data;

import com.revature.utility.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static com.revature.utility.LoggerSingleton.getLogger;
import static com.revature.utility.SQLBuilder.*;

public abstract class DAO<T> {
    private static final ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    abstract T setData(ResultSet rs) throws SQLException;

    private static Connection conn;

    {
        try {
            Connection conn = cu.getConnection();
            conn.setAutoCommit(false);
            getLogger(DAO.class).debug("got conection");
        } catch (SQLException e) {
            getLogger(DAO.class).error("Can't get connection");
            getLogger(DAO.class).error(e.toString());
        }
    }


    abstract PreparedStatement extractData(PreparedStatement ps, T t) throws SQLException;

    abstract void extractID(T t, ResultSet rs) throws SQLException;


    abstract T update(T t) throws SQLException;

    protected boolean insert(T t, String tableName, String sql) {
        Integer key = 0;
        String[] keys = {"id"};
        getLogger(DAO.class).debug("My SQL statement " + sql);
        try (Connection conn = cu.getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement ps = extractData(conn.prepareStatement(sql, keys), t);
            //should return number of rows affected
            int i = ps.executeUpdate();
            if (i == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                extractID(t, rs);
                conn.commit();
                getLogger(DAO.class).debug("Added " + t);
                return true;
            }
            getLogger(DAO.class).error("Not added successfully.");
            conn.rollback();
        } catch (SQLException ex) {
            getLogger(DAO.class).error(ex.toString());
        }

        return false;
    }

    protected T getById(int id, String tableName) {
        String sql = selectWhere(tableName, "", "id");
        try (Connection conn = cu.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                getLogger(DAO.class).debug("Object with id " + id + " found. ");
                return setData(rs);
            }
        } catch (SQLException ex) {
            getLogger(DAO.class).error(ex.toString());
        }

        getLogger(DAO.class).info("Not Found " + id);
        return null;
    }

    protected Set<T> getFiltered(String tableName, String column, String lookingFor) {
        getLogger(DAO.class).info("Getting filtered data " + column + " we are looking for " + lookingFor + " in " + tableName);
        Set<T> out = new HashSet<T>();
        String sql = selectWhere(tableName, "", column);
        getLogger(DAO.class).debug("My SQL statement " + sql);

        try (Connection conn = cu.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, lookingFor);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                getLogger(DAO.class).debug("Object with" + column + lookingFor + " found. ");
                out.add(setData(rs));
            }
            return out;
        } catch (SQLException e) {
            getLogger(DAO.class).error(e.toString());
        }


        getLogger(DAO.class).info("Not Found " + lookingFor);


        return null;
    }

    protected Set<T> getFiltered(String tableName, String column, int lookingFor) {
        getLogger(DAO.class).info("Getting filtered data " + column + " we are looking for " + lookingFor + " in " + tableName);
        Set<T> collections = new HashSet<T>();
        String sql = selectWhere(tableName, "", column);
        getLogger(DAO.class).debug("My SQL statement " + sql);
        try (Connection conn = cu.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, lookingFor);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                getLogger(DAO.class).debug("Object with" + column + lookingFor + " found. ");
                collections.add(setData(rs));
            }
            return collections;
        } catch (SQLException e) {
            getLogger(DAO.class).error(e.toString());
        }

        getLogger(DAO.class).info("Not Found" + lookingFor);


        return null;
    }


    public Set<T> getAll(String tableName) {
        getLogger(DAO.class).info("Getting all");
        Set<T> collections = new HashSet<T>();

        String sql = selectAll(tableName);
        try (Connection conn = cu.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                collections.add(setData(rs));
            }
            return collections;
        } catch (SQLException e) {
            getLogger(DAO.class).error(e.toString());
        }


        return null;

    }


    boolean delete(int id, String tableName) {
        try (Connection conn = cu.getConnection()) {
            conn.setAutoCommit(false);
            String sql = deleteSQL(tableName);
            PreparedStatement ps = conn.prepareStatement(sql);
            getLogger(DAO.class).info("Removing ...");
            getLogger(DAO.class).debug("My SQL statement " + sql);
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                getLogger(DAO.class).info("Removed object successfully.");
                conn.commit();
                return true;
            }
            getLogger(DAO.class).info("No Object found with that id.");
            conn.rollback();
        } catch (SQLException e) {
            getLogger(DAO.class).error(e.toString());
        }


        return false;
    }

    protected Connection getConnection() {
        return cu.getConnection();
    }
}