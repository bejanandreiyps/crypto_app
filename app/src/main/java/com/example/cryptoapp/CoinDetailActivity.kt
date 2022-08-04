package com.example.cryptoapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoapp.adapter.GridAdapter
import com.example.cryptoapp.adapter.ListViewAdapter
import com.example.cryptoapp.databinding.ActivityCoinDetailsBinding
import com.example.cryptoapp.domain.GridItemTagModel
import com.example.cryptoapp.domain.LVItemMemberModel

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinDetailsBinding

    @SuppressLint("SetTextI18n")
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

        val tagList = details.tags.map { GridItemTagModel(it.name) }
        binding.gridTags.adapter = GridAdapter(this, tagList)

        val teamMembers = details.team.map { LVItemMemberModel(it.name, it.position)}
        binding.lvTeamMembers.adapter = ListViewAdapter(this, teamMembers)
    }
}
