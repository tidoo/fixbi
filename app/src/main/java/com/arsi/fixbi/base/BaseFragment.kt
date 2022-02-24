package com.arsi.fixbi.base


import androidx.fragment.app.Fragment
import com.arsi.fixbi.dialogs.ProgressDialog

open class BaseFragment : Fragment() {
    private var progressDialog: ProgressDialog? = null


    fun showProgressDialog() {
        progressDialog = ProgressDialog(requireActivity())
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }

    fun hideProgressDialog() {
        if (progressDialog != null)
            progressDialog!!.dismiss()
    }


    fun onBackPressed() {
        requireActivity().onBackPressed()
    }

}