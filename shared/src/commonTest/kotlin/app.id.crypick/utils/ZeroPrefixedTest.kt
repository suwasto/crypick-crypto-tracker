package app.id.crypick.utils

import kotlin.test.Test
import kotlin.test.assertEquals

class ZeroPrefixedTest {

    @Test
    fun `format 1 to 01`() {
        val actual = 1.zeroPrefixed(maxLength = 2)
        val expected = "01"
        assertEquals(expected, actual)
    }

    @Test
    fun `format 2 to 02`() {
        val actual = 2.zeroPrefixed(maxLength = 2)
        val expected = "02"
        assertEquals(expected, actual)
    }

    @Test
    fun `format 10 to 10`() {
        val actual = 10.zeroPrefixed(maxLength = 2)
        val expected = "10"
        assertEquals(expected, actual)
    }

    @Test
    fun `format 10 to 010`() {
        val actual = 10.zeroPrefixed(maxLength = 3)
        val expected = "010"
        assertEquals(expected, actual)
    }

    @Test
    fun `format 2 to 002`() {
        val actual = 2.zeroPrefixed(maxLength = 3)
        val expected = "002"
        assertEquals(expected, actual)
    }

    @Test
    fun `format 2 to 00002`() {
        val actual = 2.zeroPrefixed(maxLength = 5)
        val expected = "00002"
        assertEquals(expected, actual)
    }

    @Test
    fun `format 12 to 00012`() {
        val actual = 12.zeroPrefixed(maxLength = 5)
        val expected = "00012"
        assertEquals(expected, actual)
    }

    @Test
    fun `format 12345 to 12345`() {
        val actual = 12345.zeroPrefixed(maxLength = 5)
        val expected = "12345"
        assertEquals(expected, actual)
    }

    @Test
    fun `format negative int to empty string`() {
        val actual = (-1).zeroPrefixed(maxLength = 5)
        val expected = ""
        assertEquals(expected, actual)
    }

    @Test
    fun `format to empty string if maxLength is below 1`() {
        val actual = 123.zeroPrefixed(maxLength = 0)
        val expected = ""
        assertEquals(expected, actual)
    }

}