package org.maple.domain.users

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository  : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
    fun existsByPhone(phone: String): Boolean
    fun findByPhone(phone: String): User?

}
