package hr.spring.uservice.application.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    private ApplicationContextProvider() {}

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static JpaRepository getRepository(Class clazz) {
        Repositories repositories = new Repositories(context);
        return (JpaRepository) repositories.getRepositoryFor(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        context = ctx;
    }

}
