package team.jtq.auth.oauth_serve.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanContext implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        BeanContext.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @NotNull
    public static <T> T getBean(String name) throws BeansException{
        return (T)applicationContext.getBean(name);
    }

    @NotNull
    public static <T> T getBean(Class<T> clazz) throws BeansException{
        return (T)applicationContext.getBean(clazz);
    }

    public static <T> T getBean(Class<T> clazz,String name) throws BeansException{
        return applicationContext.getBean(name,clazz);
    }
}
