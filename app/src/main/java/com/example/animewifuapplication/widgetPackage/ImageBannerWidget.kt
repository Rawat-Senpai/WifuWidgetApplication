package com.example.animewifuapplication.widgetPackage

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent

class ImageBannerWidget : AppWidgetProvider()  {

    companion object{
        private const val  TOAST_ACTION = "com.wiafu.widiget.TOAST_ACTION"
        const val EXTRA_ITEM ="com.wiafu.widget.EXTRA_ITEM"


        private fun updateAppWidget(
            context:Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId:Int
        ){



        }



    }


}