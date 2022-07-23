package com.example.cryptoapp

import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.indices
import androidx.core.view.size
import com.example.cryptoapp.databinding.ActivityCoinDetailsBinding
import com.example.cryptoapp.databinding.ActivityMainBinding
import com.example.cryptoapp.domain.GridItemTagModel
import com.example.cryptoapp.domain.TeamMemberModel

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idCoin = intent.getStringExtra("id_coin")
        val fileName = idCoin?.replace("-", "_")
        val fileId = resources.getIdentifier(fileName, "raw", packageName)
        val details = FileUtils.getCryptoCoinDetails(this, fileId)

        val headerString = "${details.rank}. ${details.name} (${details.symbol})"
        binding.header.text = headerString

        val descriptionString = details.description
        binding.description.text = descriptionString

        if(details.isActive) {
            binding.active.let {
                it.text = "active"
                it.setTextColor(Color.GREEN)
            }
        } else {
            binding.active.let {
                it.text = "inactive"
                it.setTextColor(Color.RED)
            }
        }

        val tagList = details.tags.map { it -> GridItemTagModel(it.name) }
        binding.gridTags.adapter = GridAdapter(this, tagList)

        val adapter: ArrayAdapter<TeamMemberModel>
        val teamMembers = details.team
        var listView = binding.lvTeamMembers
        adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, teamMembers)
        listView.adapter = adapter
    }
}
