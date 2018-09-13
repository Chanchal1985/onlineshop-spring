package shop.config;

import com.sample.online.controller.CustomerResource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/controller")
public class ShoppingApplication extends Application {

    protected ApplicationContext springContext;

    @Context
    protected ServletContext servletContext;


    @Override
    public Set<Class<?>> getClasses() {
        return Collections.emptySet();
    }

    @Override
    public Set<Object> getSingletons() {
        try
        {
            InitialContext ctx = new InitialContext();
            String xmlFile = (String)servletContext.getInitParameter("spring-beans-file");
            springContext = new ClassPathXmlApplicationContext(xmlFile);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

        Set<Object> singletons = new HashSet<>();
        singletons.add(springContext.getBean(CustomerResource.class));
        return singletons;
    }
}