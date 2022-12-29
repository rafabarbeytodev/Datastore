package com.rafabarbeytodev.android.kotlin.datastore.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.findNavController
import com.rafabarbeytodev.android.kotlin.datastore.R
import com.rafabarbeytodev.android.kotlin.datastore.databinding.FragmentSettingsBinding
import com.rafabarbeytodev.android.kotlin.datastore.ui.viewmodel.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var mBinding: FragmentSettingsBinding

    private val mDataStoreViewModel: DataStoreViewModel by hiltNavGraphViewModels(R.id.action_nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation(view)
        offProgressBar()
        setupViewModel()

    }

    private fun setupViewModel() {

        mDataStoreViewModel.getFilterSender().observe(viewLifecycleOwner) {
            mBinding.tilFilterSenders.isEnabled = it
        }
        mDataStoreViewModel.getFilterWord().observe(viewLifecycleOwner) {
            mBinding.tilFilterWords.isEnabled = it
        }
    }

    private fun offProgressBar() {
        mBinding.progressbar.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        mBinding.viewModel = mDataStoreViewModel
        return mBinding.root

    }

    override fun onPause() {
        super.onPause()
        mDataStoreViewModel.saveDataStore()
    }

    private fun setupNavigation(view: View) {
        mBinding.bottomNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_nav_graph -> {
                    view.findNavController().navigate(R.id.action_settings_to_initial)
                }
                R.id.action_SettingsFragment -> {}
            }
            false
        }
    }

}