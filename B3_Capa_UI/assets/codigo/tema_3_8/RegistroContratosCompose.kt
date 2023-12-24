package '<AQUI_VA_EL_NOMBRE_DE_SU_PAQUETE>'

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

private fun Context.toImageBitmap(uri: Uri): ImageBitmap {
    val contextResolver = this.contentResolver
    val source = ImageDecoder.createSource(contextResolver, uri)
    return ImageDecoder.decodeBitmap(source).asImageBitmap()
}

// Uso:
//      slectorDeImagenes.launch("image/*")
@Composable
fun registroSelectorDeImagenesConGetContent(
    onFotoCambiada: (ImageBitmap) -> Unit
): ManagedActivityResultLauncher<String, Uri?> {
    val context = LocalContext.current
    return rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            onFotoCambiada(context.toImageBitmap(uri))
        }
    }
}

// Para hacer fotos con TakePicture, hay que añadir en el manifest:
// <uses-feature
//      android:name="android.hardware.camera.any"
//      android:required="true" />
// <uses-permission android:name="android.permission.CAMERA" />
//
// En el manifest, dentro de <application>:
// <provider
//     android:name="androidx.core.content.FileProvider"
//     android:authorities="${applicationId}.provider"
//     android:exported="false"
//     android:grantUriPermissions="true">
//     <meta-data
//         android:name="android.support.FILE_PROVIDER_PATHS"
//         android:resource="@xml/path_provider" />
// </provider>
//
// En res/xml/path_provider.xml:
// <?xml version="1.0" encoding="utf-8"?>
// <paths>
//     <external-cache-path
//         name="my_images"
//         path="/"/>
// </paths>
// Uso:
//      hacerFoto.launch(android.Manifest.permission.CAMERA)
@Composable
fun registroHacerFotoConTakePicture(
    onFotoCambiada: (ImageBitmap) -> Unit
): ManagedActivityResultLauncher<String, Boolean> {

    val context = LocalContext.current
    val ficheroTemporal = File.createTempFile(
        "JPEG_${SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())}_",
        ".jpg",
        context.externalCacheDir
    )
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        ficheroTemporal
    )
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                onFotoCambiada(context.toImageBitmap(uri))
            }
        }
    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { success ->
        if (success) {
            cameraLauncher.launch(uri)
        }
    }
}

// Para hacer fotos con el intent de la cámara, hay que añadir en el manifest:
// <uses-feature
//      android:name="android.hardware.camera.any"
//      android:required="true" />
// <uses-permission android:name="android.permission.CAMERA" />
// Uso:
//      hacerFoto.launch(android.Manifest.permission.CAMERA)
@Composable
fun registroHacerFotoConIntent(
    onFotoCambiada: (ImageBitmap) -> Unit
): ManagedActivityResultLauncher<String, Boolean> {
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val androidBitmap = result.data?.extras?.get("data") as Bitmap
                onFotoCambiada(androidBitmap!!.asImageBitmap())
            }
        }

    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { success ->
        if (success) {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
            cameraLauncher.launch(cameraIntent)
        }
    }
}


// Para hacer llamadas telefónicas, hay que añadir en el manifest:
// <uses-feature
//      android:name="android.hardware.telephony"
//      android:required="true" />
// <uses-permission android:name="android.permission.CALL_PHONE"/>
// Uso:
//      telefono.launch(android.Manifest.permission.CALL_PHONE)

@Composable
fun registroLlamarPorTelefonoIntent(
    telefono: String
): ManagedActivityResultLauncher<String, Boolean> {
    val context = LocalContext.current
    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { success ->
        if (success) {
            Intent(Intent.ACTION_CALL).also {
                callIntent ->
                callIntent.data = Uri.parse("tel:$telefono")
                context.startActivity(callIntent)
            }
        }
    }
}

fun Context.enviarMail(
    correo: String,
    asunto: String = "Sin asunto",
    texto: String = "Sin texto",
    forzarEleccion: Boolean = false
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_EMAIL, arrayOf(correo))
        putExtra(Intent.EXTRA_SUBJECT, asunto)
        putExtra(Intent.EXTRA_TEXT, texto)
    }
    val chooser = if (forzarEleccion) {
        Intent.createChooser(intent, "Enviar correo")
    }
    else null

    if (intent.resolveActivity(packageManager) != null) {
        startActivity(chooser ?: intent)
    }
}

// Para hacer llamadas telefónicas, hay que añadir en el manifest:
// <uses-permission android:name="android.permission.READ_CONTACTS/>
// Uso:
//      val registroSeleccionContacto 
//          = registroSelectorTelefonoContacto { telefono ->
//              ...
//      }
//      ...
//      registroSeleccionContacto.launch(android.Manifest.permission.READ_CONTACTS)

@Composable
fun registroSelectorTelefonoContacto(
    onSeleccionNumeroContacto: (String) -> Unit
): ManagedActivityResultLauncher<String, Boolean> {

    val contexto = LocalContext.current
    val registroObtenerTelefono = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val contactUri: Uri? = result.data?.data
                val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
                if (contactUri != null) {
                    contexto.contentResolver.query(contactUri, projection, null, null, null)
                        .use { cursor ->
                            if (cursor != null && cursor.moveToFirst()) {
                                val numberIndex =
                                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                val number =
                                    if (numberIndex >= 0) cursor.getString(numberIndex) else "NO NUMBER"
                                onSeleccionNumeroContacto(number)
                            }
                        }
                }
            }
        })

    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { success ->
        if (success) {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            }
            registroObtenerTelefono.launch(intent)
        }
    }
}
