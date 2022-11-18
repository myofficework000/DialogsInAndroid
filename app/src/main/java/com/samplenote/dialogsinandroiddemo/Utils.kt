package com.samplenote.dialogsinandroiddemo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.lang.StringBuilder
import java.util.*
import kotlin.system.exitProcess

object Utils {
    fun openConfirmationDialog(
        context: Context,
        title: String,
        message: String,
        toastMessage: String
    ) {
        val builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Confirm") { _, _ ->
                showToast(context, toastMessage)
            }.setNegativeButton("Cacel") { _, _ ->
                //do nothing
            }

        val alertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    fun showToast(context: Context, toastMessage: String) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
    }

    fun showProgressDialog(context: Context, message: String) {
        ProgressDialog(context).apply {
            setMessage(message)
            setCancelable(false)
            setButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }

    fun logout(context: Context) {
        val builder = AlertDialog.Builder(context).apply {
            setIcon(R.drawable.ic_baseline_login_24)
            setTitle(context.getString(R.string.logout))
            setPositiveButton("Confirm") { dialog, _ ->
                dialog.dismiss()
                exitProcess(0)
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
        }
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
    }


    fun showHobbyDialog(context: Context) {
        val hobbies = arrayOf(
            "Drawing",
            "Blogging",
            "Watching Movies",
            "Cooking",
            "PUBG playing",
            "Cricket",
            "Swimming",
            "Gym"
        )

        val builder = AlertDialog.Builder(context).apply {
            setIcon(R.drawable.ic_baseline_games_24)
            setTitle("Select your hobbies")
            setSingleChoiceItems(hobbies, 0) { dialog, position ->
                val selectedHobbies = hobbies[position]
                showToast(context, "Selected languages are $selectedHobbies")
                dialog.dismiss()
            }
        }

        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
    }

    fun showSkillsDialog(context: Context) {
        val skills = arrayOf(
            "Android",
            "Web development",
            "Java",
            "Kotlin",
            "Python",
            "C",
            "C++",
            "C#",
            "HTML",
            "CSS",
            "Javascript",
            "Pearl",
            "Php"
        )
        val checkedItems = BooleanArray(skills.size) { false }
        checkedItems[1] = true
        checkedItems[5] = true

        val builder = AlertDialog.Builder(context).apply {
            setIcon(R.drawable.ic_baseline_abc_24)
            setTitle(context.getString(R.string.select_your_skills))
            setMultiChoiceItems(skills, checkedItems) { dialog, position, checked ->
                checkedItems[position] = checked
            }
            setPositiveButton("Ok") { dialog, _ ->
                val output = StringBuilder("Selected skills are \n")
                for (i in checkedItems.indices) {
                    if (checkedItems[i]) {
                        output.append("${skills[i]}\n")
                    }
                }
                showToast(context, output.toString())
                dialog.dismiss()
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
        }
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
    }

    fun selectYourBirthday(context: Context, button: Button) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            context,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                button.text = "Date is ${selectedMonth - 1} - $selectedMonth -$selectedYear"
            },
            year,
            month,
            day
        )

        datePicker.show()
    }

    fun validateLogin(email: String, password: String): Boolean {
        //write a logic where password >6 and email should not empty
        return email.isNotEmpty() && password.length > 6
    }

    fun selectYourEventPartyTime(context: Context, button: Button) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(
            context,
            { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
                button.text = "Time is $selectedHour : $selectedMinute"
            },
            hour,
            minutes,
            true
        )
        timePicker.show()
    }

}