package com.mineobank.app.network

import com.mineobank.app.data.model.support.SupportTicket
import com.mineobank.app.data.model.support.SupportTicketRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SupportApiService {

    @GET("support/tickets")
    suspend fun getTickets(): Response<List<SupportTicket>>

    @POST("support/tickets")
    suspend fun createTicket(@Body request: SupportTicketRequest): Response<SupportTicket>
}
