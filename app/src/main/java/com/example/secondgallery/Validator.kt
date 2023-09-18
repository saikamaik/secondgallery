package com.example.secondgallery

import android.util.Patterns
import android.widget.EditText
import com.example.domain.entity.login.User
import com.google.android.material.textfield.TextInputLayout
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

    fun validateUsername(usernameEditText: EditText): Boolean {
        val username: String = usernameEditText.text.toString()

        return if (username.isEmpty()) {
            setError(usernameEditText, "Поле не может быть пустым")
            false
        } else {
            setError(usernameEditText, null)
            true
        }

    }

    fun validateEmail(emailEditText: EditText): Boolean {
        val email: String = emailEditText.text.toString()

        return if (email.isEmpty()) {
            setError(emailEditText, "Поле не может быть пустым")
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            setError(emailEditText, "E-mail введен некорректно")
            false
        } else {
            setError(emailEditText, null)
            true
        }
    }

    fun validatePassword(passwordEditText: EditText): Boolean {
        val password: String = passwordEditText.text.toString()

        return if (password.isEmpty()) {
            setError(passwordEditText, "Поле не может быть пустым")
            false
        } else if (!passwordPattern.matcher(password).matches()) {
            setError(passwordEditText, "Слабый пароль")
            false
        } else {
            setError(passwordEditText, null)
            true
        }
    }

    fun validateSettings(
        usernameEditText: EditText,
        birthdayEditText: EditText,
        emailEditText: EditText,
        currentUser: User
    ): Boolean {
        return ((usernameEditText.text.toString() != currentUser.username) ||
                birthdayEditText.text.toString().trim() != currentUser.birthday.toString() ||
                emailEditText.text.toString() != currentUser.email)
    }

    fun validateDate(dateEditText: EditText): Boolean {
        val date: String = dateEditText.text.toString()

        return if (date.isEmpty()) {
            setError(dateEditText, "Поле не может быть пустым")
            false
        } else {
            setError(dateEditText, null)
            true
        }
    }

    private fun setError(data: EditText, error: String?) {
        if (data.parent.parent is TextInputLayout) {
            (data.parent.parent as TextInputLayout).error = error
        }
    }
}