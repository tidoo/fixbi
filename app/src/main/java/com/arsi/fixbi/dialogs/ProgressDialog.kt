package com.arsi.fixbi.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.arsi.fixbi.R

class ProgressDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        this.setContentView(R.layout.dialog_progress)
        this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}