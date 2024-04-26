//package com.goetu.go3timekeepingserver.di
//
//
//import com.goetu.go3timekeepingserver.timelog.data.PinCodeRepositoryImpl
//import com.goetu.go3timekeepingserver.timelog.domain.repository.PinCodeRepository
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class PinCodeModule {
//
//    @Binds
//    @Singleton
//    abstract fun bindPinCodeRepository(
//        authRepository: PinCodeRepositoryImpl
//    ): PinCodeRepository
//}