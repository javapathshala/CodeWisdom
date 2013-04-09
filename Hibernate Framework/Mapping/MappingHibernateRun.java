/*
 * Created on Aug 7, 2006
 *
 */
package Mapping;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jp.common.hibernate.HibernateUtil;

/**
 * @author dimit To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MappingHibernateRun {

	private static SessionFactory sessionFactory = null;

	public static void main(String[] args) {

		MappingHibernateRun simHibRun = new MappingHibernateRun();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		Users user = (Users) session.load(Users.class, new Integer(2));
		Events events = (Events) session.load(Events.class, new Integer(2));

		System.out.println("--------------------------------------------");
		System.out.println("First Name : " + user.getFirstname());
		System.out.println("Last Name : " + user.getLastname());
		System.out.println("Age : " + user.getAge());
		System.out.println("ID : " + user.getId());

		user.getFavouriteEvents().add(events);

		// System.out.println("--------------------------------------------");
		// System.out.println("ID : " + events.getId());
		// System.out.println("Event Title : " + events.getEvnettitle());
		tx.commit();
		HibernateUtil.closeSession();
		Set aa = user.getFavouriteEvents();

		Iterator itr = aa.iterator();
		while (itr.hasNext()) {
			System.out.println(aa);
		}

	}

}
