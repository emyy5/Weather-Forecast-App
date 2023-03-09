package com.eman.weathetapplication.features.splach


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.eman.weathetapplication.R
import com.eman.weathetapplication.data.model.Settings
import com.eman.weathetapplication.data.model.WeatherForecast
import com.eman.weathetapplication.data.network.RemoteSource
import com.eman.weathetapplication.data.repository.Repository
import com.eman.weathetapplication.data.repository.RepositoryInterface
import com.eman.weathetapplication.data.room.LocalSource
import com.eman.weathetapplication.databinding.FragmentSplachBinding
import com.eman.weathetapplication.utils.*


class SplachFragment : Fragment() {

    private lateinit var binding: FragmentSplachBinding
    private lateinit var navController: NavController
    private var currentWeather: WeatherForecast? = null
    private var settings: Settings? = null
    private lateinit var repo: RepositoryInterface


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repo = Repository.getInstance(
            RemoteSource.getInstance(),
            LocalSource.getInstance(requireActivity()),
            requireContext(),
            requireContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE))
        currentWeather = repo.getWeatherSharedPreferences()
        settings = repo.getSettingsSharedPreferences()
        if(settings == null){
            this.settings = Settings(ENGLISH, STANDARD, NONE, ENABLED)
            repo.addSettingsToSharedPreferences(settings as Settings)
        }
        else{
            if(settings?.language as Boolean) {
                LocaleHelper.setLocale(requireContext(), "en")
            }
            else{
                LocaleHelper.setLocale(requireContext(), "ar")
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSplachBinding.inflate(layoutInflater)
        navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment)

        Handler().postDelayed({
            if(currentWeather == null){
                navController.navigate(R.id.action_splachFragment_to_initialFragment)
            }
            else{

                val action = SplachFragmentDirections.actionSplachFragmentToHomeFragment(
                    currentWeather?.lat?.toFloat() as Float,
                    currentWeather?.lon?.toFloat() as Float,
                    units[settings?.unit as Int],
                    false)
                navController.navigate(action)
            }
        }, 5000)

        return binding.root

    }

}
