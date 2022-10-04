package team.jtq.epi_serve.service.imp

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.stereotype.Service
import team.jtq.epi_serve.config.BeanContext
import team.jtq.epi_serve.service.MapperReflectionService
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties

@Service
class MapperReflectionServiceImp : MapperReflectionService {

    override fun <T : Any, V : Any> addLinkinBeans(
        linkClazz: KClass<T>,
        linkBean: KClass<V>,
        targetPair: Pair<String, String>,
        sourcePair: Pair<String, String>
    ): Boolean {
        val instantLink: Any
        try {
            instantLink = BeanContext.getBeanbyClazz(linkClazz.java) as BaseMapper<V>
            val obj = linkBean.java.newInstance()
            val pro = linkBean.declaredMemberProperties
            pro.forEach {
                val kmp = it as KMutableProperty1<Any, Any?>
                if (it.name == targetPair.first) {
                    kmp.set(obj, targetPair.second)
                }
                if (it.name == sourcePair.first) {
                    kmp.set(obj, sourcePair.second)
                }
            }
            instantLink.insert(obj)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun <T : Any, V : Any> addLinkBeans(
        linkClazz: KClass<T>,
        linkBean: KClass<V>,
        items: Map<KProperty<*>, String>
    ): Boolean {
        val instantLink: Any
                try {
                    instantLink = BeanContext.getBeanbyClazz(linkClazz.java) as BaseMapper<V>
                    val obj = linkBean.java.newInstance()
                    val pro = linkBean.declaredMemberProperties
                    pro.forEach {
                        val kmp = it as KMutableProperty1<Any, Any?>
                        if(items.containsKey(it)){
                            kmp.set(obj,items[it])
                        }
                    }
                    instantLink.insert(obj)
                    return true
                } catch (e: Exception) {
                    return false
                }
    }

    override fun <T : Any, V : Any> deleteLinkinBeans(
        linkClazz: KClass<T>,
        linkBean: KClass<V>,
        items: List<Pair<KProperty<*>, String>>
    ): Boolean {
        val query = KtQueryWrapper(linkBean.java)
        try {
            val instantLink = BeanContext.getBeanbyClazz(linkClazz.java) as BaseMapper<V>
            items.forEach {
                i->query.eq(i.first,i.second)
            }
            instantLink.delete(query)
        } catch (e: Exception) {
            return false
        }
        return true
    }

    override fun <T : Any, V : Any> selectLinkinBeans(
        linkClazz: KClass<T>,
        linkBean: KClass<V>,
        items: List<Pair<KProperty<*>, String>>
    ): ArrayList<V>? {
        val query = KtQueryWrapper(linkBean.java)
        val res :ArrayList<V>
        return try{
            val instantLink = BeanContext.getBeanbyClazz(linkClazz.java) as BaseMapper<V>
            items.forEach { i->query.eq(i.first,i.second) }
            res = instantLink.selectList(query).toCollection(ArrayList<V>())
            res
        }catch (e:Exception){
            null
        }
    }

    override fun <T : Any, V : Any> selectLinkinBeansonPage(
        linkClazz: KClass<T>,
        linkBean: KClass<V>,
        items: List<Pair<KProperty<*>, String>>,
        page: Page<V>
    ): Page<V>? {
        val query = KtQueryWrapper(linkBean.java)
        val res : Page<V>
        return try{
            val instantLink = BeanContext.getBeanbyClazz(linkClazz.java) as BaseMapper<V>
            items.forEach { i->query.eq(i.first,i.second) }
            res = instantLink.selectPage(page,query)
            res
        }catch (e:Exception){
            null
        }
    }

    override fun <T : Any, V : Any> batchSelectLinkBeansInList(
        linkClazz: KClass<T>,
        linkBean: KClass<V>,
        items: Pair<KProperty<*>, List<String>>
    ): ArrayList<V>? {
        val query = KtQueryWrapper(linkBean.java)
        return try{
            val instantLink = BeanContext.getBeanbyClazz(linkClazz.java) as BaseMapper<V>
            query.`in`(items.first,items.second)
            instantLink.selectList(query).toCollection(ArrayList<V>())
        }catch (e:Exception){
            null
        }
    }

    override fun <T : Any, V : Any> limitSelectLinkBeansInList(
        linkClazz: KClass<T>,
        linkBean: KClass<V>,
        items: Pair<KProperty<*>, List<String>>,
        limit: Pair<Int, Int>
    ): ArrayList<V>? {
        val query = KtQueryWrapper(linkBean.java)
        return try{
            val instantLink = BeanContext.getBeanbyClazz(linkClazz.java) as BaseMapper<V>
            query.`in`(items.first,items.second)
            query.last("limit "+limit.first+","+limit.second)
            instantLink.selectList(query).toCollection(ArrayList<V>())
        }catch (e:Exception){
            null
        }
    }

    override fun <T : Any, V : Any> batchSelectLinkBeansInListOnPage(
        linkClazz: KClass<T>,
        linkBean: KClass<V>,
        items: Pair<KProperty<*>, List<String>>,
        page: Page<V>
    ): Page<V>? {
        if(items.second.isEmpty())
        {
            return Page<V>()
        }
        val query = KtQueryWrapper(linkBean.java)
        return try{
            val instantLink =BeanContext.getBeanbyClazz(linkClazz.java) as BaseMapper<V>
            query.`in`(items.first,items.second)
            instantLink.selectPage(page,query)
        }catch (e:Exception){
            null
        }
    }

    override fun <T : Any, V : Any> batchSelectLinkBeansNotInList(
        linkClazz: KClass<T>,
        linkBean: KClass<V>,
        items: Pair<KProperty<*>, List<String>>
    ): ArrayList<V>? {
        val query = KtQueryWrapper(linkBean.java)
        val res :ArrayList<V>
        return try{
            val instantLink = BeanContext.getBeanbyClazz(linkClazz.java) as BaseMapper<V>
            query.notIn(items.first,items.second)
            res = instantLink.selectList(query).toCollection(ArrayList<V>())
            res
        }catch (e:Exception){
            null
        }
    }

    override fun <T : Any, V : Any> countLinkBeans(
        linkClazz: KClass<T>,
        linkBean: KClass<V>,
        items: List<Pair<KProperty<*>, String>>
    ): Long? {
        val query = KtQueryWrapper(linkBean.java)
        return try{
            val instantLink = BeanContext.getBeanbyClazz(linkClazz.java) as BaseMapper<V>
            items.forEach {
                query.eq(it.first,it.second)
            }
            instantLink.selectCount(query)
        }catch (e:Exception)
        {
            null
        }
    }

    override fun <T : Any, V : Any> limlitOrderSelectBeansInList(
        linkClazz: KClass<T>,
        linkBean: KClass<V>,
        items: List<Pair<KProperty<*>, List<String>>>,
        orderType: String,
        limit: Pair<Int, Int>,
        orderProperty: KProperty<*>
    ): List<V>? {
        val query = KtQueryWrapper(linkBean.java)
        return try{
            val instantLink = BeanContext.getBeanbyClazz(linkClazz.java) as BaseMapper<V>
            items.forEach {
                query.`in`(it.first, it.second)
            }
            when (orderType){
                "ASC"->{
                       query.orderByAsc(orderProperty)
                }
                "DSEC"->{
                    query.orderByDesc(orderProperty)
                }
            }
            query.last("limit "+limit.first+","+limit.second)
            instantLink.selectList(query)
        }catch (e:Exception){
            null
        }
    }

}