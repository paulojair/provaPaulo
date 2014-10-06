package br.gov.saude.esus.prova.common.api;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceInjector {

	// Class Declarations

	private static ServiceInjector instance;

	public static ServiceInjector getInstance() {
		if (instance == null) {
			try {
				InitialContext jndiContext = new InitialContext();
				BeanManager manager = (BeanManager) jndiContext.lookup("java:comp/BeanManager");
				instance = new ServiceInjector(manager);
			} catch (NamingException e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}

	// Constructor

	private ServiceInjector(BeanManager beanManager) {
		super();
		this.beanManager = beanManager;
		this.injectors = new HashMap<Class<?>, InjectionTarget<?>>();
	}

	private BeanManager beanManager;

	private Map<Class<?>, InjectionTarget<?>> injectors;

	public <T> void inject(T instance) {
		this.getInjector(instance).inject(instance, this.beanManager.createCreationalContext((Contextual<T>) null));
	}

	@SuppressWarnings("unchecked")
	private <T> InjectionTarget<T> getInjector(T instance) {
		InjectionTarget<T> injector = (InjectionTarget<T>) this.injectors.get(instance.getClass());
		if (injector == null) {
			synchronized (this) {
				injector = (InjectionTarget<T>) this.injectors.get(instance.getClass());
				if (injector == null) {
					AnnotatedType<? extends T> createAnnotatedType = (AnnotatedType<? extends T>) this.beanManager.createAnnotatedType(instance.getClass());

					injector = (InjectionTarget<T>) this.beanManager.createInjectionTarget(createAnnotatedType);
					this.injectors.put(instance.getClass(), injector);
				}
			}
		}
		return injector;
	}

}