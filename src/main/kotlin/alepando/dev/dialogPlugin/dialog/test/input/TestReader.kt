package alepando.dev.dialogPlugin.dialog.test.input

import alepando.dev.dialogapi.packets.reader.InputReader
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import org.bukkit.entity.Player

object TestReader: InputReader {
    override fun task(player: Player, packet: ServerboundCustomClickActionPacket, value: Any?) {
        TODO("Not yet implemented")
    }
}