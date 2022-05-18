package com.bacteria.bestfilm.domain.usecase

import com.bacteria.bestfilm.CoroutinesTestRule
import com.bacteria.bestfilm.domain.repository.impl.FilmRepositoryImpl
import com.bacteria.bestfilm.domain.repository.impl.UserRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoadProfile {

    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    @Test
    fun `test all works`() {
        Assert.assertTrue(true)
    }

    @Test
    fun `fetch profile`() =
        coroutineTestRule.testDispatcher.runBlockingTest {
            //TODO: Implement the test
        }

}