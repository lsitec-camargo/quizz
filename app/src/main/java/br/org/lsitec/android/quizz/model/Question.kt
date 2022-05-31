package br.org.lsitec.android.quizz.model

class Question(
    val id: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
) {

    override fun toString(): String {
        return "Question(question='$question', correctAnswer='$correctAnswer', incorrectAnswers=$incorrectAnswers)"
    }

    fun shuffleAnswers(): List<String> {
        val answers: MutableList<String> = incorrectAnswers.toMutableList()
        answers.add(correctAnswer)
        answers.shuffle()
        return answers
    }

}