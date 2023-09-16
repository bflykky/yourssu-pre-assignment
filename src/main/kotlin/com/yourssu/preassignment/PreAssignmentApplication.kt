package com.yourssu.preassignment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = arrayOf(SecurityAutoConfiguration::class))
class PreAssignmentApplication

fun main(args: Array<String>) {
	runApplication<PreAssignmentApplication>(*args)
}
