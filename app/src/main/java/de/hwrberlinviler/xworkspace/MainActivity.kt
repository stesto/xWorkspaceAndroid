package de.hwrberlinviler.xworkspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import de.hwrberlinviler.xworkspace.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onBtnLoginClick(view: View) {
        var loginAct = Intent(this, LoginActivity::class.java);
        startActivity(loginAct);
    }
}