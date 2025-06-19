package dialog.packets.reader

import dialog.executor.Keys
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import org.bukkit.entity.Player
import org.fuchiBol.FuchiBol

object ReaderManager {

    fun peekInputs(player: Player, packet: ServerboundCustomClickActionPacket) {
        val key = Keys.from(packet.id) ?: return
        key.reader.task(player,packet)
    }

    fun peekActions(player: Player, packet: ServerboundCustomClickActionPacket, plugin: FuchiBol) {
        val key = Keys.from(packet.id) ?: return
        key.action.execute(player,plugin)
    }

}
