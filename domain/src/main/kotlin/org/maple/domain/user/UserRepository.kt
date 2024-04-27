package org.maple.domain.user

import org.maple.domain.common.error.NotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

inline fun <reified T, ID> JpaRepository<T, ID>.findByIdOrThrow(id: ID): T {
    return this.findByIdOrNull(id) ?: throw NotFoundException(T::class)
}


interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): User?
    fun findByExtId(extId: String): User?
}

