//package com.goetu.go3timekeepingserver.di
//
//
//import com.goetu.go3timekeepingserver.timelog.data.AuthRepositoryImpl
//import com.goetu.go3timekeepingserver.timelog.domain.repository.AuthRepository
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class AuthModule {
//
//    @Binds
//    @Singleton
//    abstract fun bindauthRepository(
//        authRepository: AuthRepositoryImpl
//    ): AuthRepository
//}