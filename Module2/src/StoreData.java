
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
  

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joann
 */
public class StoreData {    
private static SessionFactory factory; 
   public static void main(String[] args) {
      
      try {
         factory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      
      

      /* Add few employee records in database */
      StoreData sd = new StoreData();
      Integer ID1 = sd.addTask("Wake up","Turn off alarm","9/25/21");
      Integer ID2 =  sd.addTask("Take a shower","Use the soap","9/25/21");
      Integer ID3 = sd.addTask("Get Dressed","Put your pants on!","9/25/21");
      /* List down all the employees */
      sd.listTasks();

      /* Update employee's records */
      sd.updateTask(ID1, "Wake up bud.","Turn off the alarm","9/23/21");

      /* Delete an employee from the database */
      sd.deleteTask(ID2);

      /* List down new list of the employees */
      sd.listTasks();
   }
   
   /* Method to CREATE an employee in the database */
   public Integer addTask(String task_name , String task_desc, String due_date){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer ID = null;
      
      try {
         tx = session.beginTransaction();
         ToDo td = new ToDo(task_name, task_desc, due_date);
         ID = (Integer) session.save(td); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
      return ID;
   }
   
   /* Method to  READ all the employees */
   public void listTasks( ){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         List employees = session.createQuery("FROM Tasks").list(); 
         for (Iterator iterator = employees.iterator(); iterator.hasNext();){
            ToDo td = (ToDo) iterator.next(); 
            System.out.print("Task Name: " + td.getName());
            System.out.print("  Task Description: " + td.getDescription()); 
            System.out.println("  Due Date: " + td.getDate()); 
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
   
   /* Method to UPDATE salary for an employee */
   public void updateTask(Integer ID,String taskName,String taskDesc, String due_date){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         ToDo td = (ToDo)session.get(ToDo.class, ID); 
         td.updateTask(ID, taskName, taskDesc, due_date);
		 session.update(td); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
   
   /* Method to DELETE an employee from the records */
   public void deleteTask(Integer ID){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
         tx = session.beginTransaction();
         ToDo td = (ToDo)session.get(ToDo.class, ID); 
         session.delete(td); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
}   
