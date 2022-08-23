//package com.example.cryptoapp.activity
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.View
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.cryptoapp.util.FileUtils
//import com.example.cryptoapp.R
//import com.example.cryptoapp.adapter.RecyclerAdapter
//import com.example.cryptoapp.databinding.ActivityMainBinding
//import com.example.cryptoapp.util.AffirmationModel
//import com.example.cryptoapp.util.ImageUtils
//
//class MainActivity : AppCompatActivity(), RecyclerAdapter.ItemClickListener {
//
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val cryptoList = FileUtils.getCryptoCoins(this, R.raw.input_data)
//
//        val adapter = RecyclerAdapter()
//        adapter.setClickListener(this)
//        adapter.apply {
//            list = cryptoList
//            images = ImageUtils.loadAffirmationModelModels()
//        }
//
//        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.recyclerView.adapter = adapter
//    }
//
//    override fun onClick(view: View?, position: Int) {
//        val intent = Intent(this, CoinDetailActivity::class.java)
//        val cryptoList = FileUtils.getCryptoCoins(this, R.raw.input_data)
//        intent.putExtra("id_coin", cryptoList[position].id)
//        startActivity(intent)
//    }
//}
