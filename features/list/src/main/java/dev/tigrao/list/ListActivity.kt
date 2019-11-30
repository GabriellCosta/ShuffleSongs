package dev.tigrao.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.tigrao.shufflesongs.feature.list.R

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)
    }
}
