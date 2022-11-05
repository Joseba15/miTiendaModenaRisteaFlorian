package com.jacaranda.control;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.jacaranda.users.Users;

public class UsersControl {
	
	private static StandardServiceRegistry sr;
	private static SessionFactory sf;
	private static Session session;
	
	public UsersControl() {
		sr = new StandardServiceRegistryBuilder().configure().build();
		sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		session = sf.openSession();
	}
	
	public boolean checkUser(Users user) {
		boolean exist = false;
		Users aux = session.get(Users.class, user.getUsername());
		
		if((aux != null) && (aux.getUsername().equals(user.getUsername()) && aux.getPassword().equals(user.getPassword()))) {
			exist = true;
		}
		
		return exist;
	}
	
	public void registerUser(Users user) throws Exception {
		try {
			session.getTransaction().begin();
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new Exception("User already exist");
		}
	}
}