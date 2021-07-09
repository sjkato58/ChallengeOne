package com.katofuji.challengeone.utils

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.lang.Exception

class COUtils
{
    companion object
    {
        /**
         * Universal method to allow for Toast Popup to be made displayed.  Takes in Context
         * and as long as Context is valid, will display the information.
         *
         * @param context
         * @param message
         * @param isLong
         */
        fun displayToast(context: Context?, message: String?, isLong: Boolean)
        {
            try
            {
                if (context != null)
                {
                    if (Looper.myLooper() == Looper.getMainLooper())
                    {
                        var toastDuration = Toast.LENGTH_SHORT
                        if (isLong)
                        {
                            toastDuration = Toast.LENGTH_LONG
                        }
                        Toast.makeText(context, message, toastDuration).show()
                    }
                    else
                    {
                        val handler = Handler(Looper.getMainLooper())
                        handler.post(object : Runnable
                        {
                            override fun run()
                            {
                                displayToast(context, message, isLong)
                            }
                        })
                    }
                }
            }
            catch (ex: Exception)
            {
                ex.printStackTrace()
            }
        }


        /**
         * Universal method to change View visibility.  Takes in Context, View
         * and View Visibility Property.  If Context supplied is valid, it
         * checks whether user is on the main UI thread.  If the visibility
         * of the view doesnt match the one supplied, it changes to the new
         * visibility
         *
         * @param context
         * @param view
         * @param visibility
         */
        fun displayView(context: Context?, view: View?, visibility: Int)
        {
            try
            {
                if (context != null && view != null)
                {
                    if (Looper.myLooper() == Looper.getMainLooper())
                    {
                        if (view.visibility != visibility)
                        {
                            view.visibility = visibility
                        }
                    }
                    else
                    {
                        val handler = Handler(Looper.getMainLooper())
                        handler.post(object : Runnable
                        {
                            override fun run()
                            {
                                displayView(context, view, visibility)
                            }
                        })
                    }
                }
            }
            catch (ex: Exception)
            {
                ex.printStackTrace()
            }
        }

        /**
         * Universal method used to hide softkeyboard.
         */
        fun hideKeyboard(activity: Activity)
        {
            val inputMethodManaher = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            val currentFocusedView = activity.currentFocus
            currentFocusedView?.let {
                inputMethodManaher.hideSoftInputFromWindow(
                    currentFocusedView.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }
}