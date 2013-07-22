package com.novedia.talentmap.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

/**
 * This class is the servlet mapped in the web.xml file. This is the entry point
 * for the web context of the application.
 * 
 * @author j.marie-sainte
 */
public class SpringApplicationServlet extends AbstractApplicationServlet {

    /**
     * Serialization identifier
     */
    private static final long serialVersionUID = 1L;

    /**
     * Spring bean name in init of servlet
     */
    public static final String PARAMETER_APPLICATION_BEAN_NAME = "applicationBean";

    private String applicationBeanName;

    @Override
    public void init(javax.servlet.ServletConfig servletConfig)
	    throws javax.servlet.ServletException {
	super.init(servletConfig);
	applicationBeanName = getApplicationProperty(PARAMETER_APPLICATION_BEAN_NAME);
	if (applicationBeanName == null) {
	    throw new ServletException(
		    "Failed to load application class because bean name is null");
	}
    }

    /**
     * Get the Spring web application context
     * 
     * @return
     */
    private WebApplicationContext getSpringContext() {
	WebApplicationContext context = WebApplicationContextUtils
		.getWebApplicationContext(getServletContext());
	return context;
    }

    @Override
    protected Application getNewApplication(HttpServletRequest request)
	    throws ServletException {
	return (Application) getSpringContext().getBean(applicationBeanName);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<? extends Application> getApplicationClass()
	    throws ClassNotFoundException {
	return (Class<? extends Application>) getSpringContext().getBean(
		this.applicationBeanName).getClass();
    }

}
