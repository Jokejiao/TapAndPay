package com.codelab.nfc.di

import com.codelab.nfc.DefaultNfcRepository
import com.codelab.nfc.NfcRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNfcRepository(nfcRepository: DefaultNfcRepository,
    ): NfcRepository
}