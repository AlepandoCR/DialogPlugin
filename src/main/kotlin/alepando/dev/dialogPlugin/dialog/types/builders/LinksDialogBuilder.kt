package dialog.types.builders

import dialog.factory.button.Button
import dialog.factory.data.DialogData
import dialog.types.LinksDialog
import java.util.*

class LinksDialogBuilder {
    private lateinit var data: DialogData
    private var exitButton: Optional<Button> = Optional.empty()
    private var columns: Int = 2
    private var buttonWidth: Int = 150

    fun data(data: DialogData) = apply { this.data = data }
    fun exitButton(exitButton: Button?) = apply { this.exitButton = Optional.ofNullable(exitButton) }
    fun columns(columns: Int) = apply { this.columns = columns }
    fun buttonWidth(buttonWidth: Int) = apply { this.buttonWidth = buttonWidth }

    fun build(): LinksDialog {
        return LinksDialog(data, exitButton, columns, buttonWidth)
    }
}
