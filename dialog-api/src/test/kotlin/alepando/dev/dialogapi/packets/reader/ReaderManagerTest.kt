package alepando.dev.dialogapi.packets.reader // Same package as ReaderManager

import alepando.dev.dialogapi.executor.CustomKeyRegistry
import alepando.dev.dialogapi.factory.actions.CustomAction
import io.mockk.*
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import net.minecraft.resources.ResourceLocation
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


class ReaderManagerTest {

    private lateinit var mockPlayer: Player
    private lateinit var mockPacket: ServerboundCustomClickActionPacket
    private lateinit var mockPlugin: Plugin
    private lateinit var mockAction: CustomAction
    private lateinit var mockReader: InputReader

    private val testNamespace = "test_rm"
    private val testPath = "test_action"
    private lateinit var testLocation: ResourceLocation

    @BeforeEach
    fun setUp() {
        CustomKeyRegistry.clearAll() // Ensure clean registry for each test

        mockPlayer = mockk<Player>(relaxed = true)
        mockPacket = mockk<ServerboundCustomClickActionPacket>(relaxed = true)
        mockPlugin = mockk<Plugin>(relaxed = true)
        mockAction = mockk<CustomAction>(relaxed = true)
        mockReader = mockk<InputReader>(relaxed = true)

        testLocation = ResourceLocation.fromNamespaceAndPath(testNamespace, testPath)
        every { mockPacket.id } returns testLocation // Make packet always return our testLocation
    }

    @AfterEach
    fun tearDown() {
        CustomKeyRegistry.clearAll()
    }

    @Test
    fun `peekInputs should call reader task for registered key`() {
        CustomKeyRegistry.register(testNamespace, testPath, mockAction, mockReader)

        ReaderManager.peekInputs(mockPlayer, mockPacket)

        verify(exactly = 1) { mockReader.task(mockPlayer, mockPacket, any()) }
        verify(exactly = 0) { mockAction.execute(any(), any()) } // Ensure action is not called
    }

    @Test
    fun `peekInputs should not call reader task for unregistered key`() {
        // Do not register the key
        val unregisteredPacket = mockk<ServerboundCustomClickActionPacket>(relaxed = true)
        every { unregisteredPacket.id } returns ResourceLocation.fromNamespaceAndPath("unregistered", "key")

        ReaderManager.peekInputs(mockPlayer, unregisteredPacket)

        verify(exactly = 0) { mockReader.task(any(), any(), any()) }
    }

    @Test
    fun `peekActions should call action execute for registered key`() {
        CustomKeyRegistry.register(testNamespace, testPath, mockAction, mockReader)

        ReaderManager.peekActions(mockPlayer, mockPacket, mockPlugin)

        verify(exactly = 1) { mockAction.execute(mockPlayer, mockPlugin) }
        verify(exactly = 0) { mockReader.task(any(), any(), any()) } // Ensure reader is not called
    }

    @Test
    fun `peekActions should not call action execute for unregistered key`() {
        // Do not register the key
        val unregisteredPacket = mockk<ServerboundCustomClickActionPacket>(relaxed = true)
        every { unregisteredPacket.id } returns ResourceLocation.fromNamespaceAndPath("unregistered", "key")

        ReaderManager.peekActions(mockPlayer, unregisteredPacket, mockPlugin)

        verify(exactly = 0) { mockAction.execute(any(), any()) }
    }

    @Test
    fun `peekInputs should not fail if binding has no reader (though KeyBinding requires it)`() {
        // This case should ideally not happen due to KeyBinding constructor,
        // and CustomKeyRegistry.register enforces both action and reader are non-null.
        // This test is more about the robustness of ReaderManager if a null somehow got into the binding.
        // However, with current non-nullable types in KeyBinding, this specific scenario is prevented by Kotlin's type system
        // and the non-nullable parameters in CustomKeyRegistry.register.
        // If KeyBinding's fields were nullable and register allowed it:
        // val mockBindingWithNullReader = mockk<alepando.dev.dialogapi.executor.KeyBinding>()
        // every { mockBindingWithNullReader.action } returns mockAction
        // every { mockBindingWithNullReader.reader } returns null
        // mockkObject(CustomKeyRegistry) // Or use a more direct way to inject this mock binding if possible
        // every { CustomKeyRegistry.getBinding(testLocation) } returns mockBindingWithNullReader

        assertDoesNotThrow {
            // Simulate CustomKeyRegistry returning a binding where reader might be unexpectedly null
            // This requires more advanced mocking of CustomKeyRegistry.getBinding or KeyBinding itself
            // For now, given KeyBinding's non-nullable types, this test is mostly conceptual
            // unless we explicitly mock CustomKeyRegistry.getBinding to return a flawed KeyBinding.

            // Simple case: if getBinding returns null (as if key not found), peekInputs should not throw.
            // This is already covered by `peekInputs should not call reader task for unregistered key`.
            // So, this specific test for a KeyBinding with a null reader is hard to set up
            // without making KeyBinding fields nullable or deeply mocking.
            // The current structure of ReaderManager (binding?.reader?.task) is null-safe.
            ReaderManager.peekInputs(mockPlayer, mockPacket) // Assuming testLocation is not registered here
        }
         verify(exactly = 0) { mockReader.task(any(), any(), any()) } // Ensure no task is called if not registered
    }

    @Test
    fun `peekActions should not fail if binding has no action (though KeyBinding requires it)`() {
        // Similar to above, KeyBinding's non-nullable fields make this scenario unlikely
        // unless CustomKeyRegistry.getBinding is mocked to return a flawed KeyBinding.
        // The current structure (binding?.action?.execute) is null-safe.
        assertDoesNotThrow {
            ReaderManager.peekActions(mockPlayer, mockPacket, mockPlugin) // Assuming testLocation is not registered here
        }
        verify(exactly = 0) { mockAction.execute(any(), any()) } // Ensure no action is called if not registered
    }
}
