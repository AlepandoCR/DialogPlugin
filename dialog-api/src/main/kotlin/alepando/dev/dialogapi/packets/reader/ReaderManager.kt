package alepando.dev.dialogapi.packets.reader

import alepando.dev.dialogapi.executor.CustomKeyRegistry // Import new registry
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
// Consider adding a logger for warnings: import java.util.logging.Logger

internal object ReaderManager {
    // Optional: private val logger = Logger.getLogger(ReaderManager::class.java.name)

    fun peekInputs(player: Player, packet: ServerboundCustomClickActionPacket) {
        val binding = CustomKeyRegistry.getBinding(packet.id)
        binding?.reader?.task(player, packet)
        // Optional: if (binding == null) { logger.warning("No input reader found for packet ID: ${packet.id}") }
    }

    fun peekActions(player: Player, packet: ServerboundCustomClickActionPacket, plugin: Plugin) {
        val binding = CustomKeyRegistry.getBinding(packet.id)
        binding?.action?.execute(player, plugin)
        // Optional: if (binding == null) { logger.warning("No action found for packet ID: ${packet.id}") }
    }

}
