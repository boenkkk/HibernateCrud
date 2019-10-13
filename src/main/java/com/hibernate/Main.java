package com.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Employee;
import com.hibernate.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		Main m = new Main();
		//m.saveEmployee("Budi", "MGR", 30000, 10);
		m.retriveEmployee();
		//m.deleteEmployee();
		//m.updateEmployee();
	}

	public void saveEmployee(String ename, String job, int sal, int deptno) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Employee emp = new Employee();
			emp.setEname(ename);
			emp.setJob(job);
			emp.setSal(sal);
			emp.setDeptno(deptno);
			session.save(emp);
			
			transaction.commit();
			System.out.println("Records inserted sucessessfully");
			System.exit(0);
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void retriveEmployee() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			List employee = session.createQuery("from Employee").list();
			for (Iterator iterator = employee.iterator(); iterator.hasNext();) {
				Employee employee1 = (Employee) iterator.next();
				System.out.println(employee1.getEmpno()+"|"+employee1.getEname()+"|"+employee1.getJob()+"|"+employee1.getSal()+"|"+employee1.getDeptno());
			}
			
			transaction.commit();
			System.out.println("Employee is Retrive");
			System.exit(0);
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteEmployee() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			//cara 1
			/*String queryString = "from Employee where empno = :empno";
			Query query = session.createQuery(queryString);
			query.setLong("empno", 1);
			Employee employee = (Employee) query.uniqueResult();
			session.delete(employee);*/
			
			//cara 2
			long empno = 3;
			Employee employee = (Employee) session.load(Employee.class, empno);
			session.delete(employee);
			
			transaction.commit();
			System.out.println("One employee is deleted!");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void updateEmployee() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			//cara 1
			/*String queryString = "from Employee where ename = :ename";
			Query query = session.createQuery(queryString);
			query.setString("ename", "Budi");
			Employee employee = (Employee) query.uniqueResult();
			employee.setSal(11777);
			session.update(employee);*/
			
			//cara 2
			long empno = 5;
			Employee employee = (Employee) session.load(Employee.class, empno);
			employee.setJob("GM");
			
			transaction.commit();
			System.out.println("One employee is updated!");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
