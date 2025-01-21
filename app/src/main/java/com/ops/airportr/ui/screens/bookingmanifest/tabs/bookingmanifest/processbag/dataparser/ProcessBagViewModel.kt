package com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingmanifest.processbag.dataparser

import android.os.Parcelable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.parcelize.Parcelize

@Parcelize
data class PassengerDetails(
    val bookingRef: String,
    val bookingJourneyRef: String,
    val passengerName: String,
    val passengerId: String,
    val passengerLuggageId: String,
    val passengerLuggageCode: String
) : Parcelable
