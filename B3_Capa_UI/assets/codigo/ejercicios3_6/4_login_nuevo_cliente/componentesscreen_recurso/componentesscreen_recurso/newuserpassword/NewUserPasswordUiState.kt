package com.pmdm.tienda.ui.features.newuser.newuserpassword

import com.pmdm.utilities.validacion.Validacion

data class NewUserPasswordUiState(
    val login: String,
    val password: String,
) {
    constructor() : this(
        login = "",
        password = "",
    )
}