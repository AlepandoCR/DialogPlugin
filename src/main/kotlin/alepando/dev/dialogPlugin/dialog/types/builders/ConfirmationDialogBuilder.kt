package dialog.types.builders

import dialog.factory.button.Button
import dialog.factory.data.DialogData
import dialog.factory.button.data.ButtonData
import dialog.types.ConfirmationDialog
import dialog.util.DynamicListener
import net.minecraft.server.dialog.ActionButton
import net.minecraft.server.dialog.action.Action
import java.util.*

class ConfirmationDialogBuilder {
    private var yesButton: ActionButton? = null
    private var noButton: ActionButton? = null
    private var data: DialogData? = null
    private var dynamicListener: Optional<DynamicListener> = Optional.empty()

    fun yesButton(button: Button): ConfirmationDialogBuilder {
        yesButton = button.toNMS()
        return this
    }

    fun noButton(button: Button): ConfirmationDialogBuilder {
        noButton = button.toNMS()
        return this
    }

    fun dialogData(dialogData: DialogData): ConfirmationDialogBuilder {
        this.data = dialogData
        return this
    }

    fun dynamicListener(listener: DynamicListener): ConfirmationDialogBuilder {
        this.dynamicListener = Optional.of(listener)
        return this
    }

    fun build(): ConfirmationDialog {
        val yes = yesButton ?: throw IllegalStateException("Yes button must be set")
        val no = noButton ?: throw IllegalStateException("No button must be set")
        val dialogData = data ?: throw IllegalStateException("DialogData must be set")

        return ConfirmationDialog(yes, no, dialogData, dynamicListener)
    }
}
