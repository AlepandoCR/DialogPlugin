package dialog.factory.button

import dialog.factory.Wrapper
import dialog.factory.button.data.ButtonData
import net.minecraft.server.dialog.ActionButton
import net.minecraft.server.dialog.action.Action
import java.util.*

class Button(
    private val data: ButtonData,
    private val action: Optional<Action> = Optional.empty() // Se puede poner un StaticAction con un ClickEvent parece
):Wrapper<ActionButton> {
    override fun toNMS(): ActionButton{
        return ActionButton(data.toNMS(),action)
    }
}