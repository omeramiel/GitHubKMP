package com.omeram.kotlin.githubkmp.api

sealed class Errors : Throwable() {
    class UpdateProblem : Errors()
    class ConnectionProblem : Errors()
}