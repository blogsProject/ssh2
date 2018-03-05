package com.luotianyi.ssh2.util;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	public static void closeResource(Connection conn, PreparedStatement pstm, ResultSet rs) {
		try {
			if (conn != null)
				conn.close();
			if (pstm != null)
				pstm.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
