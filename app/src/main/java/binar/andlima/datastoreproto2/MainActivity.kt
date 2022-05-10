package binar.andlima.datastoreproto2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainviewmodel : ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainviewmodel = ViewModelProvider(this).get(ViewModel::class.java)
        mainviewmodel.data.observe(this,{
            textView.text = it.name
        })
        save_btn.setOnClickListener {
            val nama = name_et.text.toString()
            mainviewmodel.update(nama)

        }
        clear_btn.setOnClickListener {
            mainviewmodel.delet()
            name_et.setText("")
        }



    }
}