package org.example.service

import model.entity.User
import org.springframework.stereotype.Service

@Service
class UserService {
    private val users = mutableListOf<User>()
    private var nextId = 1L

    fun findAll(): List<User> = users

    fun findById(id: Long): User? = users.find { it.id == id }

    fun save(name: String, email: String): User {
        val user = User(nextId++, name, email)
        users.add(user)
        return user
    }

    fun deleteById(id: Long) {
        users.removeAll { it.id == id }
    }

}