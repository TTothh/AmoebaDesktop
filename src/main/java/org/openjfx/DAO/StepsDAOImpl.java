package org.openjfx.DAO;

import java.sql.Connection ;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.openjfx.config.Config;

public class StepsDAOImpl implements StepsDAO {
	private static final String INSERT_STEP = "INSERT INTO steps (PlayerID, X, Y, Symbol) VALUES (?, ?, ?, ?)";
	private final String ConnectionURL;

	public StepsDAOImpl() {
		ConnectionURL = Config.getValue("db.url");
	}

	@Override
	public void RecordStep(int PID, int x, int y, String symbol) {
		Connection connection;
		PreparedStatement prdstmnt;

		try {
			connection = DriverManager.getConnection(ConnectionURL);
			prdstmnt = connection.prepareStatement(INSERT_STEP);

			prdstmnt.setInt(1, PID);
			prdstmnt.setInt(2, x);
			prdstmnt.setInt(3, y);
			prdstmnt.setString(4, symbol);

			prdstmnt.execute();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
}
