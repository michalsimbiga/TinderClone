package com.application.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.application.R
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : BaseMvRxFragment() {

    @Inject
    lateinit var viewModelFactory: HomeViewModel.Factory

    private val viewModel: HomeViewModel by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.home_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        runCICButton.setOnClickListener { viewModel.fetchJokes() }
    }

    override fun invalidate() = withState(viewModel) { state ->
        textview.text = state.jokes.invoke()?.joinToString(separator = "\n") { it.text }
    }
}

