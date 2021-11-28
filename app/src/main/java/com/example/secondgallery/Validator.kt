package com.example.secondgallery

import android.util.Patterns
import android.widget.EditText
import java.util.regex.Pattern

class Validator {

    private val passwordPattern: Pattern = Pattern.compile(
        "^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                ".{8,}" +
                "$"
    )

    fun validateEmail(emailEditText: EditText): Boolean {
        val email: String = emailEditText.text.toString()

        return if (email.isEmpty()) {
            emailEditText.error = "Поле не может быть пустым"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.error = "E-mail введен некорректно"
            false
        } else {
            emailEditText.error = null
            true
        }
    }

    fun validatePassword(passwordEditText: EditText): Boolean {
        val password: String = passwordEditText.text.toString()

        return if (password.isEmpty()) {
            passwordEditText.error = "Поле не может быть пустым"
            false
        } else if (!passwordPattern.matcher(password).matches()) {
            passwordEditText.error = "Слабый пароль"
            false
        } else {
            passwordEditText.error = null
            true
        }
    }

}