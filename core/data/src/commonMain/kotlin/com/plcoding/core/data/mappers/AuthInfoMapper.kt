package com.plcoding.core.data.mappers

import com.plcoding.core.data.dto.AuthInfoSerializable
import com.plcoding.core.data.dto.UserSerializable
import com.plcoding.core.domain.auth.AuthInfo
import com.plcoding.core.domain.auth.User

fun AuthInfoSerializable.toInfo() = AuthInfo(
    accessToken = accessToken,
    refreshToken = refreshToken,
    user = user.toUser()
)

fun UserSerializable.toUser() = User(
    id = id,
    email = email,
    hasVerifiedEmail = hasVerifiedEmail,
    username = username,
    profilePictureUrl = profilePictureUrl
)

fun AuthInfo.toSerializable() = AuthInfoSerializable(
    accessToken = accessToken,
    refreshToken = refreshToken,
    user = user.toSerializable()
)

fun User.toSerializable() = UserSerializable(
    id = id,
    email = email,
    hasVerifiedEmail = hasVerifiedEmail,
    username = username,
    profilePictureUrl = profilePictureUrl
)