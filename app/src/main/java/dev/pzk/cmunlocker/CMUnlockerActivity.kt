package dev.pzk.cmunlocker

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Toast

class CMUnlockerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (intent?.action) {
            Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    try {
                        val cmLink = intent.getStringExtra(Intent.EXTRA_TEXT)

                        //Parse Url
                        val parsedLink = Uri.parse(cmLink)
                        val encodedUrl = parsedLink.getQueryParameter("r")

                        // Convert Base64 to Url
                        val decodedUrl =
                            String(Base64.decode(encodedUrl, Base64.DEFAULT), Charsets.UTF_8)

                        //Copy to Clipboard
                        val clipboardManager =
                            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clipData = ClipData.newPlainText("text", decodedUrl)
                        clipboardManager.setPrimaryClip(clipData)

                        Toast.makeText(
                            applicationContext,
                            "Unlocked, Paste in Browser 😁",
                            Toast.LENGTH_LONG
                        ).show()

                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(decodedUrl)
                        startActivity(intent)

                    } catch (e: Exception) {
                        println("Error : $e")
                        Toast.makeText(
                            applicationContext,
                            "Sorry, Link Unsupported 😢",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }

            }

        }

        finish()
    }
}