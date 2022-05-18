package com.bacteria.bestfilm.presentation

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bacteria.bestfilm.R
import com.google.android.material.dialog.MaterialDialogs

open class BaseActivity : AppCompatActivity() {
    private var progressDialog: AlertDialog? = null
    private var errorDialog: AlertDialog? = null


    open fun showAlertDialog(
        message: String = getString(R.string.st_error_occurred),
        title: String = getString(R.string.st_error_title)
    ) {
        if (errorDialog == null) {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle(title)
            dialogBuilder.setMessage(message)
            dialogBuilder.setNeutralButton(
                getString(R.string.st_accept)
            ) { _, _ -> dismissError() }

            errorDialog = dialogBuilder.create()
        }

        errorDialog?.show()
    }

    open fun showProgressDialog() {
        if (progressDialog == null) {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setView(R.layout.progress_dialog)
            progressDialog = dialogBuilder.create()
        }

        progressDialog?.show()
    }

    open fun dismissProgressDialog() {
        progressDialog?.apply {
            dismiss()
        }
    }

    open fun dismissError() {
        errorDialog?.apply {
            dismiss()
        }
    }

}