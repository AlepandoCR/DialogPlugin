package alepando.dev.dialogPlugin.dialog.executor

import alepando.dev.dialogPlugin.dialog.factory.actions.CustomAction
import alepando.dev.dialogPlugin.dialog.factory.actions.KillPlayerAction
import alepando.dev.dialogPlugin.dialog.packets.reader.InputReader
import alepando.dev.dialogPlugin.dialog.packets.reader.types.PlayerReturnValueReader
import net.minecraft.resources.ResourceLocation

const val namespace = "fuchibol"

enum class Keys(val key: ResourceLocation, val action: CustomAction, val reader: InputReader) {

    KILL_PLAYER(ResourceLocation.fromNamespaceAndPath(namespace, "kill_player"), KillPlayerAction, PlayerReturnValueReader);

    companion object {
        private val byLocation = entries.associateBy { it.key }

        fun from(location: ResourceLocation): Keys? = byLocation[location]
    }
}
