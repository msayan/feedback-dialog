package de.webtogo.library.feedback

import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import de.webtogo.library.feedback.core.Rate
import de.webtogo.library.feedback.pojo.SmileModel

class FeedbackDialog private constructor(val builder: Builder) {

    internal fun create(): AlertDialog {
        val view =
            LayoutInflater.from(builder.activity).inflate(R.layout.layout_feedback_dialog, null)
        val dialog =
            AlertDialog.Builder(builder.activity, builder.style ?: -1)
                .setView(view).create()

        dialog.setOnCancelListener {
            builder.listener?.feedbackCancelled()
        }

        val smileList = listOf(
            SmileModel(R.string.feedback_rate_bad, R.drawable.ic_smile_problem, Rate.BAD),
            SmileModel(R.string.feedback_rate_problem, R.drawable.ic_smile_neutral, Rate.NEUTRAL),
            SmileModel(R.string.feedback_rate_ok, R.drawable.ic_smile_ok, Rate.GOOD),
            SmileModel(R.string.feedback_rate_love, R.drawable.ic_smile_perfect, Rate.VERY_GOOD)
        )

        val submit = view.findViewById<Button>(R.id.submit)
        val comment = view.findViewById<EditText>(R.id.comment)
        val header = view.findViewById<TextView>(R.id.header)
        val recycler = view.findViewById<RecyclerView>(R.id.smile_recycler)
        recycler.adapter =
            SmileAdapter(builder.selectedColor, builder.unSelectedColor) { position ->
                val model = smileList[position]

                header.setText(model.header)
                if (header.visibility == View.GONE) {
                    header.visibility = View.VISIBLE
                    comment.visibility = View.VISIBLE
                    submit.visibility = View.VISIBLE
                }

                val newList = mutableListOf<SmileModel>().apply {
                    smileList.map { it.isSelected = false }
                    smileList.forEach {
                        add(it.copy())
                    }
                    get(position).isSelected = true
                }

                (recycler.adapter as SmileAdapter).submitList(newList)
            }


        (recycler.adapter as SmileAdapter).submitList(smileList)
        header.setTextColor(
            ContextCompat.getColor(
                builder.activity,
                builder.selectedColor ?: R.color.wtg_blue
            )
        )

        submit.setOnClickListener {
            (recycler.adapter as SmileAdapter).getSelected()?.let { item ->
                builder.listener?.feedbackSubmitted(item.rate, comment.text.toString())
                dialog.dismiss()
            }
        }

        return dialog
    }

    class Builder(val activity: AppCompatActivity) {

        internal var listener: FeedbackListener? = null
        internal var style: Int? = null
        internal var selectedColor: Int? = null
        internal var unSelectedColor: Int? = null

        fun setColors(@ColorRes selectedColor: Int? = null, @ColorRes unSelectedColor: Int? = null): Builder {
            this.selectedColor = selectedColor
            this.unSelectedColor = unSelectedColor
            return this
        }

        fun setListener(listener: FeedbackListener?): Builder {
            this.listener = listener
            return this
        }

        fun setStyle(@StyleRes theme: Int): Builder {
            this.style = theme
            return this
        }

        fun build(): AlertDialog {
            return FeedbackDialog(this).create()
        }
    }

}