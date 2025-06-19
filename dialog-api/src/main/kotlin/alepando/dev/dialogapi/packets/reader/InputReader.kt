package alepando.dev.dialogapi.packets.reader

import alepando.dev.dialogapi.packets.parser.CustomActionPacket
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import org.bukkit.entity.Player

interface InputReader {
    fun task(player: Player, packet: ServerboundCustomClickActionPacket, value: Any? = CustomActionPacket.getValue(packet))
}