package dialog.factory.data

import dialog.body.DialogBody
import dialog.factory.input.Input
import net.minecraft.network.chat.Component
import net.minecraft.server.dialog.DialogAction
import java.util.*

class DialogDataBuilder {
    private var title: Component = Component.literal("Title")
    private var externalTitle: Optional<Component> = Optional.empty()
    private var canCloseWithEscape: Boolean = true
    private var pause: Boolean = false
    private var afterAction: DialogAction = DialogAction.CLOSE
    private var dialogBody: MutableList<DialogBody<*>> = mutableListOf()
    private var inputs: MutableList<Input<*>> = mutableListOf()

    fun title(title: Component): DialogDataBuilder {
        this.title = title
        return this
    }

    fun externalTitle(externalTitle: Component?): DialogDataBuilder {
        this.externalTitle = Optional.ofNullable(externalTitle)
        return this
    }

    fun canCloseWithEscape(canClose: Boolean): DialogDataBuilder {
        this.canCloseWithEscape = canClose
        return this
    }

    fun pause(pause: Boolean): DialogDataBuilder {
        this.pause = pause
        return this
    }

    fun afterAction(action: DialogAction): DialogDataBuilder {
        this.afterAction = action
        return this
    }

    fun addBody(body: DialogBody<*>): DialogDataBuilder {
        this.dialogBody.add(body)
        return this
    }

    fun setBody(bodies: List<DialogBody<*>>): DialogDataBuilder {
        this.dialogBody = bodies.toMutableList()
        return this
    }

    fun addInput(input: Input<*>): DialogDataBuilder {
        this.inputs.add(input)
        return this
    }

    fun setInputs(inputs: List<Input<*>>): DialogDataBuilder {
        this.inputs = inputs.toMutableList()
        return this
    }

    fun build(): DialogData {
        return DialogData(
            title,
            externalTitle,
            canCloseWithEscape,
            pause,
            afterAction,
            dialogBody,
            inputs
        )
    }
}
