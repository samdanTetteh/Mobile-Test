package com.ijikod.uni.businessLogic


import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


/**
 * [InteractorTest] class implements usecase unit tests
 * **/
@RunWith(JUnit4::class)
class InteractorTest{


    @Test
    fun `check_empty_title_validation`(){
        val userCase = Interactor()
        val value = userCase.titleTextValidation("")
        Truth.assertThat(userCase.formErrors).contains(FormErrors.INVALID_TITLE)
    }

    @Test
    fun `check_title_text_criteria_validation`(){
        val userCase = Interactor()
        val value = userCase.titleTextValidation("se")
        Truth.assertThat(userCase.formErrors).contains(FormErrors.INVALID_TITLE)
    }


    @Test
    fun `check_title_text_validation`(){
        val userCase = Interactor()
        val value = userCase.titleTextValidation("semdasf")
        Truth.assertThat(userCase.formErrors).isEmpty()
    }


    @Test
    fun `check_empty_body_text_validation`(){
        val useCase = Interactor()
        val value = useCase.isBodyTextValid("")
        Truth.assertThat(useCase.formErrors).contains(FormErrors.MISSING_DESC)
    }


    @Test
    fun `check_body_text_validation`(){
        val useCase = Interactor()
        val value = useCase.isBodyTextValid("Samdan")
        Truth.assertThat(useCase.formErrors).isEmpty()
    }

}