package com.bassam.moviesinc.interactors

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.bassam.moviesinc.R
import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.api.MovieApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Bassam Hamada on 7/7/20.
 */
interface AuthInteractor {
    suspend fun isAuth(): Boolean
    suspend fun getApproveUrl(): String
    suspend fun isUrlApproved(url: String): Boolean
    suspend fun completeAuth()
}

class AuthInteractorImpl @Inject constructor(private var context: Context) :
    AuthInteractor {

    companion object {
        private const val APPROVE_URL = "https://www.themoviedb.org/auth/access?request_token="
    }

    private var client: MovieApi = MovieApiClient.movieApi

    private var sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    /**
     * is user authenticated
     */
    override suspend fun isAuth(): Boolean = withContext(Dispatchers.IO) {
        sharedPreferences.getString(context.getString(R.string.pref_session_id_key), null) != null
    }

    /**
     * set user authentication state
     */
    private fun saveSessionId(sessionId: String) {
        sharedPreferences.edit(commit = true) {
            putString(context.getString(R.string.pref_session_id_key), sessionId)
        }
    }

    override suspend fun getApproveUrl(): String = withContext(Dispatchers.IO) {

        // get request token
        val token = client.requestToken()

        // formulate approve URL
        APPROVE_URL + token
    }

    override suspend fun isUrlApproved(url: String): Boolean = withContext(Dispatchers.IO) {
        true
    }

    override suspend fun completeAuth() = withContext(Dispatchers.IO) {

    }
}