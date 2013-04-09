/*
 * Created on 11-Dec-06
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package SetMapping;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import SimpleBasic.SimpleHibernateRun;

import com.jp.common.hibernate.HibernateUtil;

/**
 * @author dimit To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SetMappingRun {

	public static void main(String[] args) {
		SimpleHibernateRun simHibRun = new SimpleHibernateRun();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		switch (Integer.parseInt(args[0])) {
			case 1: // This is for select records on basis of userid
				findUserInfo(session);
				break;
			case 2: // This is for inserting a new Data
				insertRecord(session);
				break;
			case 3: // This is for deleting Data from the Table Employee
				deleteRecord(session);
				break;
			case 4: // This is for deleting Data from the Table Employee
				// simHibRun.UpdateRecord(session);
				break;

			default:
				System.out.println("Enter Something");
				break;
		}

		session.getTransaction().commit();
		HibernateUtil.closeSession();
	}

	private static PersistenceUsers findUserInfo(Session session) {
		List list = session.createCriteria(PersistenceUsers.class).add(Expression.eq("userId", "12")).list();
		if (list.size() > 0) {
			session.evict(list);
			PersistenceUsers persistenceUsers = (PersistenceUsers) list.get(0);
			System.out.println("User Info is : ");
			System.out.println(persistenceUsers.getUserId());
			System.out.println(persistenceUsers.getUserName());
			Iterator itr = persistenceUsers.getAccountsPersist().iterator();
			while (itr.hasNext()) {
				System.out.println("*************************************************88");
				System.out.println("Account Info : ");
				UserAccountsPersist userAccountsPersist = (UserAccountsPersist) itr.next();
				System.out.println(userAccountsPersist.getAccountNumber());
				System.out.println(userAccountsPersist.getSortCode());
			}
			return persistenceUsers;
		} else {
			System.out.println("NO user Information found");
			return new PersistenceUsers();
		}
	}

	private static void insertRecord(Session session) {
		PersistenceUsers persistenceUsers = new PersistenceUsers();
		persistenceUsers.setUserId("12");
		persistenceUsers.setUserName("XYZ");
		UserAccountsPersist userAccountsPersist = new UserAccountsPersist();
		userAccountsPersist.setAccountNumber("111111");
		userAccountsPersist.setSortCode("1111");
		persistenceUsers.add(userAccountsPersist);
		userAccountsPersist = new UserAccountsPersist();
		userAccountsPersist.setAccountNumber("333333");
		userAccountsPersist.setSortCode("33333");
		persistenceUsers.add(userAccountsPersist);
		session.saveOrUpdate(persistenceUsers);
	}

	/**
	 * Removing particular accounts from user info
	 */
	private static void deleteRecord(Session session) {
		PersistenceUsers persistenceUsers = findUserInfo(session);
		// account to be removed.
		UserAccountsPersist selectedAccount = new UserAccountsPersist();
		selectedAccount.setAccountNumber("111111");
		selectedAccount.setSortCode("1111");
		persistenceUsers.setUserId("12");
		Iterator itr = persistenceUsers.getAccountsPersist().iterator();
		while (itr.hasNext()) {
			if (selectedAccount.getAccountNumber().equals(((UserAccountsPersist) itr.next()).getAccountNumber())) {
				itr.remove();
			}
		}
		session.saveOrUpdate(persistenceUsers);
	}
}
