package com.ijikod.uni.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ijikod.uni.di.Injection
import com.ijikod.uni.TestUtils.Mokito
import com.ijikod.uni.Utilities.Resource
import com.ijikod.uni.data.Model.UniModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Test class for [FeedViewModel]
 * **/
@RunWith(AndroidJUnit4::class)
class FeedViewModelTest{


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var vm : FeedViewModel


    private val observer: Observer<Resource<List<UniModel>>> = Mokito().mock()


    /**
     * Initialising [FeedViewModel] for tests
     * **/
    @Before
    fun setUp(){
        vm = FeedViewModel(Injection.provideRepository(ApplicationProvider.getApplicationContext()))
    }


    @Test
    fun test_model_state_change(){

        vm.data.observeForever(observer)

        runBlocking {
            vm.data()
            Mockito.verify(observer).onChanged(vm.data.value)
        }
    }

//    @Test
//    fun `test_for_return_values`(){
//        vm.data.observeForever(observer)
//        vm.data()
//
//        vm.data.value?.data?.isNotEmpty()?.let { assert(it) }
//    }


}

//@ExperimentalCoroutinesApi
//class CoroutinesTestRule(
//    val testDispatcher: TestCoroutineDispatcher = Te
//) : TestWatcher() {
//
//    override fun starting(description: Description?) {
//        super.starting(description)
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    override fun finished(description: Description?) {
//        super.finished(description)
//        Dispatchers.resetMain()
//        testDispatcher.cleanupTestCoroutines()
//    }
//}