package fr.yjk.mobility.health.utils.extensions

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okio.IOException
import retrofit2.HttpException
import java.net.UnknownHostException

enum class FailureType {
    BadRequest,
    Unauthorized,
    Forbidden,
    NotFound,
    Validation,
    UnknownHostException,
    NetworkError,
    Error
}

data class Failure(
    val message: String,
    val errors: Map<String, List<String>>? = null,
    val type: FailureType
)

/*sealed class Failure {
    data class ErrorBadRequest(val message: String) : Failure()
    data class ErrorUnauthorized(val message: String) : Failure()
    data class ErrorForbidden(val message: String) : Failure()
    data class ErrorNotFound(val message: String) : Failure()
    data class ErrorValidation(val message: String, val errors: Map<String, List<String>>?) :
        Failure()

    data class UnknownHostException(val message: String) : Failure()
    data class NetworkError(val message: String) : Failure()
    data class Error(val message: String) : Failure()
}*/

@Serializable
data class RequestError(val message: String, val errors: Map<String, List<String>>? = null)

fun Throwable.toFailure() = when (this) {

    is HttpException -> {
        when (response()?.code()) {
            400 -> Failure(message = "Mauvaise requête.", type = FailureType.BadRequest)
            401 -> Failure(message = "Accès non autorisé", type = FailureType.Unauthorized)
            403 -> Failure(message = "Accès interdit", type = FailureType.Forbidden)
            404 -> Failure(message = "Ressource introuvable", type = FailureType.NotFound)
            422 -> {
                val raw =
                    java.lang.String(response()?.errorBody()?.bytes(), Charsets.UTF_8)
                        .toString()
                println(raw)
                val errors = Json.decodeFromString<RequestError>(raw)
                Failure(message = errors.message, type = FailureType.Validation)
            }

            else -> Failure(
                type = FailureType.Error,
                message = "Quelque chose s'est mal passé. Code d'erreur: ${this.code()}",
            )
        }
    }

    is UnknownHostException -> Failure(
        type = FailureType.UnknownHostException,
        message = "Pas de connexion Internet. Veuillez vérifier votre connexion et réessayer"
    )

    is IOException -> Failure(
        type = FailureType.NetworkError,
        message = "Une erreur inconnue est survenue"
    )

    else -> Failure(
        type = FailureType.Error,
        message = "Quelque chose s'est mal passé. message d'erreur: ${this.message}"
    )
}

