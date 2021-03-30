package com.ashokit.controller;

import com.ashokit.entity.Contact;
import com.ashokit.service.ContactService;
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
    	boolean isSaved = service.saveOrUpdateContact(contact);
    	
    	if(isSaved) {
    		model.addAttribute("successMsg", "Contact Saved");
    	} else {
    		model.addAttribute("failMsg", "Failed to save contact");
    	}
    	return "contact";
    }
    
    public String viewContactHyperLink() {
    	
    	return "allcontact";
    }
}
