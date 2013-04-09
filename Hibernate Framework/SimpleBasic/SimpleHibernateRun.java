/*
 * Created on Aug 7, 2006
 *
 */
package SimpleBasic;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jp.common.hibernate.HibernateUtil;

/**
 * @author dimit To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SimpleHibernateRun {

	private static SessionFactory sessionFactory = null;

	public static void main(String[] args) {

		SimpleHibernateRun simHibRun = new SimpleHibernateRun();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		switch (Integer.parseInt(args[0])) {
			case 1: // This is for select all records from table Employee
				List rs = simHibRun.AllRecords(session);
				for (int i = 0; i < rs.size(); i++) {
					Employee emp = (Employee) rs.get(i);
					System.out.println("Name : " + emp.getName());
					System.out.println("ID : " + emp.getEmployeeId());
					System.out.println("Address : " + emp.getAddress());
					System.out.println("Salary : " + emp.getSalary());
				}
				break;
			case 2: // This is for inserting a new Data in the Table Employee
				simHibRun.insertRecord(session);
				break;
			case 3: // This is for deleting Data from the Table Employee
				simHibRun.deleteRecord(session);
				break;
			case 4: // This is for deleting Data from the Table Employee
				simHibRun.UpdateRecord(session);
				break;

			default:
				System.out.println("Enter Something");
				break;
		}

		HibernateUtil.closeSession();

	}

	/**
	 * @param session
	 */
	private void UpdateRecord(Session session) {
		Employee emp = (Employee) session.load(Employee.class, new Integer(7));
		emp.setName("Pragati2");
		session.getTransaction().commit();
		HibernateUtil.closeSession();
		// Session sessionUpd = HibernateUtil.currentSession();
		// sessionUpd.beginTransaction();
		// sessionUpd.update(emp);
		// sessionUpd.getTransaction().commit();
		// HibernateUtil.closeSession();

	}

	/**
	 * @param session
	 */
	private void deleteRecord(Session session) {
		Employee emp = (Employee) session.load(Employee.class, new Integer(6));
		session.getTransaction().commit();
		HibernateUtil.closeSession();
		Session sessionDel = HibernateUtil.currentSession();
		sessionDel.beginTransaction();
		sessionDel.delete(emp);
		sessionDel.getTransaction().commit();
		HibernateUtil.closeSession();
	}

	/**
	 * 
	 */
	private void insertRecord(Session session) {
		Employee insertEmp = new Employee();

		insertEmp.setName("Dimit");
		insertEmp.setAddress("Pragati");
		insertEmp.setSalary(200);
		session.save(insertEmp);
		session.getTransaction().commit();
	}

	private List AllRecords(Session session) {
		List result = session.createQuery("from Employee").list(); // This is
																	// HQL
																	// example
		session.getTransaction().commit();
		return result;
	}

}
