package team.jtq.auth.oauth_serve.tools

import org.springframework.util.StringUtils

private const val SEPARATOR_COMMA = ","


fun getSetBySplit(content: String): Set<String> {
    return getSetBySplit(content, SEPARATOR_COMMA)
}

fun getSetBySplit(content: String, separator: String): Set<String> {
    val set: MutableSet<String> = HashSet()
    if (StringUtils.hasText(content)) {
        return set
    }
    for (item in content.split(separator.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
        set.add(item)
    }
    return set
}