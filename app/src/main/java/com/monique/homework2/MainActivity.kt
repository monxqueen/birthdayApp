package com.monique.homework2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import java.time.temporal.ChronoUnit
import java.time.LocalDate
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var nameInput: EditText
    lateinit var giftInput: EditText
    lateinit var birthdayInput: DatePicker
    lateinit var submit: Button
    lateinit var result: TextView
    val birthdaysList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameInput = findViewById(R.id.name)
        giftInput = findViewById(R.id.gift)
        birthdayInput = findViewById(R.id.birthday)
        submit = findViewById(R.id.button)
        result = findViewById(R.id.result)

        submit.setOnClickListener{
            val name = nameInput?.text.toString()
            val gift = giftInput?.text.toString()
            val dtDeNasc = LocalDate.of(birthdayInput.year, birthdayInput.month + 1, birthdayInput.dayOfMonth)
            var daysLeft: Int


            name?.let{
                gift?.let{
                    if(dtDeNasc > LocalDate.now()){
                        result.text = "Data de nascimento inválida, tente novamente."
                    }else{
                        daysLeft = daysToBday(dtDeNasc)
                        showResult(name,gift,daysLeft)
                    }
                }
            }
        }

    }


    fun daysToBday(dtDeNasc: LocalDate): Int{ //Calcula quantos dias faltam pro aniversário
        val dateNow= LocalDate.now()
        var nextBirthday = dtDeNasc.withYear(dateNow.year)
        var difference: Long
        if (nextBirthday.isBefore(dateNow)){
            nextBirthday = nextBirthday.plusYears(1)
        }
        difference = ChronoUnit.DAYS.between(dateNow, nextBirthday)

        return difference.toInt()
    }

    fun showResult(name: String, gift: String, daysLeft: Int){
         if(daysLeft == 1){
             showList("Olá $name, falta apenas $daysLeft dia pro seu aniversário e eu espero muito que você ganhe um(a) $gift :)")
         }
         else if(daysLeft == 0){
            showList("Olá $name, hoje é o seu aniversário e eu espero muito que você ganhe um(a) $gift :)")
         }
         else{
             showList("Olá $name, faltam $daysLeft dias pro seu aniversário e eu espero muito que você ganhe um(a) $gift :)")
         }
    }

    fun showList(msg: String){
        var showBirthdays = ""

        result.visibility = View.VISIBLE

        birthdaysList.add(msg)

        for(birthdayMessage in birthdaysList){
            showBirthdays += "${birthdayMessage} \n\n"
        }

        result.text = showBirthdays
    }

}