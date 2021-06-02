/*
 *  Name: Daniel Di Clemente
 *  Course: Enterprise Java Development
 *  Assignment: Assignment 3
 *  Date: November 27, 2020
*/

package ca.sheridancollege.diclemed.database;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DatabaseConfig {

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource datasource) {
		
		return new NamedParameterJdbcTemplate(datasource);
	}
}
