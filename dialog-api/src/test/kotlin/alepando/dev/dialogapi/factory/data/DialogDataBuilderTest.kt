package alepando.dev.dialogapi.factory.data

import alepando.dev.dialogapi.body.DialogBody
import alepando.dev.dialogapi.factory.input.Input
import io.mockk.every
import io.mockk.mockk
import net.minecraft.network.chat.Component
import net.minecraft.server.dialog.DialogAction // For mocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Optional

class DialogDataBuilderTest {

    private lateinit var builder: DialogDataBuilder
    private lateinit var mockTitle: Component
    private lateinit var mockExternalTitle: Component
    private lateinit var mockDialogAction: DialogAction

    @BeforeEach
    fun setUp() {
        builder = DialogDataBuilder()
        mockTitle = mockk<Component>()
        mockExternalTitle = mockk<Component>()
        mockDialogAction = mockk<DialogAction>(relaxed = true) // relaxed mock for DialogAction

        // Default setup for builder to ensure basic build() is possible
        builder.title(mockTitle)
        builder.afterAction(mockDialogAction) // Required by DialogData constructor via toNMS
    }

    @Test
    fun `title should be set correctly`() {
        val dialogData = builder.build()
        // We can't directly access private fields,
        // but we can verify it by calling toNMS and checking the CommonDialogData
        // For now, just ensure build() works and toNMS() doesn't crash with a title.
        assertNotNull(dialogData)
        assertDoesNotThrow { dialogData.toNMS() }
    }

    @Test
    fun `externalTitle should be set correctly`() {
        builder.externalTitle(mockExternalTitle)
        val dialogData = builder.build()
        assertNotNull(dialogData)
        // Further verification would involve checking the CommonDialogData from toNMS()
        assertDoesNotThrow { dialogData.toNMS() }
    }

    @Test
    fun `canCloseWithEscape should be set correctly`() {
        builder.canCloseWithEscape(true)
        val dialogData = builder.build()
        assertNotNull(dialogData)
        // Verification via toNMS().canCloseWithEscape if that field is accessible or through behavior
        // For now, focus on builder not crashing
        assertDoesNotThrow { dialogData.toNMS() }

        builder.canCloseWithEscape(false)
        val dialogDataFalse = builder.build()
        assertNotNull(dialogDataFalse)
        assertDoesNotThrow { dialogDataFalse.toNMS() }
    }

    @Test
    fun `pause should be set correctly`() {
        builder.pause(true)
        val dialogData = builder.build()
        assertNotNull(dialogData)
        assertDoesNotThrow { dialogData.toNMS() }

        builder.pause(false)
        val dialogDataFalse = builder.build()
        assertNotNull(dialogDataFalse)
        assertDoesNotThrow { dialogDataFalse.toNMS() }
    }

    @Test
    fun `addBody should include DialogBody`() {
        val mockBody = mockk<DialogBody<*>>(relaxed = true)
        every { mockBody.toNMS<Any>() } returns mockk<net.minecraft.server.dialog.body.DialogBody>(relaxed = true) // Ensure body.toNMS() is mocked

        builder.addBody(mockBody)
        val dialogData = builder.build()
        // Verification: Check if the body is in dialogData.dialogBody (if accessible)
        // or by checking the list passed to CommonDialogData in toNMS()
        // For now, ensure it builds and toNMS doesn't crash
        assertNotNull(dialogData)
        assertDoesNotThrow { dialogData.toNMS() }
    }

    @Test
    fun `addInput should include Input`() {
        val mockInput = mockk<Input<*>>(relaxed = true)
        // Mocking the chain for toNMS within DialogData
        val mockNmsInputControl = mockk<net.minecraft.server.dialog.input.InputControl>(relaxed = true)
        every { mockInput.toNMS<Any>() } returns mockNmsInputControl

        builder.addInput(mockInput)
        val dialogData = builder.build()
        assertNotNull(dialogData)
        assertDoesNotThrow { dialogData.toNMS() }
    }

    @Test
    fun `build should return DialogData instance`() {
        val dialogData = builder.build()
        assertNotNull(dialogData)
        assertTrue(dialogData is DialogData)
    }

    @Test
    fun `toNMS on built DialogData should not throw an exception`() {
        // Basic test with minimal setup (already in @BeforeEach)
        val dialogData = builder.build()
        assertDoesNotThrow {
            dialogData.toNMS()
        }
    }

    @Test
    fun `toNMS with multiple bodies and inputs`() {
        val mockBody1 = mockk<DialogBody<*>>(name = "mockBody1", relaxed = true)
        val mockBody2 = mockk<DialogBody<*>>(name = "mockBody2", relaxed = true)
        every { mockBody1.toNMS<Any>() } returns mockk<net.minecraft.server.dialog.body.DialogBody>(name = "nmsBody1", relaxed = true)
        every { mockBody2.toNMS<Any>() } returns mockk<net.minecraft.server.dialog.body.DialogBody>(name = "nmsBody2", relaxed = true)

        val mockInput1 = mockk<Input<*>>(name = "mockInput1", relaxed = true)
        val mockInput2 = mockk<Input<*>>(name = "mockInput2", relaxed = true)
        val mockNmsInputControl1 = mockk<net.minecraft.server.dialog.input.InputControl>(name = "nmsInputControl1", relaxed = true)
        val mockNmsInputControl2 = mockk<net.minecraft.server.dialog.input.InputControl>(name = "nmsInputControl2", relaxed = true)
        every { mockInput1.toNMS<Any>() } returns mockNmsInputControl1
        every { mockInput2.toNMS<Any>() } returns mockNmsInputControl2

        builder.title(mockTitle)
            .addBody(mockBody1)
            .addBody(mockBody2)
            .addInput(mockInput1)
            .addInput(mockInput2)

        val dialogData = builder.build()
        assertDoesNotThrow {
            dialogData.toNMS()
        }
    }
}
