package com.ijikod.uni.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ijikod.uni.di.Injection
import com.ijikod.uni.TestUtils.Mokito
import com.ijikod.uni.Utilities.Resource
import com.ijikod.uni.data.Model.UniModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Test class for [ViewModel]
 * **/
@RunWith(AndroidJUnit4::class)
class ViewModelTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var vm : ViewModel


    private val observer: Observer<Resource<List<UniModel>>> = Mokito().mock()


    /**
     * Initialising [ViewModel] for tests
     * **/
    @Before
    fun setUp(){
        vm = ViewModel(Injection.provideRepository(ApplicationProvider.getApplicationContext()))
    }


    @Test
    fun test_model_state_change(){
        vm.uniData.observeForever(observer)
        vm.fetchData()
        Mockito.verify(observer).onChanged(vm.uniData.value)
    }

    @Test
    fun `test_for_return_values`(){
        vm.uniData.observeForever(observer)
        vm.fetchData()

        vm.uniData.value?.data?.isNotEmpty()?.let { assert(it) }
    }

}