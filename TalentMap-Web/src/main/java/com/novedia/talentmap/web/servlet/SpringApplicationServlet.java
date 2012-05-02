package com.novedia.talentmap.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.novedia.talentmap.services.impl.CollaboratorService;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

/**
 * 
 * @author j.marie-sainte
 * 
 */
public class SpringApplicationServlet extends AbstractApplicationServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * spring bean name in init of servlet
	 */
	public static final String PARAMETER_APPLICATION_BEAN_NAME = "applicationBean";
	
	private String applicationBeanName;

	@Override
	public void init(javax.servlet.ServletConfig servletConfig)	throws javax.servlet.ServletException {
		super.init(servletConfig);
		applicationBeanName = getApplicationProperty(PARAMETER_APPLICATION_BEAN_NAME);
		if (applicationBeanName == null) {
			throw new ServletException("Failed to load application class because bean name is null");
		}
		//checkSpringBean();
	}
	
	/**
	 * Check the main Spring bean
	 * @throws Exception 
	 */
//	private void checkSpringBean() throws Exception{
//		
//		if(applicationBeanName != null){
//			if(getSpringContext() == null){
//				throw new ServletException("Failed to load application. Spring context is null");
//			}
//			
//			if(!getSpringContext().containsBean(this.applicationBeanName)){
////				String[] beanDefinitionNames = getSpringContext().getBeanDefinitionNames();
////				StringBuilder sb = new StringBuilder();
////				
////				for (String string : beanDefinitionNames) {
////					if(string.equals("vaadinBean") || string.equals("tmServiceBean")){
////						sb.append(string);					
////					}
////				}
////				throw new ServletException("Succeed to load application bean : " + sb.toString() );
//				// Mocked implementation for skill DAO
//				CollaboratorService serviceBean= (CollaboratorService)getSpringContext().getBean("profileService");
//				throw new ServletException("Value of bean : " +  serviceBean.getCollaborator(1).toString());
////				throw new ServletException("Failed to load application bean : " +  this.applicationBeanName);
//			}
//		}
//	}
	
	/**
	 * Get the Spring web application context
	 * @return
	 */
	private WebApplicationContext getSpringContext() {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		return context;
	}

	@Override
	protected Application getNewApplication(HttpServletRequest request)	throws ServletException {
		return (Application) getSpringContext().getBean(applicationBeanName);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {
		return (Class<? extends Application>) getSpringContext().getBean(this.applicationBeanName).getClass();
	}

}
