package com.example.billz.repository

import com.example.billz.api.ApiClient
import com.example.billz.api.ApiInterface
import com.example.billz.model.LoginRequest
import com.example.billz.model.LoginResponse
import com.example.billz.model.RegisterRequest
import com.example.billz.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class Login {
    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return withContext(Dispatchers.IO) {
            val apiClient  = ApiClient.buildApiClient(ApiInterface::class.java)
            apiClient.loginUser(loginRequest)
        }

    }

    }



