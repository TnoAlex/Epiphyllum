package team.jtq.auth.oauth_serve.config

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
object BeanContext:ApplicationContextAware {

    private  lateinit var applicationContext: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        BeanContext.applicationContext = applicationContext
    }

    fun <T> getBeanbyName(name:String): T {
        return applicationContext.getBean(name) as T
    }

    fun <T> getBeanbyClazz(clazz:Class<T>):T{
        return applicationContext.getBean(clazz)
    }

    fun <T> getBeanbyNameAndClazz(name:String,clazz: Class<T>):T{
        return applicationContext.getBean(name,clazz)
    }
}