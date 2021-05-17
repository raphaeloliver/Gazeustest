package com.example.gazeusapp

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gazeusapp.viewmodel.MainActivityViewModel
import com.example.gazeusapp.adapter.RepoNameAdapter
import com.example.gazeuslibrary.models.UserRepos
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module


class MainActivity : AppCompatActivity(), RepoNameAdapter.OnRepoClickListener {

    private lateinit var search: SearchView
    private val viewModel by inject<MainActivityViewModel>()
    private var adapter : RepoNameAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {
            androidContext(applicationContext)
            modules(module {
                factory {  }
            })

            val listModules = listOf(AppModules.loadDomainModule())
            modules(listModules)
        }
        rvReposUser.layoutManager = LinearLayoutManager(this)
        setObserver()


    }

    override fun onBackPressed() {
        if (!search.isIconified) {
            search.onActionViewCollapsed()
        } else {
            super.onBackPressed();
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.layout.search_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        search = MenuItemCompat.getActionView(searchItem) as SearchView
        search.setOnCloseListener { true }

        val searchPlate =
            search.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        searchPlate.hint = "Pesquisa"

        val searchPlateView: View =
            search.findViewById(androidx.appcompat.R.id.search_plate)
        searchPlateView.setBackgroundColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        )

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if(query != null && query.length >= 3) {

                    viewModel.getRepoNameUser(query)
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })

        val searchManager =
            getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        return super.onCreateOptionsMenu(menu)

    }

    private fun setObserver() {

        viewModel.homeLiveData.observe(this , Observer {

            when(it) {

                is MainActivityViewModel.Home.Success -> {

                    if(it.items != null && it.items.count() > 0) {
                        adapter = RepoNameAdapter(it.items.toMutableList(), this)
                        rvReposUser.adapter = adapter
                    } else {
                        alertNotItemRepoName()
                    }

                }
                else -> {
                    alertNotItemRepoName()
                }
            }
        })
    }

    private fun alertNotItemRepoName() {
        Toast.makeText(this@MainActivity, getString(R.string.text_not_repo_name_user), Toast.LENGTH_LONG).show()

    }

    override fun onClickItemRepo(repos: UserRepos) {
        Log.i("Check item" , repos.id.toString())
    }
}