package space.abdilazov.youtubeapi.ui.playlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import space.abdilazov.youtubeapi.CheckInternet
import space.abdilazov.youtubeapi.MainViewModel
import space.abdilazov.youtubeapi.base.BaseActivity
import space.abdilazov.youtubeapi.databinding.ActivityMainBinding
import space.abdilazov.youtubeapi.ui.adapter.PlayAdapter

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var viewModel: MainViewModel? = null
    private lateinit var playAdapter: PlayAdapter

    override fun setupUI() {

        playAdapter = PlayAdapter()


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun setupLiveData() {
        viewModel?.getPlayList()?.observe(this) { response ->

            if (response != null) {
                Log.d("ololo", response.items?.size.toString())
                playAdapter.addList(response.items)
            }
        }

        viewBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = playAdapter

        }
    }

    override fun checkInternet() {
        val checkInternet = CheckInternet(this)
        checkInternet.observe(this,{isConnected->

            if (isConnected){

                viewBinding.disconnect.visibility = View.GONE
                viewBinding.recyclerView.visibility = View.VISIBLE

            } else {

                viewBinding.disconnect.visibility = View.VISIBLE
                viewBinding.recyclerView.visibility = View.GONE
            }
        })
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}