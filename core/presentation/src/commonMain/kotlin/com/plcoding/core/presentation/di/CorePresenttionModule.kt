package com.plcoding.core.presentation.di

import com.plcoding.core.presentation.util.ScopedStoreRegistryVIewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val corePresentationModule = module {
    viewModelOf(::ScopedStoreRegistryVIewModel)
}