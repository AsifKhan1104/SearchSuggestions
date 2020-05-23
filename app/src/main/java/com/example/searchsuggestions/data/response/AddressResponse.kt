package com.example.searchsuggestions.data.response

data class AddressResponse(
    val `data`: Data,
    val requestId: String
)

data class Data(
    val addressList: List<Address>,
    val autoCompleteRequestString: String,
    val focusWord: String
)

data class Address(
    val addressString: String,
    val city: String,
    val id: String,
    val label: String,
    val latitude: Double,
    val longitude: Double,
    val pinCode: String
)