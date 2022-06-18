package com.juanguicj.lab1_ejer1.main

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.juanguicj.lab1_ejer1.R
import com.juanguicj.lab1_ejer1.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    /*ActivityMainBinding = is base on the name of the .xml of the activity
    in this case, activity_main.xml, so ActivityMainBinding
    */
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val timer = Timer()

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        with(mainBinding) {

            mainViewModel._isNameEmpty.observe(this@MainActivity){
                    isNameEmpty ->
                if(isNameEmpty){
                    nameWarning()
                }
            }

            mainViewModel._isEmailEmpty.observe(this@MainActivity){
                    isEmailEmpty ->
                if(isEmailEmpty){
                    emailWarning()
                }
            }

            mainViewModel._isPasswordEmpty.observe(this@MainActivity){
                    isPasswordEmpty->
                if(isPasswordEmpty){
                    passwordWarning()
                }
            }

            mainViewModel._isRepeatPasswordEmpty.observe(this@MainActivity){
                    isRepeatPasswordEmpty->
                if(isRepeatPasswordEmpty){
                    repeatePasswordWarning()
                }
            }

            mainViewModel._isBirthDateEmpty.observe(this@MainActivity){
                    isBirthDateEmpty->
                if(isBirthDateEmpty){
                    birthdayWarning()
                }
            }

            mainViewModel._result.observe(this@MainActivity){
                    result->
                resultTextView.text = result
            }

            mainViewModel._birthdate.observe(this@MainActivity){
                    birthdate ->
                mainBinding.openDatePickerButton.text = birthdate
            }

            val setDate = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                mainViewModel.getBirthdate(year, month, day)
            }

            genderRadioGroup.check(gender1RadioButton.id)

            openDatePickerButton.setOnClickListener {
                DatePickerDialog(
                    this@MainActivity,
                    setDate,
                    mainViewModel.getCalendarYear(),
                    mainViewModel.getCalendarMonth(),
                    mainViewModel.getCalendarDay(),
                ).show()
            }

            saveButton.setOnClickListener {
                val password = passwordTextInputEditText.text.toString()
                val repPassword = repeatePasswordTextInputEditText.text.toString()
                val name = nameEditText.text.toString()
                val email = emailEditText.text.toString()
                val hobbie1 = hobbie1CheckBox.text.toString()
                val hobbie2 = hobbie2CheckBox.text.toString()
                val hobbie3 = hobbie3CheckBox.text.toString()
                val hobbie4 = hobbie4CheckBox.text.toString()
                val genderChecked = gender1RadioButton.isChecked
                val genderText = mapOf(
                    1 to gender1RadioButton.text.toString(),
                    2 to gender2RadioButton.text.toString())
                val birthdate = openDatePickerButton.text.toString()
                val city = citySpinner.selectedItem.toString()
                val passResources =  mapOf(
                    R.string.main_openDatapicker to
                            getString(R.string.main_openDatapicker),
                    R.string.main_result to
                            getString(R.string.main_result),
                    R.string.main_different_password to
                            getString(R.string.main_different_password)
                )
                val hobbiesState = mapOf(
                    1 to hobbie1CheckBox.isChecked,
                    2 to hobbie2CheckBox.isChecked,
                    3 to hobbie3CheckBox.isChecked,
                    4 to hobbie4CheckBox.isChecked)
                val hobbies = mapOf(1 to hobbie1,2 to  hobbie2, 3 to hobbie3, 4 to  hobbie4)

                mainViewModel.process(
                    name,
                    email,
                    password,
                    repPassword,
                    hobbiesState,
                    hobbies,
                    genderChecked,
                    genderText,
                    birthdate,
                    city,
                    passResources
                )
            }
        }
    }

    private fun nameWarning() {
        with(mainBinding){
            nameEditText.setBackgroundColor(resources.getColor(R.color.main_input_empty_Bground))
            nameEditText.setHintTextColor(resources.getColor(R.color.main_input_empty_text))
            timer.schedule(
                timerTask {
                    nameEditText.setBackgroundColor(resources.getColor(R.color.main_input_Bground))
                    nameEditText.setHintTextColor(resources.getColor(R.color.main_input_hint_text))
                }, 1000
            )
        }
    }

    private fun emailWarning() {
        with(mainBinding) {
            emailEditText.setBackgroundColor(resources.getColor(R.color.main_input_empty_Bground))
            emailEditText.setHintTextColor(resources.getColor(R.color.main_input_empty_text))
            timer.schedule(
                timerTask {
                    emailEditText.setBackgroundColor(resources.getColor(R.color.main_input_Bground))
                    emailEditText.setHintTextColor(resources.getColor(R.color.main_input_hint_text))
                }, 1000
            )
        }
    }

    private fun passwordWarning() {
        with(mainBinding) {
            passwordTextInputEditText.setBackgroundColor(resources.getColor(R.color.main_input_empty_Bground))
            timer.schedule(
                timerTask {
                    passwordTextInputEditText.setBackgroundColor(resources.getColor(R.color.main_input_Bground))
                }, 1000
            )
        }
    }

    private fun repeatePasswordWarning() {
        with(mainBinding) {
            repeatePasswordTextInputEditText.setBackgroundColor(resources.getColor(R.color.main_input_empty_Bground))
            timer.schedule(
                timerTask {
                    repeatePasswordTextInputEditText.setBackgroundColor(resources
                        .getColor(R.color.main_input_Bground))
                }, 1000
            )
        }
    }

    private fun birthdayWarning() {
        with(mainBinding) {
            openDatePickerButton.setBackgroundColor(resources.getColor(R.color.main_input_empty_Bground))
            openDatePickerButton.setTextColor(resources.getColor(R.color.main_input_empty_text))
            timer.schedule(
                timerTask {
                    openDatePickerButton.setBackgroundColor(resources.getColor(R.color.main_button_Bground))
                    openDatePickerButton.setTextColor(resources.getColor(R.color.main_button_text))
                }, 1000
            )
        }
    }
}

