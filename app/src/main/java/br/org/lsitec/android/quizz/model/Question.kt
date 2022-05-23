package br.org.lsitec.android.quizz.model

class Question(
    val id: String,
    val category: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val tags: List<String>,
    val type: String,
    val difficulty: String
) {
    override fun toString(): String {
        return "$question correctAnswer: $correctAnswer"
    }
}