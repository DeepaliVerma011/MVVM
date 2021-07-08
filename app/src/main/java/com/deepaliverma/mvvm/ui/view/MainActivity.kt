package com.deepaliverma.mvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.deepaliverma.mvvm.R
import com.deepaliverma.mvvm.data.models.Users
import com.deepaliverma.mvvm.ui.adapter.UsersAdapter
import com.deepaliverma.mvvm.ui.viewmodel.GithubViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val vm by lazy {
        ViewModelProvider(this).get(GithubViewModel::class.java)
    }

    val list = arrayListOf<Users>()
    val originalList = arrayListOf<Users>()
    var adapter = UsersAdapter(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    findUsers(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true

            }

        })

        //vm is viewModel Object
        vm.fetchUsers()
        vm.users.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                list.addAll(it)
                originalList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })

        searchView.setOnCloseListener {
            list.clear()
            //list.addAll(it)
            // originalList.addAll(it)
            list.addAll(originalList)
            return@setOnCloseListener true
        }

    }

    private fun findUsers(query: String) {
        vm.SearchUsers(query).observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })

    }
}