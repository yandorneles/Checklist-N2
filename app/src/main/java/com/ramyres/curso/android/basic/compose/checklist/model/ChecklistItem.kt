package com.ramyres.curso.android.basic.compose.checklist.model

data class ChecklistItem(
    val id: Int = 0,
    val descricao: String = "",
    var concluido: Boolean = false);



fun obterListaInicialDeItens(): List<ChecklistItem>{
    return listOf<ChecklistItem>(
        ChecklistItem(1, "Estudar para a prova de Gestão Mobile", true),
        ChecklistItem(2, "Ler livro sobre Inteligência Emocional", false),
        ChecklistItem(14, "Assistir trilogia Matrix", false),
        ChecklistItem(15, "Terminar site da Assembleia de Deus", false),
        ChecklistItem(16, "Estudar em casa", false),
        ChecklistItem(17,"Procurar os conteúdos das disciplinas do próximo semestre e iniciar meus estudos para que me saia melhor e que tenho maior proveito das disciplinas",false)
    )
}