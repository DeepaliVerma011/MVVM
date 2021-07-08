package com.deepaliverma.mvvm.data.repos

import com.deepaliverma.mvvm.data.api.Client

object GithubRepository {

    suspend fun getUsers()= Client.api.getUsers()

    suspend fun searchUsers(name:String)=Client.api.searchUsers(name)
}