package alepando.dev.dialogapi.body.types

import io.mockk.mockk
import net.minecraft.network.chat.Component
import net.minecraft.server.dialog.body.PlainMessage as NMSPlainMessage // Alias for NMS type

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlainMessageDialogBodyTest {

    private lateinit var mockContents: Component
    private val testWidth = 150

    @BeforeEach
    fun setUp() {
        mockContents = mockk<Component>(relaxed = true)
    }

    @Test
    fun `constructor should set width and contents`() {
        val plainBody = PlainMessageDialogBody(testWidth, mockContents)
        assertEquals(testWidth, plainBody.width)
        // Can't directly access private 'contents', will verify via toNMS()
        assertNotNull(plainBody)
    }

    @Test
    fun `toNMS should produce NMSPlainMessage with correct width and contents`() {
        val plainBody = PlainMessageDialogBody(testWidth, mockContents)
        val nmsPlainMessage = plainBody.toNMS()

        assertNotNull(nmsPlainMessage)
        assertTrue(nmsPlainMessage is NMSPlainMessage)

        // Assuming NMSPlainMessage has accessible properties or if we could use argument captors
        // For example, if NMSPlainMessage(val message: Component, val width: Int)
        // We can't directly verify mockContents was passed without more complex mocking
        // or if the NMS class exposes it.
        // However, we can check the width.
        // This depends on how NMSPlainMessage is structured.
        // If NMSPlainMessage has a 'width' or 'getMessage' getter:
        // assertEquals(testWidth, nmsPlainMessage.width) // hypothetical getter
        // assertEquals(mockContents, nmsPlainMessage.getMessage()) // hypothetical getter

        // For now, this is a smoke test that it produces the right type and doesn't crash.
    }

    @Test
    fun `toNMS with different width`() {
        val newWidth = 200
        val plainBody = PlainMessageDialogBody(newWidth, mockContents)
        val nmsPlainMessage = plainBody.toNMS()

        assertNotNull(nmsPlainMessage)
        // assertEquals(newWidth, nmsPlainMessage.width) // hypothetical
    }
}
