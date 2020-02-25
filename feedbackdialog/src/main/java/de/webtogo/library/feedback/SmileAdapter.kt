package de.webtogo.library.feedback

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.webtogo.library.feedback.pojo.SmileModel


class SmileAdapter(
    val selectedColor: Int? = null,
    val unSelectedColor: Int? = null,
    val callback: (Int) -> Unit
) :
    ListAdapter<SmileModel, SmileAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<SmileModel>() {
        override fun areItemsTheSame(oldItem: SmileModel, newItem: SmileModel): Boolean {
            return oldItem.header == newItem.header
        }

        override fun areContentsTheSame(oldItem: SmileModel, newItem: SmileModel): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_smile, parent, false)

        return ViewHolder(view, callback)
    }

    fun getSelected(): SmileModel? {
        return currentList.firstOrNull { it.isSelected }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = getItem(position)

        holder.image.setImageResource(model.image)
        holder.image.imageTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                holder.image.context,
                if (model.isSelected) selectedColor ?: R.color.wtg_blue else unSelectedColor
                    ?: R.color.grey
            )
        )

    }


    class ViewHolder(view: View, callback: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)

        init {
            image.setOnClickListener {
                callback.invoke(adapterPosition)
            }
        }

    }
}