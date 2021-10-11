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
    private val playList: MutableList<PlayListItems> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(playList[position])
    }

    override fun getItemCount(): Int {
        return playList.size
    }

    fun addList(list: ArrayList<PlayListItems>?) {
        if (list != null) {
            playList.addAll(list)
            notifyDataSetChanged()
        }

    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private var binding: RvItemBinding = RvItemBinding.bind(itemView)

        fun onBind(playList: PlayListItems) {

            binding.tvTheme.text = playList.snippet?.title
            Glide.with(itemView)
                .load(playList.snippet?.thumbnails?.default?.url.toString())
                .centerCrop()
                .into(binding.imageView)
            (playList?.contentDetails?.itemCount.toString()+" Video series")
                .also {
                binding.tvSeries.text = it
            }
        }
    }
}
