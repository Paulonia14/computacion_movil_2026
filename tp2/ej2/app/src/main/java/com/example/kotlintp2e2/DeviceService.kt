package com.example.kotlintp2e2

import retrofit2.http.GET

interface DeviceService {
    @GET(Constants.OBJECTS_PATH)
    suspend fun getAllDevices(): List<Device>
}