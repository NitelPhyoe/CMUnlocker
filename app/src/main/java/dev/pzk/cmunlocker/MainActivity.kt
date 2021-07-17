package dev.pzk.cmunlocker


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val window: Window = this@MainActivity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.ic_launcher_background)

        val customColor = ColorDrawable(Color.parseColor("#109d94"))
        supportActionBar?.setBackgroundDrawable(customColor)
        setContentView(R.layout.activity_main)

        val profile = findViewById<TextView>(R.id.txtLink)
        profile.movementMethod =LinkMovementMethod.getInstance()

    }
}

