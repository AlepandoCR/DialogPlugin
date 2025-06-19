package alepando.dev.dialogPlugin.dialog.packets.reader

import alepando.dev.dialogPlugin.dialog.executor.Keys
import alepando.dev.dialogPlugin.dialog.packets.reader.InputReader

data class ListedReader(val reader: InputReader, val resourceKey: Keys)
