//package com.eman.weathetapplication.features.favourite.view
//
//import android.app.Service
//import android.content.Context
//import android.net.ConnectivityManager
//import android.net.NetworkInfo
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.appcompat.app.AlertDialog
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.lifecycleScope
//import androidx.navigation.NavController
//import androidx.navigation.Navigation
//import androidx.navigation.fragment.navArgs
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.eman.weathetapplication.R
//import com.eman.weathetapplication.data.model.WeatherForecast
//import com.eman.weathetapplication.data.room.FavClickInterface
//import com.eman.weathetapplication.databinding.FragmentFavouriteBinding
//import com.eman.weathetapplication.features.favourite.viewmodel.FavoriteViewModelFactory
//import com.eman.weathetapplication.features.favourite.viewmodel.FavouriteViewModel
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.job
//import kotlinx.coroutines.launch
//
//
//class FavouriteFragment : Fragment() , FavClickInterface{
//
//    private  val TAG = "FavoriteFragment"
//    private lateinit var navController: NavController
//    private lateinit var favAdapter: FavAdapter
//    private lateinit var layoutManager: LinearLayoutManager
//    private lateinit var favViewModelFactory:FavoriteViewModelFactory
//    private lateinit var favViewModel:FavouriteViewModel
//    private lateinit var binding: FragmentFavouriteBinding
//    var connectivity : ConnectivityManager? = null
//    val args :FavouriteFragmentArgs by navArgs()
//    var info : NetworkInfo? = null
//    var weatherForecast:WeatherForecast? = null
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_favourite, container, false)
//    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }
//
//    override fun onRemoveBtnClick(address: WeatherForecast) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onFavItemClick(address: WeatherForecast) {
//        TODO("Not yet implemented")
//    }
//
//    fun addBtnClicked() {
//        binding.floatingAddFav.setOnClickListener {
//
//            val action = FavouriteFragmentDirections.actionFavouriteFragmentToMapFragment (false)
//            navController.navigate(action)
//
//        }
//    }
//    suspend fun addNew() {
//
//        weatherForecast = favViewModel.getFavWholeWeather(args.lat.toDouble(),args.loong.toDouble())
//        Log.i(TAG, "addNew:  ${args.lat}+ ${args.loong}")
//        if (weatherForecast !=null )
//            favViewModel.addtoFav(weatherForecast as WeatherForecast)
//    }
//    suspend fun getData() {
//        lifecycleScope.launch() {
//            favViewModel.getAllFav().collectLatest{
//
//                when (it) {
//                    is DBState.onFail -> { } //hide loader show alert
//                    is DBState.onSuccessList -> { favAdapter.setFavWeatherList(it.weatherList) }
//                    else -> { }//Still loading
//                }
//                favAdapter.notifyDataSetChanged()
//            }
//
//            delay(1000)
//        }.job.join()
//    }
//
//
//
//}