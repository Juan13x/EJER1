package com.juanguicj.lab1_ejer1.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juanguicj.lab1_ejer1.R
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {
    private val calendar: Calendar = Calendar.getInstance()
    private lateinit var hobbiesState: Map<Int, Boolean>
    private lateinit var hobbiesText: Map<Int, String>
    private lateinit var genderText: Map<Int, String>
    private lateinit var resourcesThis: Map<Int, String>

    fun getBirthdate(year: Int, month: Int, day: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)

        val format = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(format)
        birthdateMutableLiveData.value = sdf.format(calendar.time).toString()
    }

    fun getCalendarYear(): Int {
        return calendar.get(Calendar.YEAR)
    }

    fun getCalendarMonth(): Int {
        return calendar.get(Calendar.MONTH)
    }

    fun getCalendarDay(): Int {
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun setTextsAndResources(
        _hobbiesState: Map<Int, Boolean>,
        _hobbiesText: Map<Int, String>,
        _genderText: Map<Int, String>,
        _resourcesThis: Map<Int, String>
    ){
        hobbiesState  = _hobbiesState
        hobbiesText = _hobbiesText
        genderText = _genderText
        resourcesThis = _resourcesThis
    }

    fun process(
        name: String,
        email: String,
        password: String,
        repPassword: String,
        genderChecked: Boolean,
        birthdateThis: String,
        city: String
    ) {
        val empty = checkEmptiness(name, email, password, repPassword, birthdateThis)

        if (empty) {
            resultMutableLiveData.value = ""
        } else {
            val hobbies = hobbies()
            val gender = if (genderChecked) genderText[1] else genderText[2]
            if (repPassword == password) {
                resultMutableLiveData.value = String.format(
                    resourcesThis[R.string.main_result].toString(),
                    name,
                    email,
                    password,
                    gender,
                    hobbies,
                    birthdateThis,
                    city
                )
            } else
                resultMutableLiveData.value = resourcesThis[R.string.main_different_password]
        }
    }

    private fun hobbies(): String {
        var hobbies = ""
        if (hobbiesState[1] == true)
            hobbies += hobbiesText[1]
        if (hobbiesState[2] == true)
            hobbies += ", " + hobbiesText[2]
        if (hobbiesState[3] == true)
            hobbies += ", " + hobbiesText[3]
        if (hobbiesState[4] == true)
            hobbies += ", " + hobbiesText[4]
        if (hobbies.isNotBlank()) {
            if (hobbies.substring(0, 2) == ", ")
                hobbies = hobbies.substring(2, hobbies.lastIndex + 1)
        }
        return hobbies
    }

    private fun checkEmptiness(
        name: String,
        email: String,
        password: String,
        repPassword: String,
        birthdateThis: String
    ): Boolean {
        var empty1 = false
        if (name.isEmpty()) {
            isNameEmptyMutableLiveData.value = true
            empty1 = true
        }
        if (email.isEmpty()) {
            isEmailEmptyMutableLiveData.value = true
            empty1 = true
        }
        if (password.isEmpty()) {
            isPasswordEmptyMutableLiveData.value = true
            empty1 = true
        }
        if (repPassword.isEmpty()) {
            isRepeatPasswordEmptyMutableLiveData.value = true
            empty1 = true
        }
        if (birthdateThis == resourcesThis[R.string.main_openDatapicker]) {
            isBirthDateEmptyMutableLiveData.value = true
            empty1 = true
        }
        return empty1
    }

    private val birthdateMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val birthdateLiveData: LiveData<String> = birthdateMutableLiveData
    private val isNameEmptyMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isNameEmptyLiveData: LiveData<Boolean> = isNameEmptyMutableLiveData
    private val isEmailEmptyMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isEmailEmptyLiveData: LiveData<Boolean> = isEmailEmptyMutableLiveData
    private val isPasswordEmptyMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isPasswordEmptyLiveData: LiveData<Boolean> = isPasswordEmptyMutableLiveData
    private val isRepeatPasswordEmptyMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isRepeatPasswordEmptyLiveData: LiveData<Boolean> = isRepeatPasswordEmptyMutableLiveData
    private val isBirthDateEmptyMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isBirthDateEmptyLiveData: LiveData<Boolean> = isBirthDateEmptyMutableLiveData
    private val resultMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val resultLiveData: LiveData<String> = resultMutableLiveData
}

