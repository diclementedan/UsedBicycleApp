/*
 *  Name: Daniel Di Clemente
 *  Course: Enterprise Java Development
 *  Assignment: Assignment 3
 *  Date: November 27, 2020
*/

package ca.sheridancollege.diclemed.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.diclemed.database.DatabaseAccess;

@Service
public class UserDetailsServicesImpl implements UserDetailsService{

	@Autowired
	private DatabaseAccess da;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		ca.sheridancollege.diclemed.beans.User user = da.findUserAccount(username);
		
		if (user == null) {
			System.out.println("User not found: " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		
		List<String> roleNames = da.getRolesById(user.getUserId());
		
		// convert it into something understood by Spring Security, a granted authority list
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				grantList.add(new SimpleGrantedAuthority(role));
			}
		}
		
		UserDetails userDetails = (UserDetails)new User(user.getEmail(), user.getEncryptedPassword(), grantList);
		
		return userDetails;
	}

}
