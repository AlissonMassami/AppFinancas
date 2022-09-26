package com.example.minhasfinancas.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.minhasfinancas.data.Setores


data class SetoresComContasExtras(
    @Embedded val setoresEntity: Setores,
    @Relation(
        parentColumn = "setorNome",
        entityColumn = "setorContaExtra"
    )
    val setoresComContasExtras: SetoresComContasExtras
)
