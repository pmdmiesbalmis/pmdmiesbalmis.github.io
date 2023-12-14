package com.pmdm.tienda.ui.features.newuser.newuserpassword

sealed interface NewUserPasswordEvent {
    data class LoginChanged(val login: String) : NewUserPasswordEvent
    data class PasswordChanged(val password: String) : NewUserPasswordEvent
    object  onClickCrearCliente: NewUserPasswordEvent
}
