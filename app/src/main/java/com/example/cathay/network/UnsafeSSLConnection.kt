package com.example.cathay.network

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class UnsafeSSLConnection {

    companion object {
        fun setSslSocketFactory(okHttpClient: OkHttpClient.Builder) {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })
            try {
                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("TLS")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory

                okHttpClient.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                okHttpClient.hostnameVerifier { _, _ -> true }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}