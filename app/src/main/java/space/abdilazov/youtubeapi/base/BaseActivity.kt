package space.abdilazov.youtubeapi.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {

    protected lateinit var viewBinding: VB

    protected abstract fun inflateViewBinding(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = inflateViewBinding(LayoutInflater.from(this))
        setContentView(viewBinding.root)

        setupUI()
        setupLiveData()
        checkInternet()
    }

    abstract fun setupUI() // Инициализация UI

    abstract fun setupLiveData() // Инициализация Live data

    abstract fun checkInternet() // внутри этого метода проверяем интернет соединение

}