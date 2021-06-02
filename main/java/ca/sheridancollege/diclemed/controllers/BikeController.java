/*
 *  Name: Daniel Di Clemente
 *  Course: Enterprise Java Development
 *  Assignment: Assignment 3
 *  Date: November 27, 2020
 *  
 *  ***IMPORTANT***
 *  Account Information:
 *  Username: frank@symphonybikes.com	 Password: 1234	   Roles: USER, ADMIN	  
 *  Username: dan@symphonybikes.com		 Password: 123     Roles: USER
*/

package ca.sheridancollege.diclemed.controllers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.diclemed.beans.Bike;
import ca.sheridancollege.diclemed.beans.Manufacturer;
import ca.sheridancollege.diclemed.database.DatabaseAccess;

@Controller
public class BikeController {

	@Autowired
	private DatabaseAccess da;
	
	List<Bike> bikeList = new CopyOnWriteArrayList<Bike>();
	List<Manufacturer> manufacturerList = new CopyOnWriteArrayList<Manufacturer>();
	
	@GetMapping("/")
	public String goHome(Model model) { return "index"; }
	
	@GetMapping("/secure/insert")
	public String insert(Model model) {
		
		model.addAttribute("bike", new Bike());
		model.addAttribute("manufacturerIdList", da.getManufacturers());
		return "/secure/insert";
	}
	
	@PostMapping("/secure/insert")
	public String insert(Model model, @ModelAttribute Bike b) {
		
		da.insertBike(b.getManufacturerId(), b.getModel(), b.getYear(), b.getColour(), b.getPrice());
		model.addAttribute("bike", new Bike());
		model.addAttribute("manufacturerIdList", da.getManufacturers());
		return "/secure/insert";
	}
	
	@GetMapping("/secure/update")
	public String update(Model model) {
		
		model.addAttribute("bike", new Bike());
		return "/secure/update";
	}
	
	@PostMapping("/secure/update")
	public String update(Model modell, @RequestParam String model, @RequestParam double price) {
		
		da.updateBike(model, price);
		modell.addAttribute("bike", new Bike());
		return "/secure/update";
	}
	
	@GetMapping("/secure/delete")
	public String delete(Model model) {
		
		model.addAttribute("bike", new Bike());
		model.addAttribute("bikeList", da.getBikes());
		return "/secure/delete";
	}
	
	@PostMapping("/secure/delete")
	public String delete(Model model, @RequestParam int bikeId) {
			
			da.deleteBike(bikeId);
			model.addAttribute("bike", new Bike());
			model.addAttribute("bikeList", da.getBikes());
			return "/secure/delete";
		}
	
	@GetMapping("/login")
	public String login() {
		return"login";
	}
	
	@GetMapping("/permission-denied")
	public String permissionDenied() {
		return"/error/permission-denied";
	}
}


