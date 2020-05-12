package com.revature.utility;

/**
 * Builder class for building SQL statements
 *
 * @author : julian3
 */
public class SQLBuilder {


    public static String insertInto(String tableName, String... columns) {

        StringBuilder builder = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        for (String column : columns) {
            builder.append(column).append(',');
        }
        builder.deleteCharAt(builder.length() - 1).append(") VALUES (");
        for (String column : columns) {
            builder.append("?,");
        }
        builder.deleteCharAt(builder.length() - 1).append(")");
        return builder.toString();
    }


    public static String updateSQL(String tableName, String whereColumn, String... columns) {
        StringBuilder builder = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        for (String column : columns) {
            builder.append(column).append("=?,");
        }
        return builder.deleteCharAt(builder.length() - 1).append(" WHERE ").append(whereColumn).append("=?").toString();
    }

    public static String selectWhere(String tableName, String operator, String... columns) {
        StringBuilder builder = new StringBuilder("SELECT  * FROM ").append(tableName).append(" WHERE ");
        for (String column : columns) {
            builder.append(column).append(" = ? ").append(operator + " ");
        }

        return builder.deleteCharAt(builder.length() - 2).toString();
    }

    public static String selectAll(String tableName) {
        StringBuilder builder = new StringBuilder("SELECT  * FROM ").append(tableName);

        return builder.toString();
    }

    public static String deleteSQL(String tableName) {
        StringBuilder builder = new StringBuilder("DELETE FROM ").append(tableName).append(" WHERE id = ?");

        return builder.toString();
    }
}