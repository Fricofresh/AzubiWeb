package de.dpma.azubiweb;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;

import de.dpma.azubiweb.service.UserSession;
import de.dpma.azubiweb.view.LoginPage;
import de.dpma.azubiweb.view.StartPage;

@SpringBootApplication
public class ApplicationStart extends WicketBootSecuredWebApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(ApplicationStart.class, args);
		
	}
	
	@Override
	public Class<? extends Page> getHomePage() {
		
		return StartPage.class;
	}
	
	// @Override
	// public Session newSession(Request request, Response response) {
	//
	// return new UserSession(request);
	// }
	
	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		
		return UserSession.class;
	}
	
	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		
		return LoginPage.class;
	}
	
	// @Override
	// public void init() {
	//
	// mountPage("/LoginPage", LoginPage.class);
	
	// IUnauthorizedComponentInstantiationListener iucil = new
	// IUnauthorizedComponentInstantiationListener() {
	//
	// @Override
	// public void onUnauthorizedInstantiation(Component component) {
	//
	// component.setResponsePage(LoginPage.class, new
	// PageParameters().add("auth", "failed"));
	// }
	// };
	// getSecuritySettings().setUnauthorizedComponentInstantiationListener(iucil);
	// getApplicationSettings().setPageExpiredErrorPage(LoginPage.class);
	// getApplicationSettings().setAccessDeniedPage(LoginPage.class);
	// }
}
