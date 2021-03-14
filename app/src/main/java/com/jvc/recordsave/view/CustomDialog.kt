package com.jvc.recordsave.view

import android.app.AlertDialog
import android.content.Context

class CustomDialog {
    companion object {

        fun getDialogWithList(
            context: Context,
            title: String,
            items: Array<String>,
            _customDialogCallback: CustomDialogCallback
        ): CustomDialogCallback? {

            //    var customDialogCallback: CustomDialogCallback? = null
            //   customDialogCallback = _customDialogCallback

            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)

            builder.setItems(items) { dialog, which ->
                run {
                    _customDialogCallback!!.onDialogClickListener(position = which)
                    dialog.dismiss()
                }
            }

            val dialog = builder.create()
            dialog.show()

            return _customDialogCallback
        }
    }

   public interface CustomDialogCallback {
        fun onDialogClickListener(position: Int)
    }
}