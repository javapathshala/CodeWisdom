/*
 * Created on Aug 10, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.jp.hib.frw;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * @author dimit
 */
public class HibHelper {

	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * @return
	 */
	private static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	/**
	 * @return
	 * @throws HibernateException
	 */
	public static Session getSession() throws HibernateException {
		Session s = (Session) session.get();
		// Open a new Session, if this Thread has none yet
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);

		}
		return s;
	}

	/**
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
		Session s = (Session) session.get();
		session.set(null);
		if (s != null)
			s.close();
	}

}
