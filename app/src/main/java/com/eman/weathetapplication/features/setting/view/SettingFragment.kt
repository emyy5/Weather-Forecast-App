package com.eman.weathetapplication.features.setting.view

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.eman.weathetapplication.MainActivity
import com.eman.weathetapplication.R
import com.eman.weathetapplication.data.model.Settings
import com.eman.weathetapplication.data.network.RemoteSource
import com.eman.weathetapplication.data.repository.Repository
import com.eman.weathetapplication.data.room.LocalSource
import com.eman.weathetapplication.databinding.FragmentSettingBinding
import com.eman.weathetapplication.features.setting.viewmodel.SettingViewModel
import com.eman.weathetapplication.features.setting.viewmodel.SettingViewModelFactory
import com.eman.weathetapplication.utils.LocaleHelper
import com.eman.weathetapplication.utils.SHARED_PREFERENCES


class SettingFragment : Fragment() {

    lateinit var settingsViewModel: SettingViewModel
    lateinit var settingsViewModelFactory: SettingViewModelFactory
    lateinit var binding: FragmentSettingBinding
    private lateinit var settings: Settings
    lateinit var navController: NavController
    var connectivity: ConnectivityManager? = null
    var info: NetworkInfo? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentSettingBinding.bind(view)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        settingsViewModelFactory = SettingViewModelFactory(
            Repository.getInstance(
                RemoteSource.getInstance(),
                LocalSource.getInstance(requireActivity()),
                requireContext(),
                requireContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
            )
        )
        settingsViewModel =
            ViewModelProvider(this, settingsViewModelFactory)[SettingViewModel::class.java]

        settings = Settings()
        setupUI()

        binding.notiEnable.setOnClickListener {
            settings.notification = true
            settingsViewModel.setSettingsSharedPrefs(settings)
        }

        binding.notiDisable.setOnClickListener {
            settings.notification = false
            settingsViewModel.setSettingsSharedPrefs(settings)

            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setMessage(getString(R.string.warning))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.ok)) { dialog, id ->
                    dialog.cancel()
                }
            val alert = dialogBuilder.create()
            alert.show()
        }

        binding.GPS.setOnClickListener {
            settings.location = 1
            settingsViewModel.setSettingsSharedPrefs(settings)
            connectivity =
                context?.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity != null) {
                info = connectivity!!.activeNetworkInfo
                Log.i("TAG", "connectivity != null")
                if (info != null) {
                    if (info!!.state == NetworkInfo.State.CONNECTED) {

                    } else {
                        val dialogBuilder = AlertDialog.Builder(requireContext())
                        dialogBuilder.setMessage(getString(R.string.networkWarning))
                            .setCancelable(false)
                            .setPositiveButton(getString(R.string.ok)) { dialog, id ->
                                dialog.cancel()
                            }
                        val alert = dialogBuilder.create()
                        alert.show()
                    }
                }
            }
        }

        binding.map.setOnClickListener {
            settings.location = 2
//            settingsViewModel.setSettingsSharedPrefs(settings)
            connectivity =
                context?.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity != null) {
                info = connectivity!!.activeNetworkInfo
                Log.i("TAG", "connectivity != null")
                if (info != null) {
                    if (info!!.state == NetworkInfo.State.CONNECTED) {


                        val action =
                            SettingFragmentDirections.actionSettingFragment2ToMapFragment(true)
                        navController.navigate(action)
                    } else {
                        val dialogBuilder = AlertDialog.Builder(requireContext())
                        dialogBuilder.setMessage(getString(R.string.networkWarning))
                            .setCancelable(false)
                            .setPositiveButton(getString(R.string.ok)) { dialog, id ->
                                dialog.cancel()
                            }
                        val alert = dialogBuilder.create()
                        alert.show()
                    }
                }
            }
        }

        binding.standardUnit.setOnClickListener {
            settings.unit = 0
            settingsViewModel.setSettingsSharedPrefs(settings)
            // startActivity(Intent(requireContext(), MainActivity::class.java))
            // requireActivity().finish()
        }

        binding.metricUnit.setOnClickListener {
            settings.unit = 1
            settingsViewModel.setSettingsSharedPrefs(settings)
            // startActivity(Intent(requireContext(), MainActivity::class.java))
            // requireActivity().finish()
        }

        binding.imperialUnit.setOnClickListener {
            settings.unit = 2
            settingsViewModel.setSettingsSharedPrefs(settings)
            // startActivity(Intent(requireContext(), MainActivity::class.java))
            //requireActivity().finish()
        }

        binding.arabicLang.setOnClickListener {
            settings.language = false
            settingsViewModel.setSettingsSharedPrefs(settings)
            LocaleHelper.setLocale(requireContext(), "ar")
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }

        binding.englishLang.setOnClickListener {
            settings.language = true
            settingsViewModel.setSettingsSharedPrefs(settings)
            LocaleHelper.setLocale(requireContext(), "en")
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }

    }

    private fun setupUI() {
        Log.d("anything", "setupUI: ${settings.language}")
        if (settings.language!!) {
            binding.englishLang.isChecked = true
        } else {
            binding.arabicLang.isChecked = true
        }

        if (settings.unit == 0) {
            binding.standardUnit.isChecked = true
        } else if (settings.unit == 1) {
            binding.metricUnit.isChecked = true
        } else {
            binding.metricUnit.isChecked = true
        }

        if (settings.location == 1) {
            binding.GPS.isChecked = true
        } else if (settings.location == 2) {
            binding.map.isChecked = true
        }

        if (settings.notification!!) {
            binding.notiEnable.isChecked = true
        } else {
            binding.notiDisable.isChecked = true
        }
    }

}


