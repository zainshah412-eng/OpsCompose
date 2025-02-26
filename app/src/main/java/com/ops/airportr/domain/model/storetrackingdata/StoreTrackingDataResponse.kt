package com.ops.airportr.domain.model.storetrackingdata

data class StoreTrackingDataResponse(
    var description: Any,
    var responseStatus: Int,
    var validationErrorMessages: Any,
    var validationErrors: List<Any>
)
