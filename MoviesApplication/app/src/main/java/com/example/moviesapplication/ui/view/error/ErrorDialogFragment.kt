package com.example.moviesapplication.ui.view.error

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.moviesapplication.R

class ErrorDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setMessage(R.string.an_error_occurred)
            .setPositiveButton(R.string.OK) { _, _ -> }
            .create()
    }
}
