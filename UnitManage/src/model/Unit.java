package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Unit {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electricity?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

public String insertUnit(String user_account_no, String usage_date, String used_units, String price_per_unit) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " INSERT INTO `electricity_power_unit`(`unit_id`, `user_account_no`, `usage_date`, `used_units`, `price_per_unit`, `total_price`) VALUES (?,?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			double sum_total_price = Double.parseDouble(used_units) * Double.parseDouble(price_per_unit);
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, user_account_no);
			preparedStmt.setString(3, usage_date);
			preparedStmt.setString(4, used_units);
			preparedStmt.setString(5, price_per_unit);
			preparedStmt.setString(6, String.valueOf(sum_total_price));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the unit.";
			System.err.println(e.getMessage());
		}
		return output;
	}
public String readUnit() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Unit ID</th><th>Account No</th><th>Date</th><th>Unit Amount</th><th>Price for PerUnit</th><th>total_price Amount</th></tr>";
			String query = "select * from electricity_power_unit";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String unit_id = Integer.toString(rs.getInt("unit_id"));
				String user_account_no = rs.getString("user_account_no");
				String usage_date = rs.getString("usage_date");
				String used_units = rs.getString("used_units");
				String price_per_unit = rs.getString("price_per_unit");
				String total_price = rs.getString("total_price");

// Add into the html table
				output += "<tr><td>" + unit_id + "</td>";
				output += "<td>" + user_account_no + "</td>";
				output += "<td>" + usage_date + "</td>";
				output += "<td>" + used_units + "</td>";
				output += "<td>" + price_per_unit + "</td>";
				output += "<td>" + total_price + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the unit.";
			System.err.println(e.getMessage());
		}
		return output;
public String updateUnit(String unit_id, String user_account_no, String usage_date, String used_units, String price_per_unit) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE electricity_power_unit SET user_account_no=?,usage_date=?,used_units=?,price_per_unit=?,total_price=?" + "WHERE unit_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			double sum_total_price = Double.parseDouble(used_units) * Double.parseDouble(price_per_unit);
			// binding values
			preparedStmt.setString(1, user_account_no);
			preparedStmt.setString(2, usage_date);
			preparedStmt.setString(3, used_units);
			preparedStmt.setString(4, price_per_unit);
			preparedStmt.setString(5, String.valueOf(sum_total_price));
			preparedStmt.setInt(6, Integer.parseInt(unit_id));	}
}



