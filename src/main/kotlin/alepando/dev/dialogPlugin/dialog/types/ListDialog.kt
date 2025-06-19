package dialog.types

import dialog.factory.Dialog
import dialog.factory.Wrapper
import dialog.factory.button.Button
import dialog.factory.data.DialogData
import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import net.minecraft.server.dialog.ActionButton
import net.minecraft.server.dialog.DialogListDialog
import java.util.*

typealias NMSDialogListDialog = DialogListDialog

class ListDialog(
    data: DialogData,
    private val dialogs: List<net.minecraft.server.dialog.Dialog>,
    private val exitButton: Optional<Button>,
    private val columns: Int = 2,
    private val buttonWidth: Int = 150
) : Dialog(data), Wrapper<NMSDialogListDialog> {

    override fun toNMS(): NMSDialogListDialog {
        return NMSDialogListDialog(
            data.toNMS(),
            toNMSDialogHolderSet(),
            toNMSOptionalButton(),
            columns,
            buttonWidth
        )
    }

    private fun toNMSDialogHolderSet(): HolderSet<net.minecraft.server.dialog.Dialog> {
        val holders = dialogs.map { Holder.direct(it) }
        return HolderSet.direct(holders)
    }


    private fun toNMSOptionalButton(): Optional<ActionButton> {
        return exitButton.map { it.toNMS() }
    }
}
