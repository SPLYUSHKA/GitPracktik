package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton


const val APP_PREFERENS = "APP_PREFERENS"
const val KEY_VALUE_PREF_NAME = "NAME"
const val KEY_VALUE_PREF_Pass ="PASS"
class MainActivity : AppCompatActivity() {


    private lateinit var  button: AppCompatButton
    private lateinit var  name : EditText
    private lateinit var  password : EditText
    private lateinit var preferens : SharedPreferences
    var WentOne :Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.buttonWent)
        name = findViewById(R.id.edit_name)
        password = findViewById(R.id.editText)
        preferens =getSharedPreferences(APP_PREFERENS,Context.MODE_PRIVATE)
        WentOne =preferens.getBoolean("WentOne",false)
        if(WentOne == true){

            name.setText(preferens.getString(KEY_VALUE_PREF_NAME,"null"))
        }
        button.setOnClickListener{

            if(WentOne == true &&  password.text.toString().isNotEmpty())
            {
                var pas = preferens.getString(KEY_VALUE_PREF_Pass,"def")
                if(password.text.toString() ==pas){
                var inten = Intent(this,MainActivity2::class.java);
                startActivity(inten)}
                else {
                    var toast = Toast.makeText(this,"Пароль неверный",Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.BOTTOM,0,30)
                    toast.show()
                }
            }
            else  if(name.text.toString().isNotEmpty() && password.text.toString().isNotEmpty() && WentOne == false){
                preferens.edit()
                    .putString(KEY_VALUE_PREF_NAME,name.text.toString())
                    .putString(KEY_VALUE_PREF_Pass,password.text.toString())
                    .putBoolean("WentOne",true)
                    .apply()

                var inten = Intent(this,MainActivity2::class.java);
                startActivity(inten)

            }
            else
            {
                var toast = Toast.makeText(this,"Поля ввода данных не должны быть пустыми",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM,0,30)
                toast.show()
            }


        }
    }
}