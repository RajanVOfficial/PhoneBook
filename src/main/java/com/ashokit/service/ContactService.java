package com.ashokit.service;

import java.util.List;

import com.ashokit.entity.Contact;

public interface ContactService {
	
	public boolean saveOrUpdateContact(Contact contact);
	
	public List<Contact> getAllContacts();
	
	public Contact getContactById(Integer contactId);
	
	public boolean deleteContactById(Integer contactId);
	
}
