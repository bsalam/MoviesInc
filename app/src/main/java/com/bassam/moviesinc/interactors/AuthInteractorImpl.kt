package com.bassam.moviesinc.interactors

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.bassam.moviesinc.R
import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.api.data.AccessTokenBody
import com.bassam.moviesinc.api.data.RequestTokenBody
import com.bassam.moviesinc.api.data.SessionBody
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

class AuthInteractorImpl @Inject constructor(
    private var context: Context,
    private val client: MovieApi,
    private val sharedPreferences: SharedPreferences
) :
    AuthInteractor {

    companion object {
        private const val APPROVE_URL = "https://www.themoviedb.org/auth/access?request_token="
        private const val REDIRECT_URL =
            "com.bassam.moviesinc://approve/callback"
    }

    private lateinit var requestToken: String

    /**
     * is user authenticated
     */
    override suspend fun isAuth(): Boolean = withContext(Dispatchers.IO) {
        sharedPreferences.getString(context.getString(R.string.pref_session_id_key), null) != null
    }

    /**
     * save session id
     */
    private fun saveSessionId(sessionId: String) {
        sharedPreferences.edit(commit = true) {
            putString(context.getString(R.string.pref_session_id_key), sessionId)
        }
    }

    /**
     * save account id
     */
    private fun saveAccountId(accountId: String) {
        sharedPreferences.edit(commit = true) {
            putString(context.getString(R.string.pref_account_id_key), accountId)
        }
    }

    /**
     * get approve URL to load it into browser
     */
    override suspend fun getApproveUrl(): String = withContext(Dispatchers.IO) {

        // get request token
        client.requestToken(RequestTokenBody(REDIRECT_URL)).also {
            requestToken = it.requestToken
        }

        // formulate approve URL
        "$APPROVE_URL${requestToken}".also {
            Log.d("Auth", "ApproveUrl: $it")
        }
    }

    /**
     * check whether given url is approved
     */
    override suspend fun isUrlApproved(url: String): Boolean = withContext(Dispatchers.IO) {
        url == REDIRECT_URL
    }

    /**
     * complete authentication process
     */
    override suspend fun completeAuth() = withContext(Dispatchers.IO) {

        // get access token and account id, and save account id
        client.accessToken(AccessTokenBody(requestToken)).also {
            saveAccountId(it.accountId)

            // get session id, and save it
            client.session(SessionBody(it.accessToken)).also { res ->
                saveSessionId(res.sessionId)
            }
        }

        return@withContext
    }
}