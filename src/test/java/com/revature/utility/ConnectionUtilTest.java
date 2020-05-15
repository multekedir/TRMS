package com.revature.utility;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

public class ConnectionUtilTest {

    @Test
    public void TestConnectionUtil() {
        ConnectionUtil conn = ConnectionUtil.getConnectionUtil();
        Connection connection = conn.getConnection();
        assertNotNull(connection);
    }
}