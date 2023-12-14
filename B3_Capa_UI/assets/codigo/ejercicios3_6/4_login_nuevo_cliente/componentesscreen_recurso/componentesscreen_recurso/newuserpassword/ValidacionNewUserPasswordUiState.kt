package com.pmdm.tienda.ui.features.newuser.newuserpassword

import com.pmdm.utilities.validacion.Validacion

data class ValidacionNewUserPasswordUiState(val validacionLogin: Validacion = Validacion(false),
                                            val validacionPassword: Validacion = Validacion(false),
                                            val validacionUsuario: Validacion = Validacion(false),
                                            val validacionCuentaCreada:Validacion= Validacion(false)
)
