package com.ashokit.controller;

import com.ashokit.entity.Contact;
import com.ashokit.props.AppProperties;
import com.ashokit.service.ContactService;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactInfoController {

    private ContactService service;

    private AppProperties props;
    
    public ContactInfoController(ContactService service, AppProperties props) {
		this.service = service;
		this.props = props;
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
    	Integer cid = contact.getContactId();
    	boolean isSaved = service.saveOrUpdateContact(contact);
    	Map<String, String> messages = props.getMessages();
    	
    	if(isSaved) {
    		if(cid==null) {
    			model.addAttribute("successMsg", messages.get("contactSaveSucc"));
        	} else {
        		model.addAttribute("successMsg", messages.get("contactUpdateSucc"));
        	}
    	} else {
    		model.addAttribute("failMsg", messages.get("contactSaveFail"));
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
