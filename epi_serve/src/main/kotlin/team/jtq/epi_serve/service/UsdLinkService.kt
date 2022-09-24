package team.jtq.epi_serve.service

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


interface UsdLinkService {
    fun <T : Any, V : Any> addLinkinBeans(
        linkClazz: KClass<T>,
        linkBean: KClass<V>,
        targetPair: Pair<String, String>,
        sourcePair: Pair<String, String>
    ): Boolean

    fun <T:Any,V:Any> addLinkBeans(
        linkClazz:KClass<T>,
        linkBean:KClass<V>,
        items: Map<KProperty<*>, String>
    ): Boolean

    fun <T : Any, V : Any> deleteLinkinBeans(
        linkClazz: KClass<T>,
        linkBean: KClass<V>,
        items: List<Pair<KProperty<*>, String>>
    ): Boolean

    fun <T : Any, V : Any> selectLinkinBeans(
        linkClazz:KClass<T>,
        linkBean:KClass<V>,
        items:List<Pair<KProperty<*>,String>>
    ): ArrayList<V>?

    fun <T:Any,V:Any> selectLinkinBeansonPage(
        linkClazz:KClass<T>,
        linkBean:KClass<V>,
        items:List<Pair<KProperty<*>,String>>,
        page: Page<V>
    ):Page<V>?

    fun <T:Any,V:Any> batchSelectLinkBeansInList(
        linkClazz:KClass<T>,
        linkBean:KClass<V>,
        items:Pair<KProperty<*>,List<String>>
    ):ArrayList<V>?

    fun <T:Any,V:Any> batchSelectLinkBeansInListOnPage(
        linkClazz:KClass<T>,
        linkBean:KClass<V>,
        items:Pair<KProperty<*>,List<String>>,
        page: Page<V>
    ):Page<V>?

    fun <T:Any,V:Any> batchSelectLinkBeansNotInList(
        linkClazz:KClass<T>,
        linkBean:KClass<V>,
        items:Pair<KProperty<*>,List<String>>
    ):ArrayList<V>?

    fun <T:Any,V:Any> countLinkBeans(
        linkClazz:KClass<T>,
        linkBean:KClass<V>,
        items: List<Pair<KProperty<*>, String>>
    ): Long?

}