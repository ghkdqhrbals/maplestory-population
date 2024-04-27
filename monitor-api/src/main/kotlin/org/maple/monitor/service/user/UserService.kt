package org.maple.monitor.service.user

import jakarta.transaction.Transactional
import org.maple.domain.user.User
import org.maple.domain.user.UserRepository
import org.maple.domain.user.findByIdOrThrow
import org.springframework.stereotype.Service

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository
) {
    fun findUserById(id: Long) = userRepository.findByIdOrThrow(id)
    fun findUserByName(name: String) = userRepository.findByName(name)
    fun saveUser(userName: String) : User {
        return userRepository.save(User(name = userName))
    }
    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
    fun updateUser(id: Long, userName: String) {
        val user = userRepository.findByIdOrThrow(id)
        user.name = userName
        userRepository.save(user)
    }
    fun findAll(): MutableList<User> = userRepository.findAll()
    fun findByExtId(extId: String): User? = userRepository.findByExtId(extId)

}