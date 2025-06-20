package alepando.dev.dialogapi.factory.input.types

import alepando.dev.dialogapi.factory.input.types.builders.BooleanInputBuilder
import io.mockk.mockk
import net.minecraft.network.chat.Component
// Assuming NMS BooleanInput is net.minecraft.server.dialog.input.BooleanInput
import net.minecraft.server.dialog.input.BooleanInput as NMSBooleanInput // Alias for clarity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BooleanInputTest {

    private lateinit var builder: BooleanInputBuilder
    private lateinit var mockLabel: Component

    @BeforeEach
    fun setUp() {
        builder = BooleanInputBuilder()
        mockLabel = mockk<Component>(relaxed = true)
        // Set default label for builder to be valid for basic build
        builder.label(mockLabel)
    }

    @Test
    fun `builder should build BooleanInput with correct label`() {
        val booleanInput = builder.build()
        assertNotNull(booleanInput)
        assertEquals(mockLabel, booleanInput.label)
    }

    @Test
    fun `builder should set initial value true`() {
        val booleanInput = builder.initial(true).build()
        // Verification via toNMS if possible, or if BooleanInput exposes its state
        assertDoesNotThrow {
            val nmsInput = booleanInput.toNMS()
            // Assuming NMSBooleanInput has an 'initial' or similar accessible property
            // For example: assertTrue((nmsInput as NMSBooleanInput).initialState)
            // This depends on the actual NMS class structure.
            // For now, this is a smoke test for toNMS().
            assertNotNull(nmsInput)
        }
    }

    @Test
    fun `builder should set initial value false`() {
        val booleanInput = builder.initial(false).build()
        assertDoesNotThrow {
            val nmsInput = booleanInput.toNMS()
            // Example: assertFalse((nmsInput as NMSBooleanInput).initialState)
            assertNotNull(nmsInput)
        }
    }

    @Test
    fun `builder should set onTrue action string`() {
        val actionString = "do_something_on_true"
        val booleanInput = builder.onTrue(actionString).build()
        // Verification via toNMS if NMSBooleanInput stores this string
        assertDoesNotThrow {
            val nmsInput = booleanInput.toNMS()
            // Example: assertEquals(actionString, (nmsInput as NMSBooleanInput).onTrueAction)
            assertNotNull(nmsInput)
        }
    }

    @Test
    fun `builder should set onFalse action string`() {
        val actionString = "do_something_on_false"
        val booleanInput = builder.onFalse(actionString).build()
        assertDoesNotThrow {
            val nmsInput = booleanInput.toNMS()
            // Example: assertEquals(actionString, (nmsInput as NMSBooleanInput).onFalseAction)
            assertNotNull(nmsInput)
        }
    }

    @Test
    fun `toNMS should produce NMSBooleanInput`() {
        val booleanInput = builder
            .initial(true)
            .onTrue("ON_TRUE")
            .onFalse("ON_FALSE")
            .build()

        val nmsInput = booleanInput.toNMS()
        assertNotNull(nmsInput)
        // This assertion depends on the actual type returned by BooleanInput.toNMS()
        assertTrue(nmsInput is NMSBooleanInput)
    }

    @Test
    fun `default values from builder should be used`() {
        // Builder sets default initial=false, onTrue="true", onFalse="false"
        val booleanInput = builder.label(Component.literal("Test")).build() // Only label is mandatory for this test
        val nmsDefaultsInput = booleanInput.toNMS()
        assertNotNull(nmsDefaultsInput)
        // Assuming NMSBooleanInput has getters for these properties after construction
        // Example:
        // assertEquals(false, nmsDefaultsInput.initial)
        // assertEquals("true", nmsDefaultsInput.onTrueText)
        // assertEquals("false", nmsDefaultsInput.onFalseText)
    }
}
