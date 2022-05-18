package com.bacteria.bestfilm.domain.usecase

import com.bacteria.bestfilm.CoroutinesTestRule
import com.bacteria.bestfilm.domain.repository.impl.FilmRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoadTest {

    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    @Test
    fun `test all works`() {
        Assert.assertTrue(true)
    }

    @Test
    fun `fetch all films`() =
        coroutineTestRule.testDispatcher.runBlockingTest {
            val repository =
                FilmRepositoryImpl(
                    FakeFilmRemoteDataSource(fakeResponse), FakeFilmLocalDataSource(
                        fakeResponse
                    )
                )

            val loadFilms = LoadFilms(repository)

            loadFilms.invoke("MX", "61").collect {
                // compare only the first element
                Assert.assertEquals(fakeResponse.toEntity().movies[0].code, it.movies[0].code)
            }

        }

}