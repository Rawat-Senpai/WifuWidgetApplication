package com.example.animewifuapplication.widgetPackage

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.example.animewifuapplication.R
import com.example.animewifuapplication.repository.AnimeRepository
import com.example.animewifuapplication.utils.NetworkResult
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class StackRemoteViewFactory @Inject constructor(@ApplicationContext private val context: Context,private val animeRepository: AnimeRepository) :
    RemoteViewsService.RemoteViewsFactory {

    private val widgetItem = ArrayList<Bitmap>()
    private val imageUrls = ArrayList<String>()


    override fun onCreate() {

    }

    override fun onDataSetChanged() {


        widgetItem.clear()

        runBlocking {
            // get wify images

            animeRepository.getWaifuPics("","")

            val response = animeRepository.waifuPicResponse.value

            if(response is NetworkResult.Success){

                imageUrls.clear()
                imageUrls.addAll(animeRepository.extractImageUrls(response.data))

                widgetItem.clear()

                imageUrls.forEach{url->

                    val bitmap = getBitmapFromUrl(url)
                    bitmap?.let {
                        widgetItem.add(it)
                    }

                }

            }


        }


    }

    override fun onDestroy() {

    }

    override fun getCount(): Int = widgetItem.size


    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(context.packageName, R.layout.widget_item)
        rv.setImageViewBitmap(R.id.image_view, widgetItem[position])

        val extras = bundleOf(ImageBannerWidget.EXTRA_ITEM to position)
        val fillIntent = Intent()
        fillIntent.putExtras(extras)

        rv.setOnClickFillInIntent(R.id.image_view, fillIntent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(p0: Int): Long = 0

    override fun hasStableIds(): Boolean = false

    private suspend fun getBitmapFromUrl(url: String): Bitmap? {

        return withContext(Dispatchers.IO) {
            try {
                val futureTarget = Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .submit()

                futureTarget.get()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }


}