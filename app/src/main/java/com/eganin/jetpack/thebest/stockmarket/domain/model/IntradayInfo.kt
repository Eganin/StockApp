package com.eganin.jetpack.thebest.stockmarket.domain.model

import java.time.LocalDateTime

data class IntradayInfo(
    val date: LocalDateTime,
    val close: Double,
)