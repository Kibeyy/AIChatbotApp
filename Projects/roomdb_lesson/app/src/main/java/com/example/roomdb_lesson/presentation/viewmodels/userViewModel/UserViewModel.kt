package com.example.roomdb_lesson.presentation.viewmodels.userViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdb_lesson.data.local.userDao.userDao
import com.example.roomdb_lesson.data.local.userEntity.userEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userDao: userDao ):ViewModel() {

    val allUsers = userDao.getAllUsers().asLiveData()

    fun addUser(userEntity: userEntity){
        viewModelScope.launch {
            userDao.insertUser(userEntity)
        }
    }

    fun deleteUser(userEntity: userEntity){
        viewModelScope.launch {
            userDao.deleteUser(userEntity)
        }
    }

}