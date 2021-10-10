package space.abdilazov.youtubeapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import space.abdilazov.youtubeapi.databinding.RvItemBinding
import space.abdilazov.youtubeapi.model.PlayListItems

class PlayAdapter: RecyclerView.Adapter<PlayAdapter.ViewHolder>() {

    private lateinit var binding: RvItemBinding
    private val PlayList: MutableList<PlayListItems> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(PlayList[position])
    }

    override fun getItemCount(): Int {
        return PlayList.size
    }

    fun addList(list: ArrayList<PlayListItems>?){
        PlayList.addAll(mutableListOf())
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private var binding: RvItemBinding = RvItemBinding.bind(itemView)

        fun onBind(playListItems: PlayListItems) {

            binding.tvTheme.text = playListItems.snippet?.title
            Glide.with(itemView)
                .load(playListItems.snippet?.thumbnails?.default?.url.toString())
                .centerCrop()
                .into(binding.imageView)
        }
    }
}
