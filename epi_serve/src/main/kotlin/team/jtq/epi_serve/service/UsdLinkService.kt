package team.jtq.epi_serve.service

import kotlin.reflect.KClass
import kotlin.reflect.KProperty


interface UsdLinkService {
    fun <T : Any, V : Any> addLinkinBeans(
        linkClazz: KClass<T>,
        linkBean: KClass<V>,
        targetPair: Pair<String, String>,
        sourcePair: Pair<String, String>
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


}