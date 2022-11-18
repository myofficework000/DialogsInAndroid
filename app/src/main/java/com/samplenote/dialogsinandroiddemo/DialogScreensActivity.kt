package com.samplenote.dialogsinandroiddemo

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.samplenote.dialogsinandroiddemo.Utils.logout
import com.samplenote.dialogsinandroiddemo.Utils.openConfirmationDialog
import com.samplenote.dialogsinandroiddemo.Utils.selectYourBirthday
import com.samplenote.dialogsinandroiddemo.Utils.selectYourEventPartyTime
import com.samplenote.dialogsinandroiddemo.Utils.showHobbyDialog
import com.samplenote.dialogsinandroiddemo.Utils.showProgressDialog
import com.samplenote.dialogsinandroiddemo.Utils.showSkillsDialog
import com.samplenote.dialogsinandroiddemo.Utils.showToast
import com.samplenote.dialogsinandroiddemo.Utils.validateLogin
import com.samplenote.dialogsinandroiddemo.databinding.ActivityDialogScreensBinding
import com.samplenote.dialogsinandroiddemo.databinding.CustomLoginDialogBinding

class DialogScreensActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDialogScreensBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogScreensBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            btnDelete.setOnClickListener {
                openConfirmationDialog(
                    context = this@DialogScreensActivity,
                    getString(R.string.title_delete),
                    getString(R.string.confirmation_message),
                    getString(R.string.deleted_message),
                )
            }

            btnSave.setOnClickListener {
                openConfirmationDialog(
                    context = this@DialogScreensActivity,
                    getString(R.string.title_save),
                    getString(R.string.confirmation_message_save),
                    getString(R.string.saved_message),
                )
            }
            btnDialogProgress.setOnClickListener {
                showProgressDialog(
                    this@DialogScreensActivity,
                    getString(R.string.loading)
                )
            }
            btnSelectHobby.setOnClickListener {
                showHobbyDialog(this@DialogScreensActivity)
            }
            btnSelectSkills.setOnClickListener {
                showSkillsDialog(this@DialogScreensActivity)
            }
            btnLogout.setOnClickListener {
                logout(this@DialogScreensActivity)
            }
            btnBirthDay.setOnClickListener {
                selectYourBirthday(this@DialogScreensActivity, binding.btnBirthDay)
            }
            btnLoginUsingCustomUI.setOnClickListener {
                makeCustomLoginDialog(this@DialogScreensActivity)
            }
            btnTimePicker.setOnClickListener {
                selectYourEventPartyTime(this@DialogScreensActivity, binding.btnTimePicker)
            }
        }
    }

    private fun makeCustomLoginDialog(context: Context) {
        val dialogBinding: CustomLoginDialogBinding =
            CustomLoginDialogBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(context).apply {
            setView(dialogBinding.root)
            setCancelable(false)
        }

        val dialog = builder.create()
        dialogBinding.apply {
            btnLogin.setOnClickListener {
                val email = EditTextEmail.text.toString()
                val password = EditTextPassword.text.toString()
                if (validateLogin(email, password)) {
                    showToast(this@DialogScreensActivity, getString(R.string.login_success))
                    dialog.dismiss()
                } else {
                    showToast(this@DialogScreensActivity, getString(R.string.invalid_cred))
                }
            }
            btnBack.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()

        val params = dialog.window?.attributes
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        dialog.window?.attributes = params
    }
}