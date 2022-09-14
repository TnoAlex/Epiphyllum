package team.jtq.auth.oauth_serve.entity

import lombok.Data

@Data
data class OauthAppRegister(
    val opId:String,
    val appName:String,
    val authorizedGrantTypes: String,
    val authorities: String,
    val scope:String,
    val refreshTokenValidity:Int,
    val redirectUri: String,
    val accessTokenValidity: Int,
    val trusted: Int,
)
