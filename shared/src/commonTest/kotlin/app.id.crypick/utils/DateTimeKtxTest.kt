package app.id.crypick.utils

import kotlin.test.Test
import kotlin.test.assertEquals

class DateTimeKtxTest {

    private val dateTimeKtx = DateTimeKtx()

    @Test
    fun `getFormattedDate with +0000`() {
        val input = "2022-06-17T12:34:56+00:00"
        val actual = dateTimeKtx.getFormattedDate(input)
        assertEquals("2022-06-17", actual)
    }

    @Test
    fun `getFormattedDate with Z`() {
        val input = "2022-12-08T12:34:56Z"
        val actual = dateTimeKtx.getFormattedDate(input)
        assertEquals("2022-12-08", actual)
    }

}