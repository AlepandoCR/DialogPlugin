package alepando.dev.dialogPlugin.dialog.types

import alepando.dev.dialogPlugin.dialog.factory.Dialog
import alepando.dev.dialogPlugin.dialog.factory.Wrapper
import alepando.dev.dialogPlugin.dialog.factory.button.Button
import alepando.dev.dialogPlugin.dialog.factory.data.DialogData
import net.minecraft.server.dialog.ActionButton
import net.minecraft.server.dialog.ServerLinksDialog
import java.util.*

typealias NMSServerLinksDialog = ServerLinksDialog

class LinksDialog(
    data: DialogData,
    private val exitButton: Optional<Button>,
    private val columns: Int = 2,
    private val buttonWidth: Int = 150
) : Dialog(data), Wrapper<NMSServerLinksDialog> {

    override fun toNMS(): NMSServerLinksDialog {
        return NMSServerLinksDialog(
            data.toNMS(),
            toNMSOptionalButton(),
            columns,
            buttonWidth
        )
    }

    private fun toNMSOptionalButton(): Optional<ActionButton> {
        return exitButton.map { it.toNMS() }
    }
}
