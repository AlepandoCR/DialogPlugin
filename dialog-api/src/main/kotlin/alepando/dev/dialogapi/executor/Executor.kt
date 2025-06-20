package alepando.dev.dialogapi.executor

import alepando.dev.dialogapi.factory.actions.CustomAction
import alepando.dev.dialogapi.factory.actions.KillPlayerAction
import alepando.dev.dialogapi.packets.reader.InputReader
import alepando.dev.dialogapi.packets.reader.types.PlayerReturnValueReader
import net.minecraft.resources.ResourceLocation

const val namespace = "dialog_plugin"

enum class Executor(val key: ResourceLocation, val action: CustomAction, val reader: InputReader) {

    KILL_PLAYER(ResourceLocation.fromNamespaceAndPath(namespace, "kill_player"), KillPlayerAction, PlayerReturnValueReader);

    companion object {
        private val byLocation = entries.associateBy { it.key }

        fun from(location: ResourceLocation): Executor? = byLocation[location]
    }
}
