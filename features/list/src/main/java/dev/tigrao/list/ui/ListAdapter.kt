package dev.tigrao.list.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.tigrao.list.entity.ListVO
import dev.tigrao.shufflesongs.feature.list.R

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private val list = mutableListOf<ListVO>()

    fun submitList(parameterList: List<ListVO>) {
        list.clear()
        list.addAll(parameterList)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)

        return ListViewHolder(layout)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val trackName = itemView.findViewById<TextView>(R.id.txt_item_track)
        private val artistName = itemView.findViewById<TextView>(R.id.txt_item_artist_name)
        private val artwork = itemView.findViewById<ImageView>(R.id.img_item_artwork)

        fun bind(item: ListVO) {
            trackName.text = item.musicName
            artistName.text = item.artistName

            Glide.with(artwork)
                .load(item.albumArt)
                .apply(RequestOptions.circleCropTransform())
                .into(artwork)
        }
    }
}
