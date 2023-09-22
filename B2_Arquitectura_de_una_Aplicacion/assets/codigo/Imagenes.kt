package com.pmdm.agenda.utilities.imagenes

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.util.Base64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class Imagenes {
    companion object {
        fun blobToBase64(byteArray: ByteArray?): String? =
            byteArray?.let { Base64.encodeToString(it, Base64.DEFAULT) }

        fun base64ToBlob(base64ImageString: String?): ByteArray? =
            base64ImageString?.let { Base64.decode(it, Base64.DEFAULT) }

        fun base64ToBitmap(base64ImageString: String): Bitmap {
            val decodedString = base64ImageString?.let { Base64.decode(it, Base64.DEFAULT) }
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString!!.size)
        }

        fun bitmapToBase64(bitmap: Bitmap): String {
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            val byteArray: ByteArray = stream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        }

        fun bitmapFromRerouceId(recurso: Int, context: Context): Bitmap =
            BitmapFactory.decodeResource(context.resources, recurso)

        suspend fun bitmapFromURI(uri: Uri, context: Context): Bitmap = withContext(Dispatchers.IO) {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(context.contentResolver, uri)
            )
        }
    }
}