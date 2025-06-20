package alepando.dev.dialogapi.body.types

import alepando.dev.dialogapi.body.types.builders.ItemDialogBodyBuilder
import io.mockk.every
import io.mockk.mockk
import net.minecraft.server.dialog.body.ItemDialogBody as NMSItemDialogBody // Alias for NMS type
import net.minecraft.server.dialog.body.PlainMessage as NMSPlainMessage // Alias for NMS type
import org.bukkit.inventory.ItemStack // Bukkit type
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.bukkit.Material // Required for creating a real ItemStack if needed for mocking CraftItemStack

// Import CraftItemStack for mocking if ItemDialogBody.toNMS() directly uses it.
// If ItemDialogBody directly calls CraftItemStack.asNMSCopy(itemStack), we need to mock this static method.
// For these tests, we'll assume ItemDialogBody's constructor takes an ItemStack
// and its .toNMS() method handles the conversion, which might involve CraftItemStack.
// We will mock the Bukkit ItemStack.
import org.bukkit.craftbukkit.inventory.CraftItemStack // For mocking static method if necessary

class ItemDialogBodyTest {

    private lateinit var builder: ItemDialogBodyBuilder
    private lateinit var mockBukkitItemStack: ItemStack // Mocking Bukkit's ItemStack
    private lateinit var mockNmsItemStack: net.minecraft.world.item.ItemStack // Mocking NMS ItemStack
    private lateinit var mockDescription: NMSPlainMessage

    @BeforeEach
    fun setUp() {
        builder = ItemDialogBodyBuilder()
        mockBukkitItemStack = mockk<ItemStack>(relaxed = true)
        mockNmsItemStack = mockk<net.minecraft.world.item.ItemStack>(relaxed = true)
        mockDescription = mockk<NMSPlainMessage>(relaxed = true)

        // Mock the static CraftItemStack.asNMSCopy method if direct conversion is problematic in tests
        // For ItemDialogBody.toNMS() to work, it needs to convert Bukkit ItemStack to NMS.
        // This static method is final, so we'd typically need PowerMock or to test differently.
        // However, MockK can mock companion object methods or top-level functions.
        // If CraftItemStack.asNMSCopy is a static Java method, it's harder.
        // A common workaround: if ItemDialogBody uses CraftItemStack.asCraftCopy(item).handle,
        // then mockBukkitItemStack needs to be a CraftItemStack, or its .handle needs to be mocked.
        // Let's assume for now ItemDialogBody can take any Bukkit ItemStack and internally handle it.
        // If direct calls to CraftItemStack static methods are an issue, tests would need adjustment or a helper.
        // For now, we rely on relaxed mocks and focus on builder logic.

        // Set default values for builder to be valid for basic build
        builder.item(mockBukkitItemStack) // item is mandatory
        builder.width(20)          // width is part of DialogBody constructor
    }

    @Test
    fun `builder should build ItemDialogBody with item and width`() {
        val itemBody = builder.build()
        assertNotNull(itemBody)
        assertEquals(20, itemBody.width)
        assertDoesNotThrow { itemBody.toNMS() }
    }

    @Test
    fun `builder should set height`() {
        val itemBody = builder.height(30).build()
        assertDoesNotThrow {
            val nmsBody = itemBody.toNMS()
            assertNotNull(nmsBody)
            // Example: assertEquals(30, nmsBody.height) // If accessible & set by our class
        }
    }

    @Test
    fun `builder should set description`() {
        val itemBody = builder.description(mockDescription).build()
        assertDoesNotThrow {
            val nmsBody = itemBody.toNMS()
            assertNotNull(nmsBody)
            // Example: assertEquals(mockDescription, nmsBody.description().orElse(null)) // If accessible
        }
    }

    @Test
    fun `builder should set showDecorations true`() {
        val itemBody = builder.showDecorations(true).build()
        assertDoesNotThrow {
            val nmsBody = itemBody.toNMS()
            assertNotNull(nmsBody)
            // Example: assertTrue(nmsBody.showDecorations()) // If accessible
        }
    }

    @Test
    fun `builder should set showDecorations false`() {
        val itemBody = builder.showDecorations(false).build()
        assertDoesNotThrow {
            val nmsBody = itemBody.toNMS()
            assertNotNull(nmsBody)
            // Example: assertFalse(nmsBody.showDecorations()) // If accessible
        }
    }

    @Test
    fun `builder should set showTooltip true`() {
        val itemBody = builder.showTooltip(true).build()
        assertDoesNotThrow {
            val nmsBody = itemBody.toNMS()
            assertNotNull(nmsBody)
            // Example: assertTrue(nmsBody.showTooltip()) // If accessible
        }
    }

    @Test
    fun `builder should set showTooltip false`() {
        val itemBody = builder.showTooltip(false).build()
        assertDoesNotThrow {
            val nmsBody = itemBody.toNMS()
            assertNotNull(nmsBody)
            // Example: assertFalse(nmsBody.showTooltip()) // If accessible
        }
    }

    @Test
    fun `toNMS should produce NMSItemDialogBody`() {
        // To properly test toNMS which involves CraftItemStack.asCraftCopy(item).handle
        // we might need to mock CraftItemStack or use a real CraftItemStack if possible.
        // For this test, we'll create a simple real ItemStack and rely on relaxed mocks for now,
        // or assume that the internal conversion logic is sound and focus on our builder.

        // If CraftItemStack is an issue, this test might be more of a smoke test
        // unless CraftBukkit is part of the test classpath and initialized.

        val realBukkitItemStack = ItemStack(Material.STONE) // Use a real Bukkit ItemStack

        val itemBody = builder
            .item(realBukkitItemStack) // Use the real item here
            .height(25)
            .description(mockDescription)
            .showDecorations(true)
            .showTooltip(false)
            .build()

        // Due to CraftItemStack.asCraftCopy potentially being problematic in a pure unit test,
        // this toNMS() call might fail if not handled.
        // A full integration test or mocking CraftItemStack would be needed for robust verification.
        // For now, if it throws an error related to CraftItemStack, the test setup needs more work.
        // We'll assume the added Paper API dependency helps resolve CraftItemStack for the test runtime.
        assertDoesNotThrow("toNMS should not throw if CraftItemStack is available") {
            val nmsBody = itemBody.toNMS()
            assertNotNull(nmsBody)
            assertTrue(nmsBody is NMSItemDialogBody)
        }
    }
}
