package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    private lateinit var NamePeople :TextView
    private var nameBib = arrayListOf<String>("Катя","Лера","Соня","Паша")
    private lateinit var  spin : Spinner
    private var SelectedPeople : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        NamePeople = findViewById(R.id.text)
        spin = findViewById(R.id.NameCats)
        var pref = this.getSharedPreferences(APP_PREFERENS, MODE_PRIVATE)
        var name =pref.getString(KEY_VALUE_PREF_NAME,"def")
        NamePeople.text = "Приятно,что вы с нами ${name}.Мяу!"

        var nameCats = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nameBib)
        nameCats.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = nameCats

        spin.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSeleted  : View,
                selectedPosition: Int,
                SelectedId: Long
            ) {
                SelectedPeople = nameBib[selectedPosition]
                var toast =Toast.makeText(applicationContext,"${SelectedPeople}",Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

    }



}


