package de.hwrberlinviler.xworkspace

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import de.hwrberlinviler.xworkspace.data.XWorkspaceAPI
import de.hwrberlinviler.xworkspace.data.model.StaticUser
import de.hwrberlinviler.xworkspace.data.model.User
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun Login(view: View) {
        val txtUsername = findViewById<EditText>(R.id.txtLoginUsername)
        val txtPassword = findViewById<EditText>(R.id.txtLoginPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin);

        btnLogin.isEnabled = false

        lifecycleScope.launch {
            val loginRespose = XWorkspaceAPI.client.loginUser(
                txtUsername.text.toString(),
                txtPassword.text.toString()
            )
            if (loginRespose.isSuccessful) {
                checkLogin(loginRespose.body()!!)
            }
        }

    }

    fun checkLogin(userList: List<User>) {
        if (userList.count() == 1) {
            StaticUser.User = userList.first();
            startActivity(Intent(this, MainActivity::class.java))
        }
        else {
            AlertDialog.Builder(this)
                .setTitle(R.string.login_failed_title)
                .setMessage(R.string.login_failed_text)
                .show()
        }

        val btnLogin = findViewById<Button>(R.id.btnLogin);

        btnLogin.isEnabled = true
    }
}