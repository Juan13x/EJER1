package com.juanguicj.lab1_ejer1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.juanguicj.lab1_ejer1.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    /*ActivityMainBinding = is base on the name of the .xml of the activity
    in this case, activity_main.xml, so ActivityMainBinding
    */
    private lateinit var mainBinding: ActivityMainBinding
    private val timer = Timer()

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        val calendar = Calendar.getInstance()

        val setDate = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)

            val format = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(format)
            val birthdate = sdf.format(calendar.time).toString()
            mainBinding.openDatePickerButton.text = birthdate
        }

        with(mainBinding) {
            genderRadioGroup.check(gender1RadioButton.id)

            openDatePickerButton.setOnClickListener {
                DatePickerDialog(
                    this@MainActivity,
                    setDate,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                ).show()
            }

            saveButton.setOnClickListener {
                val password = passwordTextInputEditText.text.toString()
                val repPassword = repeatePasswordTextInputEditText.text.toString()
                val name = nameEditText.text
                val email = emailEditText.text

                var hobbies = ""
                if (hobbie1CheckBox.isChecked)
                    hobbies += hobbie1CheckBox.text
                if (hobbie2CheckBox.isChecked)
                    hobbies += ", " + hobbie2CheckBox.text
                if (hobbie3CheckBox.isChecked)
                    hobbies += ", " + hobbie3CheckBox.text
                if (hobbie4CheckBox.isChecked)
                    hobbies += ", " + hobbie4CheckBox.text
                if (hobbies.isNotBlank()){
                    if(hobbies.substring(0, 2) == ", ")
                        hobbies =  hobbies.substring(2, hobbies.lastIndex + 1)
                }
                val gender = if (gender1RadioButton.isChecked) gender1RadioButton.text else
                    gender2RadioButton.text
                val birthdate = openDatePickerButton.text
                val city = citySpinner.selectedItem

                var empty = false

                if (nameEditText.text.isEmpty()) {
                    nameEditText.setBackgroundColor(resources.getColor(R.color.main_input_empty_Bground))
                    nameEditText.setHintTextColor(resources.getColor(R.color.main_input_empty_text))
                    timer.schedule(
                        timerTask {
                            nameEditText.setBackgroundColor(resources.getColor(R.color.main_input_Bground))
                            nameEditText.setHintTextColor(resources.getColor(R.color.main_input_hint_text))
                        }, 1000
                    )
                    empty = true
                }
                if (emailEditText.text.isEmpty()) {
                    emailEditText.setBackgroundColor(resources.getColor(R.color.main_input_empty_Bground))
                    emailEditText.setHintTextColor(resources.getColor(R.color.main_input_empty_text))
                    timer.schedule(
                        timerTask {
                            emailEditText.setBackgroundColor(resources.getColor(R.color.main_input_Bground))
                            emailEditText.setHintTextColor(resources.getColor(R.color.main_input_hint_text))
                        }, 1000
                    )
                    empty = true
                }
                if (passwordTextInputEditText.text.isNullOrEmpty()) {
                    passwordTextInputEditText.setBackgroundColor(resources.getColor(R.color.main_input_empty_Bground))
                    timer.schedule(
                        timerTask {
                            passwordTextInputEditText.setBackgroundColor(resources.getColor(R
                                .color.main_input_Bground))
                        }, 1000
                    )
                    empty = true
                }
                if (repeatePasswordTextInputEditText.text.isNullOrEmpty()) {
                    repeatePasswordTextInputEditText.setBackgroundColor(resources.getColor(R
                        .color.main_input_empty_Bground))
                    timer.schedule(
                        timerTask {
                            repeatePasswordTextInputEditText.setBackgroundColor(resources
                                .getColor(R.color.main_input_Bground))
                        }, 1000
                    )
                    empty = true
                }
                if (openDatePickerButton.text == getString(R.string.main_openDatapicker)) {
                    openDatePickerButton.setBackgroundColor(resources.getColor(R.color.main_input_empty_Bground))
                    openDatePickerButton.setTextColor(resources.getColor(R.color.main_input_empty_text))
                    timer.schedule(
                        timerTask {
                            openDatePickerButton.setBackgroundColor(resources.getColor(R.color
                                .main_button_Bground))
                            openDatePickerButton.setTextColor(resources.getColor(R.color.main_button_text))
                        }, 1000
                    )
                    empty = true
                }

                if (!empty){
                    if(repPassword == password)
                        resultTextView.text = getString(
                            R.string.main_result, name, email, password, gender,
                            hobbies, birthdate, city
                        )
                    else
                        resultTextView.text = getString(R.string.main_different_password)
                } else{
                    resultTextView.text = ""
                }
            }
        }
    }
}

