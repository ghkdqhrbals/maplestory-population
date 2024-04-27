package org.maple.config.aop

import jakarta.persistence.EntityManager
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Aspect
@Component
class EntityTracker {
    @Autowired
    private lateinit var entityManager: EntityManager

    @Pointcut("execution(* jakarta.persistence.EntityManager.flush(..))")
    fun entityManagerFlushOperation() {}

    @AfterReturning("entityManagerFlushOperation()")
    fun logJpaFlushOperation(joinPoint: JoinPoint) {
        val flushedEntities = entityManager.flush()
        println("Flush operation executed. Flushed entities: $flushedEntities")
    }
}