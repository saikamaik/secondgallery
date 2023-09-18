package com.example.secondgallery.presentation.basemvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import moxy.MvpAppCompatFragment

abstract class BaseFragment<V : BaseView, P : BasePresenter<V>, VB : ViewBinding> :
    MvpAppCompatFragment(), BaseView {

    protected abstract val presenter: P
    var _binding: VB? = null
    val binding: VB
        get() = _binding!!

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = initializeBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    abstract fun setUpListeners()

    abstract fun initializeBinding(): VB

    override fun showToast(id: Int) {
        Toast.makeText(requireContext(), id, Toast.LENGTH_SHORT).show()
    }
}