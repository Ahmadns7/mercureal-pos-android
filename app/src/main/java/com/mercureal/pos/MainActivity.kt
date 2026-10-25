package com.mercureal.pos

import android.annotation.SuppressLint
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  private lateinit var web: WebView
  private val remoteUrl = "https://mercureal-pos.vercel.app"
  private val localUrl = "file:///android_asset/web/index.html"

  @SuppressLint("SetJavaScriptEnabled")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    web = WebView(this)
    setContentView(web)

    web.setBackgroundColor(Color.parseColor("#0C0A09"))
    web.settings.apply {
      javaScriptEnabled = true
      domStorageEnabled = true
      databaseEnabled = true
      cacheMode = WebSettings.LOAD_DEFAULT
      userAgentString = "$userAgentString MercuRealPOS/1.0"
    }
    web.webViewClient = object : WebViewClient() {
      override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url = request?.url ?: return false
        val scheme = url.scheme ?: return false
        if (scheme == "file" || scheme == "data" || scheme == "blob") return false
        return url.host?.endsWith("vercel.app") != true
      }
    }

    if (isOnline()) web.loadUrl(remoteUrl) else web.loadUrl(localUrl)
  }

  private fun isOnline(): Boolean {
    val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    val caps = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
    return caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
  }

  override fun onBackPressed() {
    if (web.canGoBack()) web.goBack() else super.onBackPressed()
  }

  override fun onPause() {
    super.onPause()
    web.onPause()
  }

  override fun onResume() {
    super.onResume()
    web.onResume()
  }
}
