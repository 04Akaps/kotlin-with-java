package org.example.controller

import model.entity.User
import org.example.custom.requeest.annotation.PageCustomAnnotation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.example.service.UserService;

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers(): List<User>  {
        return  userService.findAll()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User>  {
        val res = userService.findById(id)?.let { ResponseEntity.ok(it) }

        return res ?: ResponseEntity.notFound().build()
    }


    @PostMapping
    fun createUser(@RequestBody user: User): User {
        return userService.save(user.name, user.email)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        userService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/test")
    fun apiTest() {
        println("test");
    }

    @GetMapping("/page-custom-annotations")
    fun pagingAnnotation(@PageCustomAnnotation(defaultValue = "ASC") order: String) {
        println("Order: $order")
    }
}