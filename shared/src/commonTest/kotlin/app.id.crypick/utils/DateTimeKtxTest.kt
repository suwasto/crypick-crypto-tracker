package app.id.crypick.utils

import kotlin.test.Test
import kotlin.test.assertEquals

class DateTimeKtxTest {

    private val dateTimeKtx = DateTimeKtx()

    @Test
    fun `getFormattedDate with +0000`() {
        val input = "2022-06-17T12:34:56+00:00"
        val actual = dateTimeKtx.getFormattedDate(input)
        assertEquals("17 June 2022", actual)
    }

    @Test
    fun `getFormattedDate with Z`() {
        val input = "2022-12-08T12:34:56Z"
        val actual = dateTimeKtx.getFormattedDate(input)
        assertEquals("08 December 2022", actual)
    }

}