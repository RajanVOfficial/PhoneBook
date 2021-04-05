package com.ashokit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ashokit.entity.Contact;
import com.ashokit.props.AppProperties;
import com.ashokit.service.ContactService;

@Controller
public class ContactOperationController {
	
	private ContactService service;

	private AppProperties props;
	
	public ContactOperationController(ContactService service, AppProperties props) {
		this.service = service;
		this.props = props;
	}
	
	@GetMapping("/edit")
	public String editContact(@RequestParam("cid") Integer contactId, Model model) {
		
		Contact contactObj = service.getContactById(contactId);
		
		model.addAttribute("contact", contactObj);
		
		return "contact";
	}
	
	@GetMapping("/delete")
	public String deleteContact(@RequestParam("cid") Integer contactId, RedirectAttributes redirectAttributes) {
		Map<String, String> messages = props.getMessages();
		boolean isDeleted = service.deleteContactById(contactId);
		
		if(isDeleted) {
			redirectAttributes.addFlashAttribute("successMsg", messages.get("contactDeleteSucc"));
    	} else {
    		redirectAttributes.addFlashAttribute("failMsg", messages.get("contactDeleteFail"));
    	}
		
		return "redirect:view-contacts";
	}
}
