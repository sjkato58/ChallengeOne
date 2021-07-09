package com.katofuji.challengeone.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.katofuji.challengeone.R
import com.katofuji.challengeone.components.LoginStatus
import com.katofuji.challengeone.databinding.FragmentLoginBinding
import com.katofuji.challengeone.utils.COUtils
import com.katofuji.challengeone.viewmodels.LoginViewModel


class LoginFragment : Fragment()
{
    lateinit var mBinding: FragmentLoginBinding
    lateinit var mViewHolder: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mViewHolder = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        mBinding = FragmentLoginBinding.inflate(inflater, container, false)

        mViewHolder.mLoginStatus.observe(viewLifecycleOwner, { status ->
            if (status == LoginStatus.Ok)
            {
                findNavController().navigate(R.id.action_loginFragment_to_photosFragment)
            }
            else if (status != LoginStatus.Default)
            {
                val message = mViewHolder.obtainErrorMessage(requireContext(), status)
                if (!TextUtils.isEmpty(message))
                {
                    COUtils.displayToast(requireContext(), message, false)
                }
            }
        })

        mBinding.loginBtn.setOnClickListener(mViewHolder.mListener)

        mBinding.emailinputEtv.addTextChangedListener(mViewHolder.mEmailTextWatcher)

        mBinding.passwordinputEtv.addTextChangedListener(mViewHolder.mPasswordTextWatcher)

        return mBinding.root
    }
}