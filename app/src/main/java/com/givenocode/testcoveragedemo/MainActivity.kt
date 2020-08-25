package com.givenocode.testcoveragedemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.givenocode.domain.Data
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ZZZZ", Locale.US)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloadButton.setOnClickListener {
            progress.visibility = View.VISIBLE
            downloadButton.isEnabled = false

            CoroutineScope(IO).launch {
                val data = SimpleDependencyInjector.dataInteractor.getData()

                withContext(Main) {
                    progress.visibility = View.GONE
                    downloadButton.isEnabled = true

                    contentTextView.text = data.content
                    timeTextView.text = DATE_FORMAT.format(Date(data.timestamp))
                    offlineTextView.visibility  = if (data.source == Data.Source.ONLINE) View.GONE else View.VISIBLE
                }
            }
        }
    }
}