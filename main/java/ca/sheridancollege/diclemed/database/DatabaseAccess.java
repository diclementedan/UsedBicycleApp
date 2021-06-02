/*
 *  Name: Daniel Di Clemente
 *  Course: Enterprise Java Development
 *  Assignment: Assignment 3
 *  Date: November 27, 2020
*/

package ca.sheridancollege.diclemed.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.diclemed.beans.Bike;
import ca.sheridancollege.diclemed.beans.Manufacturer;
import ca.sheridancollege.diclemed.beans.User;

@Repository
public class DatabaseAccess {

	@Autowired
	NamedParameterJdbcTemplate jdbc;

	public List<Manufacturer> getManufacturers() {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM manufacturer";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Manufacturer>(Manufacturer.class));
	}
	
	public List<Bike> getBikes() {
			
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM bike";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Bike>(Bike.class));
		}
	
	public void insertBike(int manufacturerId, String model, int year, String colour, double price) {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO bike (manufacturerID, model, year, colour, price)"
				+ " VALUES (:manufacturerId, :model, :year, :colour, :price)";
		namedParameters.addValue("manufacturerId", manufacturerId);
		namedParameters.addValue("model", model);
		namedParameters.addValue("year", year);
		namedParameters.addValue("colour", colour);
		namedParameters.addValue("price", price);
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0)
			System.out.println("Bike inserted into database");
	}

	public void updateBike(String model, double price) {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "UPDATE bike SET price = :price WHERE model = :model";
		namedParameters.addValue("price", price);
		namedParameters.addValue("model", model);
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0) {
			System.out.println("Updated bikes with model: " + model);
			System.out.println("Price changed to: $" + price);
			System.out.println("Number of entries updated: " + rowsAffected);
		}
	}

	public void deleteBike(int bikeId) {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM bike WHERE bikeId = :bikeId";
		namedParameters.addValue("bikeId", bikeId);
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0) {
			System.out.println("Deleted bike with id = \"" + bikeId + "\" from bike table");
		}
	}

	public User findUserAccount(String email) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user where email=:email";
		parameters.addValue("email", email);
		ArrayList<User> users = (ArrayList<User>)
				jdbc.query(query, parameters,
				new BeanPropertyRowMapper<User>(User.class));
		
		if (users.size() > 0)
			return users.get(0);
		else
			return null;
	}
	
	public List<String> getRolesById(Long userId) {
		
		ArrayList<String> roles = new ArrayList<String>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT user_role.userId, sec_role.roleName "
				+ "FROM user_role, sec_role "
				+ "WHERE user_role.roleId = sec_role.roleId "
				+ "AND userId=:userId";
		
		parameters.addValue("userId", userId);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		
		for (Map<String, Object> row :rows) {
			roles.add((String)row.get("roleName"));
		}
		return roles;
	}
	
}