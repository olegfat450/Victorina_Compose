package com.example.victorina_compose.data

data class Question(
    val amount: Int,
    val `data`: List<QuestionData>,
    val ok: Boolean
)

data class QuestionData(
    val answers: List<String>,
    val id: Int,
    val question: String
)