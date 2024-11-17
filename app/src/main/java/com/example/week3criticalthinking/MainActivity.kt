package com.example.week3criticalthinking


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week3criticalthinking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var myRepository: ViewModelRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        //mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        myRepository = ViewModelRepository()

        val recycleView: RecyclerView = mainBinding.root.findViewById(R.id.recyclerView)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = RecycleViewAdapter(myRepository.imageList, this)
    }
}
