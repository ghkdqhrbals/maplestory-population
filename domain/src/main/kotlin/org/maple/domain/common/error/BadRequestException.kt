package org.maple.domain.common.error

class BadRequestException(
    val detail: String?,
): MapleFusionRuntimeException(
    title = "Bad Request",
    message = detail?: "Bad Request",
    httpStatus = 400
) {

}