package com.ashokit.controller;

import com.ashokit.entity.Contact;
import com.ashokit.service.ContactService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactInfoController {

    private ContactService service;

    public ContactInfoController(ContactService service) {
        this.service = service;
    }

    
    /**
     * This method is used to display contact form
     * @return
     */
    @GetMapping("/load-form")
    public String loadForm(Model model) {
        Contact contactObj  = new Contact();
        
        // sending data from controller to ui
        model.addAttribute("contact", contactObj);
        
        // returning logical view name
        return "contact";
    }
    
    @PostMapping("/saveContact")
    public String handleSubmitBtn(Model model, Contact contact) {
    
    	String successMsg = "Contact Saved";
    	String failMsg = "Failed to save contact";
    	
    	if(contact.getContactId()!=null) {
    		successMsg = "Contact Updated Successfully!";
    		failMsg = "Failed to Update Contact";
    	} 
    	
    	boolean isSaved = service.saveOrUpdateContact(contact);
    	
    	if(isSaved) {
    		model.addAttribute("successMsg", successMsg);
    	} else {
    		model.addAttribute("failMsg", failMsg);
    	}
    	return "contact";
    }
    
    @GetMapping("/view-contacts")
    public String viewContactHyperLink(Model model) {
    	
    	List<Contact> allContacts = service.getAllContacts();   
    	
    	model.addAttribute("contacts", allContacts);
    	
    	return "contacts-display";
    }
}
