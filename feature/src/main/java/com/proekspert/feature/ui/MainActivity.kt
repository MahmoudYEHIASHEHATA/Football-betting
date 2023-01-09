package com.proekspert.feature.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.proekspert.base.BaseActivity
import com.proekspert.feature.R
import com.proekspert.feature.databinding.ActivityMainBinding
import com.proekspert.feature.ui.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    override val bindLayout: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.nav_container) as NavHostFragment }
    val navController by lazy { navHostFragment.navController }

    override fun prepareView(savedInstanceState: Bundle?) {
        chekLastDestination()
    }

    private fun chekLastDestination() {
        if (!viewModel.isCurrentIsLastPresentScreen(
                navController.currentDestination?.id
            )
        ) {
            navController.navigate(viewModel.getLastDestination())
        }
    }

    override fun onStop() {
        super.onStop()
        if (viewModel.getLastDestination() != R.id.bettingDialogFragment)
            viewModel.saveLastDestination(
                navController.currentDestination?.id
            )
    }
}