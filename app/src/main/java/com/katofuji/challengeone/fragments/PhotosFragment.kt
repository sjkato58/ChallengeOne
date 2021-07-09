package com.katofuji.challengeone.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.katofuji.challengeone.adapters.PhotosAdapter
import com.katofuji.challengeone.components.VerticalSpacingItemDecoration
import com.katofuji.challengeone.components.downloads.DownloadStatus
import com.katofuji.challengeone.databinding.FragmentPhotosBinding
import com.katofuji.challengeone.utils.COUtils
import com.katofuji.challengeone.viewmodels.PhotosViewModel

class PhotosFragment : Fragment()
{
    lateinit var mUiHandler: Handler
    private lateinit var mPhotosViewModel: PhotosViewModel
    private lateinit var mBinding: FragmentPhotosBinding

    private val mAdapter: PhotosAdapter by lazy {
        PhotosAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mPhotosViewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
        mUiHandler = Handler(Looper.getMainLooper())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        mBinding = FragmentPhotosBinding.inflate(inflater, container, false)

        mBinding.toolbarBackiv.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(v: View?)
            {
                requireActivity().onBackPressed()
            }
        })

        mPhotosViewModel.getPhotosData().observe(viewLifecycleOwner, Observer { downloadItem ->
            when (downloadItem.status)
            {
                DownloadStatus.Loading ->
                {
                    COUtils.displayView(requireContext(), mBinding.spinnercontainer, View.VISIBLE)
                }
                DownloadStatus.Error ->
                {
                    COUtils.displayView(requireContext(), mBinding.spinnercontainer, View.INVISIBLE)

                    COUtils.displayToast(requireContext(), downloadItem.message, false)
                }
                DownloadStatus.Success ->
                {
                    COUtils.displayView(requireContext(), mBinding.spinnercontainer, View.INVISIBLE)

                    downloadItem.data?.let { listData ->
                        mUiHandler.post(Runnable {
                            mAdapter.setData(listData)
                        })
                    }
                }
            }
        })

        setupRecyclerview()

        COUtils.hideKeyboard(requireActivity())

        return mBinding.root
    }

    fun setupRecyclerview()
    {
        val recyclerview = mBinding.recyclerview

        val layoutManager = LinearLayoutManager(requireActivity())
        recyclerview.layoutManager = layoutManager

        val itemDecorator = VerticalSpacingItemDecoration(requireContext())
        recyclerview.addItemDecoration(itemDecorator)

        recyclerview.adapter = mAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        mUiHandler.post(Runnable {
            mPhotosViewModel.loadPhotosData()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mUiHandler.removeCallbacksAndMessages(null)
    }
}