package com.example.cryptoapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.adapter.MovieAdapter
import com.example.cryptoapp.databinding.FragmentSearchBinding
import com.example.cryptoapp.domain.movie.MovieDetailsModel
import com.example.cryptoapp.view_model.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    @SuppressLint("StaticFieldLeak")
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.searchViewModel = viewModel
        viewModel.movieList.observe(viewLifecycleOwner) { list ->
            (binding.rvResults.adapter as MovieAdapter).movieList = list
        }
        createResultsAdapter()
        createSearchBar()
    }

    private fun createResultsAdapter() {
        binding.rvResults.layoutManager = GridLayoutManager(activity, 4)
        binding.rvResults.adapter = MovieAdapter(
            { model -> onMovieCardHold(model, binding.rvResults)},
            { movieId -> onMovieCardClick(movieId) }
        )
    }

    private fun createSearchBar() {
        binding.etSearchField.addTextChangedListener(object : TextWatcher {

            var job: Job = Job()

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                job.cancel()

                job = lifecycleScope.launch(Dispatchers.IO) {
                    delay(350)
                    viewModel.loadSearchResults(if (editText?.isNotEmpty() == true) editText.toString() else "")
                    if (editText?.isNotEmpty() == true) {
                        viewModel.loadSearchResults(editText.toString())
                    } else {
                        viewModel.loadSearchResults("")
                    }
                }
            }
        })
    }

    private fun onMovieCardClick(movieId: Int) {
        findNavController().navigate(SearchFragmentDirections.searchToMovieDetailsAction(movieId))
    }

    private fun onMovieCardHold(model: MovieDetailsModel, view: RecyclerView) {
        viewModel.handleMovieCardHold(model)
        (view.adapter as? MovieAdapter)?.modifyElement(model)
    }
}