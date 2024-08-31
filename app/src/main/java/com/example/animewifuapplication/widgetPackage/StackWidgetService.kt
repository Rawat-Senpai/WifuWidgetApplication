package com.example.animewifuapplication.widgetPackage

import android.content.Intent
import android.widget.RemoteViewsService
import com.example.animewifuapplication.repository.AnimeRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class StackWidgetService: RemoteViewsService() {

    @Inject
    lateinit var animeRepository: AnimeRepository

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory =
        StackRemoteViewFactory(this.applicationContext, animeRepository)


}