package alepando.dev.dialogapi.packets.reader

import alepando.dev.dialogapi.executor.Executor
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

internal object ReaderManager {

    fun peekInputs(player: Player, packet: ServerboundCustomClickActionPacket) {
        val key = Executor.from(packet.id) ?: return
        key.reader.task(player,packet)
    }

    fun peekActions(player: Player, packet: ServerboundCustomClickActionPacket, plugin: Plugin) {
        val key = Executor.from(packet.id) ?: return
        key.action.execute(player,plugin)
    }

}
