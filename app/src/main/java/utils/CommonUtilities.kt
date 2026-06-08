package utils

import android.util.Patterns

object CommonUtilities {

    fun CharSequence?.isValidEmail(): Boolean {
        return !this.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    fun String.isValidPassword(): Boolean {
        val passwordRegex =
            """^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$""".toRegex()
        return passwordRegex.matches(this)
    }
}