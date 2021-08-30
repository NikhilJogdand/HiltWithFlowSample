package com.codeinger.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codeinger.myapplication.network.repository.PostRepository
import com.codeinger.myapplication.ui.post.PostViewModel
import com.codeinger.myapplication.ui.post.PostViewModelFactory
import com.codeinger.myapplication.utils.Resource
import com.codeinger.myapplication.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val TAG = "nik"
    @Inject
    lateinit var mPostRepository: PostRepository
    @Inject
    lateinit var mSharedPref: SharedPref

    private val mViewModel : PostViewModel by viewModels {
        PostViewModelFactory(mPostRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val mViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        mViewModel.post.observe(this) {
            when(it) {
                is Resource.Failed -> {
                    Log.d(TAG, "Failed ${it.message}")
                }
                is Resource.Loading -> {
                    Log.d(TAG, "Loading...")
                }
                is Resource.Success -> {
                        Log.d(TAG, "Success")
                }
            }
        }
        mViewModel.getPost()
    }




}