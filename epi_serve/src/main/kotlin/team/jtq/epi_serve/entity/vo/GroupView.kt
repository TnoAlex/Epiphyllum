package team.jtq.epi_serve.entity.vo

import team.jtq.epi_serve.annotation.NoArg

@NoArg
data class GroupView(
    var gid:String,
    var groupName:String,
    var groupDescription:String,
    var groupIco:String,
    var createTime:String,
    var createUser:String,
    var recentlyJoined:List<String>,
    var nickNames:List<String>,
    var joinedNum:Long
)
