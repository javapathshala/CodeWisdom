/**
 * Copywrite @ Dimit Chadha
 */
package com.jp.spring.app.di;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author Dimit Chadha
 * 
 */
public class ContainerDI implements Serializable {

	private static final long serialVersionUID = 1362866823679216747L;

	private DefaultListableBeanFactory factory;

	public ContainerDI() {

		factory = new DefaultListableBeanFactory();

	}

	public ContainerDI(ContainerDI parent) {

		factory = new DefaultListableBeanFactory(parent.factory);
	}

	public Description add(Class<?> clazz) {
		try {
			AbstractBeanDefinition bd = BeanDefinitionReaderUtils.createBeanDefinition(null, clazz.getName(), clazz.getClassLoader());
			bd.setLazyInit(true);
			bd.setSingleton(true);
			bd.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
			factory.registerBeanDefinition(clazz.getName(), bd);
			return new ContainerDI.Description(factory, bd);
		} catch (ClassNotFoundException e) {
			throw new FatalBeanException("Unable to configure bean for: " + clazz.getName(), e);
		}
	}

	/**
	 * @return the factory
	 */
	public DefaultListableBeanFactory getFactory() {

		return factory;

	}

	public Object get(Class<?> clazz) {
		try {
			return factory.getBean(clazz.getName());
		} catch (NoSuchBeanDefinitionException e) {
			Map beans = BeanFactoryUtils.beansOfTypeIncludingAncestors(factory, clazz);
			if (beans.isEmpty()) {
				return instantiateClassByBestEfforts(clazz);
			}
			return beans.values().iterator().next();
		}

	}

	public void addInstance(Object bean) {
		factory.registerSingleton(bean.getClass().getName(), bean);
	}

	public void dispose() {

		factory.destroySingletons();
	}

	private Object instantiateClassByBestEfforts(Class<?> clazz) {
		ContainerDI container = new ContainerDI(this);
		container.add(clazz);
		return container.get(clazz);
	}

	public Class<?>[] getRegisteredClasses() {
		Set<Class> classes = new HashSet<Class>();
		String[] beanDefinitionNames = factory.getBeanDefinitionNames();
		for (int beanIndex = 0; beanIndex < beanDefinitionNames.length; beanIndex++) {
			String beanDefinitionName = beanDefinitionNames[beanIndex];
			try {
				Class<?> aClass = Class.forName(beanDefinitionName);
				if (aClass != null) {
					classes.add(aClass);
				}
			} catch (ClassNotFoundException e) {
			}
		}
		return (Class<?>[]) classes.toArray(new Class[0]);
	}

	public static class Description {
		private final DefaultListableBeanFactory factory;
		private AbstractBeanDefinition definition;
		private final AbstractBeanDefinition proxy;
		private final MutablePropertyValues propertyValues;

		boolean proxyRegistered = false;

		public Description(DefaultListableBeanFactory factory, AbstractBeanDefinition definition) throws ClassNotFoundException {
			this.factory = factory;
			this.definition = definition;

			// ConstructorArgumentValues constructorValues = new
			// ConstructorArgumentValues();
			propertyValues = new MutablePropertyValues();

			// this.proxy= BeanDefinitionReaderUtils.createBeanDefinition(
			// BeanNameAutoProxyCreator.class.getName(), null,
			// definition.getBeanClass().getClassLoader());
			this.proxy = BeanDefinitionReaderUtils.createBeanDefinition(null, BeanNameAutoProxyCreator.class.getName(), definition.getBeanClass()
					.getClassLoader());

			propertyValues.addPropertyValue("beanNames", definition.getBeanClassName());

		}

		public Description toUse(Object toUseInConstructor) {
			definition.getConstructorArgumentValues().addGenericArgumentValue(toUseInConstructor);
			return this;
		}

		public Description interceptWith(Class<?> injector) {
			propertyValues.addPropertyValue("interceptorNames", injector.getName());
			if (!proxyRegistered) {
				proxyRegistered = true;
				String proxyBeanName = "proxy.for" + definition.getBeanClassName();
				factory.registerBeanDefinition(proxyBeanName, this.proxy);
				Object proxyBean = factory.getBean(proxyBeanName);
				factory.addBeanPostProcessor((BeanPostProcessor) proxyBean);

			}
			return this;
		}
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		// do nothing as we don't want to serilaize cache data
		System.out.println("No data");
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		ContainerDI parentContainer = null;
		if (parentContainer != null) {
			factory = new DefaultListableBeanFactory(parentContainer.factory);
		}

	}

}
