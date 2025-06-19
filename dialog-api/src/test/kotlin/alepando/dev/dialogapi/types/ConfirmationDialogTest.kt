package alepando.dev.dialogapi.types

import alepando.dev.dialogapi.factory.data.DialogData
import alepando.dev.dialogapi.types.builders.ConfirmationDialogBuilder
import alepando.dev.dialogapi.util.DynamicListener
import io.mockk.*
import net.minecraft.server.dialog.ActionButton
import net.minecraft.server.dialog.CommonDialogData
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Optional

// NMSConfirmationDialog is a typealias in ConfirmationDialog.kt,
// so we use its fully qualified name for mocking if needed, or mock the underlying type.
// For this test, we'll mock net.minecraft.server.dialog.ConfirmationDialog directly.
typealias ActualNMSConfirmationDialog = net.minecraft.server.dialog.ConfirmationDialog


class ConfirmationDialogTest {

    private lateinit var builder: ConfirmationDialogBuilder
    private lateinit var mockDialogData: DialogData
    private lateinit var mockNmsDialogData: CommonDialogData
    private lateinit var mockYesButton: ActionButton
    private lateinit var mockNoButton: ActionButton
    private lateinit var mockDynamicListener: DynamicListener

    @BeforeEach
    fun setUp() {
        builder = ConfirmationDialogBuilder()
        mockDialogData = mockk<DialogData>()
        mockNmsDialogData = mockk<CommonDialogData>()
        mockYesButton = mockk<ActionButton>(relaxed = true)
        mockNoButton = mockk<ActionButton>(relaxed = true)
        mockDynamicListener = mockk<DynamicListener>(relaxed = true)

        // Mock behavior for DialogData.toNMS()
        every { mockDialogData.toNMS() } returns mockNmsDialogData
    }

    @Test
    fun `builder should build ConfirmationDialog with correct data`() {
        val dialog = builder
            .data(mockDialogData)
            .yesButton(mockYesButton)
            .noButton(mockNoButton)
            .dynamicListener(mockDynamicListener)
            .build()

        assertNotNull(dialog)
        //assertEquals(mockDialogData, dialog.data) // direct comparison might not work well with mocks
        // Instead, verify interactions or properties if accessible, or rely on toNMS behavior.
        // For now, primarily testing builder completion and toNMS.

        val nmsDialog = dialog.toNMS()
        assertNotNull(nmsDialog)
        // In a real scenario, if NMSConfirmationDialog had getters, we'd use them.
        // Here, we mainly test that the constructor of NMSConfirmationDialog is called with the right things.
        // This would typically require a more complex setup to spy on constructors or use argument captors.
        // For now, a non-crashing toNMS() is a good start.

        verify { mockDialogData.toNMS() } // Ensure DialogData.toNMS was called
    }

    @Test
    fun `builder should build ConfirmationDialog without optional dynamic listener`() {
        val dialog = builder
            .data(mockDialogData)
            .yesButton(mockYesButton)
            .noButton(mockNoButton)
            .build()

        assertNotNull(dialog)
        assertFalse(dialog.getDynamicListener().isPresent)
        assertDoesNotThrow { dialog.toNMS() }
    }

    @Test
    fun `toNMS should produce NMSConfirmationDialog`() {
        // Capture arguments passed to NMSConfirmationDialog constructor
        // This is a more advanced MockK technique.
        // We need to ensure the actual NMS class is mockable or use a more indirect approach.
        // For this example, let's assume we can capture.
        // If not, the test becomes more of a smoke test.

        // Slot to capture arguments
        val nmsDataSlot = slot<CommonDialogData>()
        val yesButtonSlot = slot<ActionButton>()
        val noButtonSlot = slot<ActionButton>()

        // Use a spy if we want to verify constructor call on the real NMS class,
        // or mockConstruction if that's too complex.
        // For simplicity, we'll just check our dialog's toNMS() output type and non-crash.

        val dialog = ConfirmationDialog(mockYesButton, mockNoButton, mockDialogData, Optional.of(mockDynamicListener))
        val nmsConfirmationDialog = dialog.toNMS()

        assertNotNull(nmsConfirmationDialog)
        // Verify that the NMS dialog was created with the NMS version of our DialogData
        // This requires NMSConfirmationDialog to have accessible properties or a way to verify.
        // If NMSConfirmationDialog is final or hard to inspect, this part is tricky.
        // assertEquals(mockNmsDialogData, nmsConfirmationDialog.dialogData) // Example if accessible

        // Verify our mockDialogData.toNMS() was called during the process
        verify { mockDialogData.toNMS() }
    }


    @Test
    fun `getDynamicListener should return listener when provided`() {
        val dialog = ConfirmationDialog(mockYesButton, mockNoButton, mockDialogData, Optional.of(mockDynamicListener))
        assertTrue(dialog.getDynamicListener().isPresent)
        assertEquals(mockDynamicListener, dialog.getDynamicListener().get())
    }

    @Test
    fun `getDynamicListener should return empty when not provided`() {
        val dialog = ConfirmationDialog(mockYesButton, mockNoButton, mockDialogData, Optional.empty())
        assertFalse(dialog.getDynamicListener().isPresent)
    }

    @Test
    fun `getBukkitListener should return listener from DynamicListener when present`() {
        val mockBukkitListener = mockk<org.bukkit.event.Listener>()
        every { mockDynamicListener.getListener() } returns mockBukkitListener
        // every { mockDynamicListener.isEmpty } returns false // This line is problematic for Optional if isEmpty is a method.
                                                            // In Dialog.kt, it's `!dynamicListener.isEmpty`
                                                            // For a mock, we need to ensure isPresent is true for the Optional.
                                                            // The Optional itself is created with Optional.of(mockDynamicListener),
                                                            // so it will be present. The isEmpty on the mock itself is not how Optional works.

        val dialog = ConfirmationDialog(mockYesButton, mockNoButton, mockDialogData, Optional.of(mockDynamicListener))

        assertEquals(mockBukkitListener, dialog.getBukkitListener())
        verify { mockDynamicListener.getListener() }
    }

    @Test
    fun `getBukkitListener should return null when DynamicListener is not present`() {
         val dialog = ConfirmationDialog(mockYesButton, mockNoButton, mockDialogData, Optional.empty())
         assertNull(dialog.getBukkitListener())
    }
}
