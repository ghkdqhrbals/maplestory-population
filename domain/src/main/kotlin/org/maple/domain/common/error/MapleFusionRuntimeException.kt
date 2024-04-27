package org.maple.domain.common.error

inline fun defaultHttpStatus(): Int = 500

abstract class MapleFusionRuntimeException(
    val title: String,
    val ex: Throwable? = null,
    open val httpStatus: Int = defaultHttpStatus(),
    override val message: String = "Maple Fusion Runtime Exception"
) : RuntimeException(title, ex) {

}