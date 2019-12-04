package dev.tigrao.list.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dev.tigrao.commons.statemachine.ErrorDataDTO
import dev.tigrao.commons.statemachine.ErrorEvent
import dev.tigrao.commons.statemachine.FinishedEvent
import dev.tigrao.commons.statemachine.StartedEvent
import dev.tigrao.commons.statemachine.StateEvent
import dev.tigrao.commons.statemachine.SuccessEvent
import dev.tigrao.list.entity.ListVO
import dev.tigrao.shufflesongs.feature.list.R
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private val listViewModel by viewModel<ListViewModel>()

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.rv_list) }
    private val loading by lazy { findViewById<View>(R.id.progress_list) }

    private val listAdapter = ListAdapter()
    private val layoutManagerFactory by inject<LayoutManagerFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)
        prepareObservers()
        prepareList()

        listViewModel.fetchSongs()
    }

    private fun prepareList() {
        recyclerView.apply {
            val linearLayoutManager = layoutManagerFactory.getInstance(this@ListActivity)
            layoutManager = linearLayoutManager
            adapter = listAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@ListActivity,
                    linearLayoutManager.orientation
                )
            )
        }
    }

    private fun prepareObservers() {
        listViewModel
            .liveData
            .observe(this, Observer(::observeStates))
    }

    private fun observeStates(state: StateEvent<List<ListVO>>) {
        when (state) {
            StartedEvent -> startLoad()
            is ErrorEvent -> onError(state.errorDataDTO)
            is SuccessEvent -> onSuccess(state.result)
            FinishedEvent -> finishLoad()
        }
    }

    private fun onSuccess(list: List<ListVO>) {
        Log.d("ListActivity", "Teve sucesso")

        listAdapter.submitList(list)
    }

    private fun startLoad() {
        Log.d("ListActivity", "Iniciou")

        loading.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun onError(errorDataDTO: ErrorDataDTO) {
        Snackbar.make(
            window.decorView,
            errorDataDTO.throwable.message.orEmpty(),
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.list_error_try_again) {
                listViewModel.fetchSongs()
            }
            .show()
    }

    private fun finishLoad() {
        loading.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_list_shuffle)
            listViewModel.shuffle()

        return super.onOptionsItemSelected(item)
    }
}
