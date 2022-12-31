package com.rafabarbeytodev.android.kotlin.datastore.ui.view

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.findNavController
import com.rafabarbeytodev.android.kotlin.datastore.R
import com.rafabarbeytodev.android.kotlin.datastore.databinding.FragmentInitialBinding
import com.rafabarbeytodev.android.kotlin.datastore.ui.viewmodel.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitialFragment : Fragment() {

    private lateinit var mBinding: FragmentInitialBinding
    private val mDataStoreViewModel: DataStoreViewModel by hiltNavGraphViewModels(R.id.action_nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation(view)
        setupViewModel()

    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    private fun setupViewModel() {
        mDataStoreViewModel.getDataStore()
        mDataStoreViewModel.userPreferences.observe(viewLifecycleOwner){
            mBinding.etFilterSenders.text = it.destinationMail.toEditable()
        }
        mDataStoreViewModel.destinationMail.observe(viewLifecycleOwner) {
            mBinding.etFilterSenders.text = it.toEditable()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentInitialBinding.inflate(inflater, container, false)
        mBinding.viewModel = mDataStoreViewModel
        return mBinding.root
    }

    private fun setupNavigation(view: View) {
        mBinding.bottomNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_SettingsFragment -> {
                    view.findNavController().navigate(R.id.action_initial_to_settings)
                }
                R.id.action_nav_graph -> {}
            }
            false
        }
    }
}