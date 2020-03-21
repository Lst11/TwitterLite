package com.lst11.twitterlite

import com.lst11.twitterlite.executor.PostExecutorThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

abstract class BaseService(
    val postExecutorThread: Scheduler,
    val workExecutorThread: Scheduler = Schedulers.io()
) {

    constructor(postExecutorThread: PostExecutorThread)
            : this(postExecutorThread.getScheduler())
}