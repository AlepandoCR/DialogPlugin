package alepando.dev.dialogPlugin.dialog.packets.reader.types

import alepando.dev.dialogPlugin.dialog.packets.reader.InputReader
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import org.bukkit.entity.Player

object PlayerReturnValueReader: InputReader {
    override fun task(player: Player, packet: ServerboundCustomClickActionPacket, value: Any?) {
        player.sendMessage("In: $value")
    }
}