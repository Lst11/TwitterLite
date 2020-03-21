package com.lst11.twitterlite.executor

import io.reactivex.Scheduler

interface PostExecutorThread {
    fun getScheduler(): Scheduler
}