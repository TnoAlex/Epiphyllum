package team.jtq.auth.oauth_serve.tools

import org.springframework.util.StringUtils

private const val SEPARATOR_COMMA = ","


fun getSetBySplit(content: String): Set<String> {
    val set: MutableSet<String> = HashSet()
    if (!StringUtils.hasText(content)) {
        return set
    }
    for (item in content.split(SEPARATOR_COMMA.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
        set.add(item)
    }
    return set
}