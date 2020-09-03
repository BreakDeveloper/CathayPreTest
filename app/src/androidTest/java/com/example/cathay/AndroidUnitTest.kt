package com.example.cathay

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.example.cathay.data.respository.RegionDataRepository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AndroidUnitTest : KoinTest {
    private lateinit var lifecycle: LifecycleRegistry

    @Mock
    private lateinit var owner: LifecycleOwner

    @Mock
    private lateinit var app: App

    private val regionDataRepository: RegionDataRepository by inject()

    @Rule
    @JvmField
    val testRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        App.instance = app

        lifecycle = LifecycleRegistry(owner)
        lifecycle.currentState = Lifecycle.State.RESUMED
        Mockito.`when`(owner.lifecycle).thenReturn(lifecycle)
    }

    @After
    fun after() {
        stopKoin()
    }
}