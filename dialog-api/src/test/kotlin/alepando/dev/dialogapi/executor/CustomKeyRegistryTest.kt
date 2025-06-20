package alepando.dev.dialogapi.executor

import alepando.dev.dialogapi.factory.actions.CustomAction
import alepando.dev.dialogapi.packets.reader.InputReader
import io.mockk.mockk
// import io.mockk.verify // Not used in the provided code, can be removed
import net.minecraft.resources.ResourceLocation
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CustomKeyRegistryTest {

    private lateinit var mockAction: CustomAction
    private lateinit var mockReader: InputReader

    @BeforeEach
    fun setUp() {
        // Clear registry before each test to ensure isolation
        CustomKeyRegistry.clearAll()
        mockAction = mockk<CustomAction>(relaxed = true)
        mockReader = mockk<InputReader>(relaxed = true)
    }

    @AfterEach
    fun tearDown() {
        // Clear registry after each test
        CustomKeyRegistry.clearAll()
    }

    @Test
    fun `register should add new key binding successfully`() {
        val namespace = "test_plugin"
        val path = "test_action"

        assertDoesNotThrow {
            CustomKeyRegistry.register(namespace, path, mockAction, mockReader)
        }

        val location = ResourceLocation.fromNamespaceAndPath(namespace, path)
        val binding = CustomKeyRegistry.getBinding(location)
        assertNotNull(binding)
        assertEquals(mockAction, binding?.action)
        assertEquals(mockReader, binding?.reader)
    }

    @Test
    fun `getBinding should return null for unregistered key`() {
        val location = ResourceLocation.fromNamespaceAndPath("non_existent", "key")
        assertNull(CustomKeyRegistry.getBinding(location))
    }

    @Test
    fun `register should throw IllegalStateException for duplicate key`() {
        val namespace = "test_plugin"
        val path = "duplicate_action"
        CustomKeyRegistry.register(namespace, path, mockAction, mockReader) // First registration

        val exception = assertThrows(IllegalStateException::class.java) {
            CustomKeyRegistry.register(namespace, path, mockk<CustomAction>(), mockk<InputReader>()) // Second registration
        }
        assertTrue(exception.message?.contains("already registered") == true)
    }

    @Test
    fun `register should throw IllegalArgumentException for invalid namespace`() {
        assertThrows(IllegalArgumentException::class.java) {
            // ResourceLocation constructor now validates, so this should throw
            CustomKeyRegistry.register("Invalid Namespace!", "test_action", mockAction, mockReader)
        }
    }

    @Test
    fun `register should throw IllegalArgumentException for invalid path`() {
        assertThrows(IllegalArgumentException::class.java) {
            // ResourceLocation constructor now validates, so this should throw
            CustomKeyRegistry.register("test_plugin", "Invalid Path!", mockAction, mockReader)
        }
    }

    @Test
    fun `clearAll should remove all registered keys`() {
        CustomKeyRegistry.register("test1", "path1", mockAction, mockReader)
        CustomKeyRegistry.register("test2", "path2", mockAction, mockReader)

        CustomKeyRegistry.clearAll()

        assertNull(CustomKeyRegistry.getBinding(ResourceLocation.fromNamespaceAndPath("test1", "path1")))
        assertNull(CustomKeyRegistry.getBinding(ResourceLocation.fromNamespaceAndPath("test2", "path2")))
    }

    // Example CustomAction and InputReader for more concrete binding tests if needed
    class TestAction : CustomAction() {
        var executed = false
        override fun task(player: Player, plugin: Plugin) { executed = true }
    }
    class TestReader : InputReader {
        var tasked = false
        override fun task(player: Player, packet: net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket, value: Any?) { tasked = true }
    }

    @Test
    fun `getBinding retrieves correct concrete types`() {
        val specificAction = TestAction()
        val specificReader = TestReader()
        val namespace = "concrete"
        val path = "test"

        CustomKeyRegistry.register(namespace, path, specificAction, specificReader)
        val binding = CustomKeyRegistry.getBinding(ResourceLocation.fromNamespaceAndPath(namespace, path))

        assertNotNull(binding)
        assertSame(specificAction, binding?.action) // Use assertSame for object identity
        assertSame(specificReader, binding?.reader) // Use assertSame for object identity
    }
}
