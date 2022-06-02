package br.org.lsitec.android.quizz.model

class QuizMetadata(
    val byCategory: Map<String, Int>
) {
    val getCategories get() = byCategory.keys.toList()
}