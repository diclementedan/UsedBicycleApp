/*
 *  Name: Daniel Di Clemente
 *  Course: Enterprise Java Development
 *  Assignment: Assignment 3
 *  Date: November 27, 2020
*/

package ca.sheridancollege.diclemed.beans;

import lombok.*;

@Data
@NoArgsConstructor
public class User {
	
	private Long userId;
	
	@NonNull
	private String email;
	
	@NonNull
	private String encryptedPassword;
	
	@NonNull
	private boolean enabled;
}
