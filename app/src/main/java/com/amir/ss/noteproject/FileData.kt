package com.amir.ss.noteproject

import android.net.Uri

data class FileData(
    val fileUri: Uri,
    val name: String,
    var mime: String,
    var isDir: Boolean
)