package com.example.quizkn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizkn.adapters.ItemAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycle_view_items.layoutManager = LinearLayoutManager(this)
        val itemAdapter = ItemAdapter(this, getItemsList())
        recycle_view_items.adapter= itemAdapter

        btn_profile.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        btn_music.setOnClickListener{
            val intent = Intent(this, MusicQuestionsActivity::class.java)
            startActivity(intent)
        }
        btn_sports.setOnClickListener{
            val intent = Intent(this, SportsQuestionsActivity::class.java)
            startActivity(intent)
        }
        btn_education.setOnClickListener{
            val intent = Intent(this, EducationQuestionsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getItemsList(): ArrayList<String> {
        val list= ArrayList<String>()
        for (i in 1..3){
            list.add("Item $i")
        }
        return list
    }
}