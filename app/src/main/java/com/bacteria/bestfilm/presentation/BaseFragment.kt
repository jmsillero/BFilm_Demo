package com.bacteria.bestfilm.presentation

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bacteria.bestfilm.R

open class BaseFragment : Fragment() {
    private var progressDialog: AlertDialog? = null
    private var errorDialog: AlertDialog? = null


    open fun showAlertDialog(
        message: String? = getString(R.string.st_error_occurred),
        title: String = getString(R.string.st_error_title)
    ) {
        if (errorDialog == null) {
            val dialogBuilder = AlertDialog.Builder(requireActivity())
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
            val dialogBuilder = AlertDialog.Builder(requireActivity())
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