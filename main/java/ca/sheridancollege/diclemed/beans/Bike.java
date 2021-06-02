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
public class Bike implements Serializable{

	private int bikeId;
	private int manufacturerId;
	private String model;
	private int year;
	private String colour;
	private double price;
	
}
