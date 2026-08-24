package br.com.github.sprint3.navegation

import kotlinx.serialization.Serializable

@Serializable
object SplashRoute

@Serializable
object LoginRoute

@Serializable
object HomeRoute

@Serializable
object TurmasRoute

@Serializable
object CadastroRoute

@Serializable
object DashboardRoute

@Serializable
data class StudentsRoute(val turmaId: String)

