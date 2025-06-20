package alepando.dev.dialogapi.executor

import alepando.dev.dialogapi.factory.actions.CustomAction
import alepando.dev.dialogapi.packets.reader.InputReader // Ensure this path is correct after InputReader became public
import net.minecraft.resources.ResourceLocation
// Consider adding a logger for warnings, e.g., import java.util.logging.Logger

/**
 * Internal data class to hold a registered custom action and its corresponding input reader.
 */
internal data class KeyBinding(val action: CustomAction, val reader: InputReader)

/**
 * A registry for developers to register custom actions and input readers,
 * associating them with a unique ResourceLocation (namespace and path).
 */
object CustomKeyRegistry {

    private val registeredKeys = mutableMapOf<ResourceLocation, KeyBinding>()
    // Optional: private val logger = Logger.getLogger(CustomKeyRegistry::class.java.name)

    /**
     * Registers a custom key with an associated action and input reader.
     *
     * @param namespace The namespace for the key (e.g., your plugin's ID).
     * @param path The path for the key (e.g., "my_custom_action").
     * @param action The [CustomAction] to be executed when this key is triggered.
     * @param reader The [InputReader] to process any input associated with this key.
     * @throws IllegalArgumentException if the namespace or path contains invalid characters for ResourceLocation.
     * @throws IllegalStateException if the key (namespace and path combination) is already registered.
     */
    fun register(namespace: String, path: String, action: CustomAction, reader: InputReader) {
        val location = try {
            ResourceLocation.fromNamespaceAndPath(namespace, path)
        } catch (e: Exception) {
            // Catching generic Exception as ResourceLocation can throw different errors for invalid chars.
            // logger.log(Level.SEVERE, "Invalid namespace or path provided: namespace='${'$'}namespace', path='${'$'}path'", e)
            throw IllegalArgumentException("Invalid namespace or path: '${'$'}namespace:${'$'}path'. Ensure they contain valid characters.", e)
        }

        if (registeredKeys.containsKey(location)) {
            // logger.warning("Custom key '${'$'}location' is already registered. Overwriting is not allowed.")
            throw IllegalStateException("Custom key '${'$'}location' is already registered.")
        }
        registeredKeys[location] = KeyBinding(action, reader)
        // logger.info("Custom key '${'$'}location' registered successfully.")
    }

    /**
     * Retrieves the [KeyBinding] (action and reader) for a given ResourceLocation.
     * Intended for internal use by the dialog system (e.g., ReaderManager).
     *
     * @param location The [ResourceLocation] of the key.
     * @return The [KeyBinding] if found, otherwise null.
     */
    internal fun getBinding(location: ResourceLocation): KeyBinding? {
        return registeredKeys[location]
    }

    /**
     * Clears all registered custom keys.
     * Mainly intended for testing or server shutdown/plugin disable scenarios.
     */
    fun clearAll() { // Made public for testing or specific lifecycle management by consuming plugin
        registeredKeys.clear()
        // logger.info("All custom keys have been cleared.")
    }
}
