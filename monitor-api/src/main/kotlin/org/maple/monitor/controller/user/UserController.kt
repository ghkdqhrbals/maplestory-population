package org.maple.monitor.controller.user

import org.maple.domain.common.error.NotFoundException
import org.maple.domain.user.User
import org.maple.monitor.service.user.UserService
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UserController (
    private val userService: UserService
){
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello, User!"
    }
    @GetMapping("/users")
    fun getUsers(): MutableList<User> {
        return userService.findAll()
    }
    @GetMapping("/user/{extId}")
    fun getUser(@PathVariable("extId") extId: String): User {
        return userService.findByExtId(extId)?: throw NotFoundException("User not found")
    }
    @PostMapping("/user")
    fun saveUser(@Param("name") name: String): User {
        return userService.saveUser(name)
    }
}