package com.katofuji.challengeone.viewmodels

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.core.util.PatternsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katofuji.challengeone.R
import com.katofuji.challengeone.components.LoginStatus
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel()
{
    var mEmail: String = ""
    var mPassword: String = ""

    val mLoginStatus: MutableLiveData<LoginStatus> = MutableLiveData(LoginStatus.Default)

    init
    {

    }

    val mEmailTextWatcher = object : TextWatcher
    {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
        {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
        {
            mEmail = s.toString()
        }

        override fun afterTextChanged(s: Editable?)
        {

        }
    }

    val mPasswordTextWatcher = object : TextWatcher
    {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
        {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
        {
            mPassword = s.toString()
        }

        override fun afterTextChanged(s: Editable?)
        {

        }
    }

    val mListener = View.OnClickListener { checkLoginStatus() }

    private fun checkLoginStatus()
    {
        if (TextUtils.isEmpty(mEmail))
        {
            setLoginStatus((LoginStatus.Error_BlankEmail))
        }
        else if (PatternsCompat.EMAIL_ADDRESS.matcher(mEmail).matches())
        {
            setLoginStatus((LoginStatus.Error_InvalidEmail))
        }
        else if (TextUtils.isEmpty(mPassword))
        {
            setLoginStatus((LoginStatus.Error_BlankPW))
        }
        else
        {
            setLoginStatus((LoginStatus.Ok))
        }
    }

    private fun setLoginStatus(loginStatus: LoginStatus)
    {
        viewModelScope.launch {
            mLoginStatus.value = loginStatus
        }
    }

    fun obtainErrorMessage(context: Context, loginStatus: LoginStatus): String
    {
        var message = ""
        when (loginStatus)
        {
            LoginStatus.Error_BlankEmail ->
            {
                message = context.getString(R.string.error_emailblank)
            }
            LoginStatus.Error_InvalidEmail ->
            {
                message = context.getString(R.string.error_emailinvalid)
            }
            LoginStatus.Error_BlankPW ->
            {
                message = context.getString(R.string.error_pwblank)
            }
        }
        return message
    }
}