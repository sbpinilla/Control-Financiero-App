package com.sergiodev.financeControl.financialRecord.ui.model

data class CheckKeysModel(val name: String, val isCheck: Boolean = false) {

    companion object {

        fun generateCheckKeysList():List<CheckKeysModel>{

            return listOf(
                CheckKeysModel("Nomina"),
                CheckKeysModel("Comida"),
                CheckKeysModel("Carro"),
                CheckKeysModel("Moto"),
                CheckKeysModel("Prestamo"),
                CheckKeysModel("Opto"),
                CheckKeysModel("Libros"),
                CheckKeysModel("Cursos"),
                CheckKeysModel("Pc"),
                CheckKeysModel("Mac"),
                CheckKeysModel("Servicios"),
                CheckKeysModel("Casa"),
                CheckKeysModel("Otros")
            )

        }

    }

}

