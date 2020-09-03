package com.example.cathay

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

inline fun <reified T : Activity> T.openBrowser(url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

inline fun <reified T : Fragment> T.withBundleValue(argsBuilder: Bundle.() -> Unit):
        T = this.apply { arguments = Bundle().apply(argsBuilder) }

inline fun <reified T : Any> Fragment.getBundleValue(key: String, default: T) = lazy {
    arguments?.get(key) as? T ?: default
}

fun ImageView.load(url: String) = Picasso.get().load(url).into(this)

fun AppCompatActivity.changeFragment(fragment: Fragment) =
    changeFragment(R.id.content_view, fragment)

fun AppCompatActivity.changeFragment(container: Int = R.id.content_view, fragment: Fragment) {
    supportFragmentManager.commit(true) {
        replace(container, fragment)
    }
}

fun AppCompatActivity.addFragment(fragment: Fragment) {
    addFragment(R.id.content_view, fragment)
}

fun AppCompatActivity.addFragment(container: Int = R.id.content_view, fragment: Fragment) {
    supportFragmentManager.commit(true) {
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        addToBackStack(null)
        add(container, fragment)
    }
}

inline fun <reified T> Gson.fromJson(json: String): T =
    fromJson(json, object : TypeToken<T>() {}.type)

private val decimalFormatNumber6 = DecimalFormat("#.######")

fun BigDecimal.formatPoint6(): String {
    decimalFormatNumber6.roundingMode = RoundingMode.DOWN
    return decimalFormatNumber6.format(this).replace("^-(?=0(\\.0*)?$)".toRegex(), "")
}

fun BigDecimal.div(decimal: BigDecimal): BigDecimal = this.divide(decimal, 8, RoundingMode.DOWN)

fun String.toDecimal() = BigDecimal(this)

fun String.div(decimalStr: String) = toDecimal().div(decimalStr.toDecimal())