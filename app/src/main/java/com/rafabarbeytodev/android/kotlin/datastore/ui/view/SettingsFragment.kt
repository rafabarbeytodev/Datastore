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
        setupViewModel()
        offProgressBar()

        mBinding.smFilterWords.setOnClickListener {
            mDataStoreViewModel.filterWords.value = mBinding.smFilterWords.isChecked
        }
        mBinding.smFilterSenders.setOnClickListener {
            mDataStoreViewModel.filterSenders.value = mBinding.smFilterSenders.isChecked
        }
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    private fun setupViewModel() {
        with(mDataStoreViewModel) {
            getDataStore()
            with(mBinding) {
                userPreferences.observe(viewLifecycleOwner) {
                    etMailDestination.text = it.destinationMail.toEditable()
                    etTelephoneDestination.text = it.destinationTelephone.toEditable()
                    etFilterSenders.text = it.sendersFiltered.toEditable()
                    tilFilterSenders.isEnabled = it.filterSenders
                    etFilterWords.text = it.keywords.toEditable()
                    tilFilterWords.isEnabled = it.filterWords
                    smActivation.isChecked = it.activation
                    smFilterWords.isChecked = it.filterWords
                    smFilterSenders.isChecked = it.filterSenders
                }
                getFilterSender().observe(viewLifecycleOwner) {
                    tilFilterSenders.isEnabled = it
                }
                getFilterWord().observe(viewLifecycleOwner) {
                    tilFilterWords.isEnabled = it
                }
            }
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
        with(mDataStoreViewModel){
            destinationMail.value = mBinding.etMailDestination.text.toString()
            destinationTelephone.value =
                mBinding.etTelephoneDestination.text.toString()
            activation.value = mBinding.smActivation.isChecked
            filterWords.value = mBinding.smFilterWords.isChecked
            filterSenders.value = mBinding.smFilterSenders.isChecked
            keywords.value = mBinding.etFilterWords.text.toString()
            sendersFiltered.value = mBinding.etFilterSenders.text.toString()
            saveDataStore()
        }
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