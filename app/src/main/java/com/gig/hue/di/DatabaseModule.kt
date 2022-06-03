package com.gig.hue.com.gig.hue.di

import android.content.Context
import com.gig.hue.dao.UserDao
import com.gig.hue.dao.UserInformationDao
import com.gig.hue.data.HueDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): HueDatabase = HueDatabase.getDatabase(context)

    @Provides
    fun provideUserDao(appDatabase: HueDatabase): UserDao = appDatabase.userDao()


    @Provides
    fun provideUserInformationDao(appDatabase: HueDatabase): UserInformationDao = appDatabase.userInformationDao()

}