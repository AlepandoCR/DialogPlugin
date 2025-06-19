package alepando.dev.dialogPlugin.dialog.packets.reader

import alepando.dev.dialogPlugin.DialogPlugin
import alepando.dev.dialogPlugin.dialog.executor.Keys
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import org.bukkit.entity.Player

object ReaderManager {

    fun peekInputs(player: Player, packet: ServerboundCustomClickActionPacket) {
        val key = Keys.from(packet.id) ?: return
        key.reader.task(player,packet)
    }

    fun peekActions(player: Player, packet: ServerboundCustomClickActionPacket, plugin: DialogPlugin) {
        val key = Keys.from(packet.id) ?: return
        key.action.execute(player,plugin)
    }

}
