package de.webtogo.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import de.webtogo.library.feedback.FeedbackDialog
import de.webtogo.library.feedback.FeedbackListener
import de.webtogo.library.feedback.core.Rate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.submit_button).setOnClickListener {
            val dialog = FeedbackDialog.Builder(this)
                .setListener(object : FeedbackListener {
                    override fun feedbackCancelled() {
                        Toast.makeText(this@MainActivity, "Feedback cancelled!", Toast.LENGTH_LONG)
                            .show()
                    }

                    override fun feedbackSubmitted(rate: Rate, comment: String?) {
                        Toast.makeText(
                            this@MainActivity,
                            "Feedback submitted! Selected is $rate and comment is $comment",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }

                })
                .setColors(R.color.wtg_blue, R.color.grey)
                .setStyle(R.style.AppTheme_Dialog)
                .build()

            dialog.show()
        }
    }
}
