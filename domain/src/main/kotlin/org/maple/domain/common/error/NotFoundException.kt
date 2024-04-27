package org.maple.domain.common.error

import kotlin.reflect.KClass

class NotFoundException : MapleFusionRuntimeException {
    constructor(detail: String, cause: Throwable? = null) :
            super("Not Found", cause)
    constructor(clazz: KClass<*>) : super(
        title = "Not Found",
        message = "${clazz.simpleName!!.uppercase()} Not Found"
    )
}