package com.novedia.talentmap.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.ApplicationServlet;

/**
 * Spring servlet to manage the application context with Spring.
 * 
 * @author j.marie-sainte
 */
public class SpringVaadinServlet extends ApplicationServlet  {
	
	
	/**
	 * Serialization identifier
	 */
	private static final long serialVersionUID = 677906720752307288L;
	
	/**
	 * Web application context
	 */
	private WebApplicationContext webApplicationContext;

	/**
	 * Initialize this servlet.
	 * 
	 * @throws ServletException
	 *             if there is no {@link WebApplicationContext} associated with
	 *             this servlet's context
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		
		try {
			this.webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		} catch (IllegalStateException e) {
			throw new ServletException("could not locate containing WebApplicationContext");
		}
	}
	
	/**
	 * Get the containing Spring {@link WebApplicationContext}. This only works
	 * after the servlet has been initialized (via {@link #init init()}).
	 * 
	 * @throws ServletException
	 *             if the operation fails
	 */
	protected final WebApplicationContext getWebApplicationContext() throws ServletException {
		
		if (this.webApplicationContext == null){
			throw new ServletException("can't retrieve WebApplicationContext before init() is invoked");
		}
			
		return this.webApplicationContext;

	}
	
	
	/**
	 * Get the {@link AutowireCapableBeanFactory} associated with the containing
	 * Spring {@link WebApplicationContext}. This only works after the servlet
	 * has been initialized (via {@link #init init()}).
	 * 
	 * @throws ServletException
	 *             if the operation fails
	 */
	protected final AutowireCapableBeanFactory getAutowireCapableBeanFactory()
			throws ServletException {
		try {
			return getWebApplicationContext().getAutowireCapableBeanFactory();
		} catch (IllegalStateException e) {
			throw new ServletException("containing context "+ getWebApplicationContext() + " is not autowire-capable", e);
		}
	}
	
	/**
	 * Create and configure a new instance of the configured application class.
	 * 
	 * <p>
	 * The implementation in {@link AutowiringApplicationServlet} delegates to
	 * {@link #getAutowireCapableBeanFactory getAutowireCapableBeanFactory()},
	 * then invokes {@link AutowireCapableBeanFactory#createBean
	 * AutowireCapableBeanFactory.createBean()} using the configured
	 * {@link Application} class.
	 * </p>
	 * 
	 * @param request
	 *            the triggering {@link HttpServletRequest}
	 * @throws ServletException
	 *             if creation or autowiring fails
	 */
	@Override
	protected Application getNewApplication(HttpServletRequest request)	throws ServletException {
		
		Class<? extends Application> cl = null;
		try {
			cl = getApplicationClass();
		} catch (ClassNotFoundException e1) {
			throw new ServletException("getNewApplication failed", e1);
		}
		
		AutowireCapableBeanFactory beanFactory = getAutowireCapableBeanFactory();
		
		try {
			return beanFactory.createBean(cl);
		} catch (BeansException e) {
			throw new ServletException("failed to create new instance of " + cl, e);
		}
	}

}
