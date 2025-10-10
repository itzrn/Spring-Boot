package com.hibernatepractice.hibernatepractice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App 
{
    public static void main( String[] args)
    {
    	
    	// this is to insert the data to the table
//        Student s1 = new Student();
//        s1.setId(108);
//        s1.setName("Ishika");
//        s1.setStudentClass("Software Developer");
//        
//        // This builds the registry containing all the Hibernate settings from hibernate.cfg.xml
//        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build(); // if the name is as default then you may not write it will work else you need to provide the .cgf.xml file
//        
//        // The MetadataSources reads all mappings (your entity classes) and combines them with configuration details.
//        // getMetadataBuilder().build() compiles this into a single Metadata object.
//        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
//        
//        // creates a SessionFactory, which is a thread-safe, heavy weight object used to open sessions.
//        SessionFactory sf = metadata.getSessionFactoryBuilder().build();
//        
//        // this is an interface
//        // Opens a Session, which represents a single connection between your app and the database.
//        Session session=sf.openSession();
//        
//        // Opens a transaction, ensuring all your DB operations either succeed together or fail together.
//        Transaction transaction = session.beginTransaction();
//        
//        // Tells Hibernate to save your entity (Student s1) to the database.
//        session.persist(s1);
//        
//        // Finalizes the transaction — all changes are written permanently to the database. 
//        // If something fails before this line, changes are not saved.
//        transaction.commit();
//        
//        System.out.println("Successfully updated");
//        
//        session.close();
//        sf.close();
    	
    	
    	
    	
    	
    	// to retrieve the data from the database
//    	Student s = new Student();
//    	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
//    	Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
//    	SessionFactory sf = metadata.getSessionFactoryBuilder().build();
//    	Session session=sf.openSession();
//    	Transaction transaction = session.beginTransaction();
//    	
//    	s=session.get(Student.class, 108); // .load() will also work
//    	// sometime you need to cast it to student if you face any error
//    	// s=(Student)session.get(Student.class, 108);
//    	
//    	System.out.println(s);
//    	
//    	transaction.commit();
//    	
//    	sf.close();
//    	session.close();
    	
    	
    	
    	
    	// if we have some other tables also and want to push all the data in one tabel
    	Student s1 = new Student();
    	Marks m = new Marks(12.2, 15.5, 16.8);
    	s1.setId(102);
    	s1.setName("Aryan");
    	s1.setStudentClass("BSCS");
    	s1.setStudent_marks(m);
    	
    	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    	Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
    	SessionFactory sf = metadata.getSessionFactoryBuilder().build();
    	Session session=sf.openSession();
    	Transaction transaction = session.beginTransaction();
    	session.persist(s1);
    	System.out.println("Inserted Successfully!");
    	transaction.commit();
    	sf.close();
    	session.close();
    }
}

