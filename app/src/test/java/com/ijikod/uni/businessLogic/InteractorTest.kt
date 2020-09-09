package com.ijikod.uni.businessLogic


import com.google.common.truth.Truth
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class InteractorTest{




    @Test
    fun checkTitleValidation(){
        val userCase = Interactor()
        val value = userCase.titleTextValidation("")
        assertEquals(value, true)
        Truth.assertThat(userCase.formErrors).contains(FormErrors.INVALID_TITLE)
    }

}