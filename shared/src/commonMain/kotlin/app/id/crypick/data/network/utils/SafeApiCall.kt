package app.id.crypick.data.network.utils

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.util.network.UnresolvedAddressException

suspend fun <T : Any?> safeApiCall(apiCall: suspend () -> T): NetworkResultState<T> {
    return try {
        NetworkResultState.Loading

        NetworkResultState.Success(apiCall.invoke())
    } catch (e: RedirectResponseException) {
        NetworkResultState.Failure(exception = e)
    } catch (e: ClientRequestException) {
        NetworkResultState.Failure(exception = e)
    } catch (e: ServerResponseException) {
        NetworkResultState.Failure(exception = e)
    } catch (e: UnresolvedAddressException) {
        NetworkResultState.Failure(exception = e)
    } catch (e: Exception) {
        NetworkResultState.Failure(exception = e)
    }
}