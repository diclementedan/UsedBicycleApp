/*
 *  Name: Daniel Di Clemente
 *  Course: Enterprise Java Development
 *  Assignment: Assignment 3
 *  Date: November 27, 2020
*/

package ca.sheridancollege.diclemed.beans;

import java.io.Serializable;

import lombok.*;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer implements Serializable{
	
	private Long manufacturerId;
	private String manufacturerName;
}
