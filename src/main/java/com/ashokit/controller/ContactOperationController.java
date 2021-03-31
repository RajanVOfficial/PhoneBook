package com.ashokit.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.entity.Contact;
import com.ashokit.service.ContactService;

@Controller
public class ContactOperationController {
	
	private ContactService service;

	public ContactOperationController(ContactService service) {
		this.service = service;
	}
	
	@GetMapping("/edit")
	public String editContact(@RequestParam("cid") Integer contactId, Model model) {
		
		Contact contactObj = service.getContactById(contactId);
		
		model.addAttribute("contact", contactObj);
		
		return "contact";
	}
	
	@GetMapping("/delete")
	public String deleteContact(@RequestParam("cid") Integer contactId, Model model) {
		
		boolean isDeleted = service.deleteContactById(contactId);
		
		if(isDeleted) {
			model.addAttribute("successMsg", "Contact Deleted Successfully");
    	} else {
    		model.addAttribute("failMsg", "Failed to Delete Contact");
    	}
		
		List<Contact> allContacts = service.getAllContacts();   
    	
    	model.addAttribute("contacts", allContacts);
		
		return "contacts-display";
	}
}
