package com.nba.datacenter.utils;

import com.nba.datacenter.common.Const;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OracleUtil {

    public static Connection getConnection() throws Exception {
        Class.forName(Const.className);
        return DriverManager.getConnection(Const.url, Const.username, Const.password);
    }

    public static void stopConnection(Connection connection, PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
