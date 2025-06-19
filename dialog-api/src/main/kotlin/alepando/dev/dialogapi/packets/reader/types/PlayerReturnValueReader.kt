package alepando.dev.dialogapi.packets.reader.types

import alepando.dev.dialogapi.packets.reader.InputReader
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import org.bukkit.entity.Player

internal object PlayerReturnValueReader: InputReader {
    override fun task(player: Player, packet: ServerboundCustomClickActionPacket, value: Any?) {
        player.sendMessage("In: $value")
    }
}