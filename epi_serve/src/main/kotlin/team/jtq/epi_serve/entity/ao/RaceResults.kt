package team.jtq.epi_serve.entity.ao

import com.alibaba.excel.annotation.ExcelProperty
import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class RaceResults(
    @ExcelProperty(value = ["附件id"], index = 0)
    val annexId: String,
    @ExcelProperty(value = ["获奖等级"], index = 1)
    val award: String?
)
