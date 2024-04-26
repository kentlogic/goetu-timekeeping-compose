package com.goetu.go3timekeepingserver.di


import com.goetu.go3timekeepingserver.timelog.data.AuthRepositoryImpl
import com.goetu.go3timekeepingserver.timelog.data.PinCodeRepositoryImpl
import com.goetu.go3timekeepingserver.timelog.data.TimelogRepositoryImpl
import com.goetu.go3timekeepingserver.timelog.domain.repository.AuthRepository
import com.goetu.go3timekeepingserver.timelog.domain.repository.PinCodeRepository
import com.goetu.go3timekeepingserver.timelog.domain.repository.TimelogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindtimelogsRepository(
        timelogsRepository: TimelogRepositoryImpl
    ): TimelogRepository


    @Binds
    @Singleton
    abstract fun bindpincodeRepository(
        timelogsRepository: PinCodeRepositoryImpl
    ): PinCodeRepository


    @Binds
    @Singleton
    abstract fun bindauthRepository(
        timelogsRepository: AuthRepositoryImpl
    ): AuthRepository
}