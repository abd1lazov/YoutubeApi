package space.abdilazov.youtubeapi.ui.playlist

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import space.abdilazov.youtubeapi.CheckInternet
import space.abdilazov.youtubeapi.MainViewModel
import space.abdilazov.youtubeapi.base.BaseActivity
import space.abdilazov.youtubeapi.databinding.ActivityMainBinding
import space.abdilazov.youtubeapi.ui.adapter.PlayAdapter

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var viewModel: MainViewModel
    private lateinit var playAdapter: PlayAdapter

    override fun setUI() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun setupLiveData() {
        viewModel.getPlayList().observe(this, { response ->

            if (response != null) {
                playAdapter.addList(response.items)
            }

            playAdapter = PlayAdapter()
        })

        viewBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = playAdapter

        }
    }
    override fun checkInternet() {
        val checkInternet = CheckInternet(this)
        checkInternet.observe(this,{
                isConnected->
            if (isConnected){

                viewBinding.layoutDisconnect.visibility = View.GONE
                viewBinding.recyclerView.visibility = View.VISIBLE

            }else {

                viewBinding.layoutDisconnect.visibility = View.VISIBLE
                viewBinding.recyclerView.visibility = View.GONE
            }
        })
    }


    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initClickListener() {
        TODO("Not yet implemented")
    }
}