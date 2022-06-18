package com.juanguicj.lab1_ejer1.main

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juanguicj.lab1_ejer1.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timerTask

class MainViewModel: ViewModel() {
    val calendar = Calendar.getInstance()

    fun getBirthdate(year: Int, month: Int, day: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)

        val format = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(format)
        birthdate.value = sdf.format(calendar.time).toString()
    }

    fun getCalendarYear(): Int{
        return calendar.get(Calendar.YEAR)
    }

    fun getCalendarMonth(): Int{
        return calendar.get(Calendar.MONTH)
    }

    fun getCalendarDay(): Int{
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun process(
        name: String,
        email: String,
        password: String,
        repPassword: String,
        hobbiesState: Map<Int,Boolean>,
        hobbiesText: Map<Int,String>,
        genderChecked: Boolean,
        genderText: Map<Int,String>,
        birthdateThis: String,
        city: String,
        resourcesThis: Map<Int,String>
    ) {
        var empty = false

        if (name.isEmpty()) {
            isNameEmpty.value = true
            empty = true
        }
        if (email.isEmpty()) {
            isEmailEmpty.value = true
            empty = true
        }
        if (password.isNullOrEmpty()) {
            isPasswordEmpty.value = true
            empty = true
        }
        if (repPassword.isNullOrEmpty()) {
            isRepeatPasswordEmpty.value = true
            empty = true
        }
        if (birthdateThis == resourcesThis[R.string.main_openDatapicker]) {
            isBirthDateEmpty.value = true
            empty = true
        }

        if(empty){
            result.value = ""
        }
        else{
            var hobbies = ""
            if (hobbiesState[1] == true)
                hobbies += hobbiesText[1]
            if (hobbiesState[2] == true)
                hobbies += ", " + hobbiesText[2]
            if (hobbiesState[3] == true)
                hobbies += ", " + hobbiesText[3]
            if (hobbiesState[4] == true)
                hobbies += ", " + hobbiesText[4]
            if (hobbies.isNotBlank()){
                if(hobbies.substring(0, 2) == ", ")
                    hobbies =  hobbies.substring(2, hobbies.lastIndex + 1)
            }

            val gender = if (genderChecked) genderText[1] else genderText[2]
            
            if(repPassword == password){
                result.value = String.format(
                    resourcesThis[R.string.main_result].toString(),
                    name,
                    email,
                    password,
                    gender,
                    hobbies,
                    birthdateThis,
                    city
                )
            }
            else
                result.value = resourcesThis[R.string.main_different_password]
        }
    }

    private val birthdate : MutableLiveData<String> = MutableLiveData()
    val _birthdate: LiveData<String> = birthdate
    private val isNameEmpty : MutableLiveData<Boolean> = MutableLiveData()
    val _isNameEmpty: LiveData<Boolean> = isNameEmpty
    private val isEmailEmpty : MutableLiveData<Boolean> = MutableLiveData()
    val _isEmailEmpty: LiveData<Boolean> = isEmailEmpty
    private val isPasswordEmpty : MutableLiveData<Boolean> = MutableLiveData()
    val _isPasswordEmpty: LiveData<Boolean> = isPasswordEmpty
    private val isRepeatPasswordEmpty : MutableLiveData<Boolean> = MutableLiveData()
    val _isRepeatPasswordEmpty: LiveData<Boolean> = isRepeatPasswordEmpty
    private val isBirthDateEmpty : MutableLiveData<Boolean> = MutableLiveData()
    val _isBirthDateEmpty: LiveData<Boolean> = isBirthDateEmpty
    private val result : MutableLiveData<String> = MutableLiveData()
    val _result: LiveData<String> = result
}

