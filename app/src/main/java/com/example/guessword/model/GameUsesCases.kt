package com.example.guessword.model

class SubmitAnswerUseCase {
    operator fun invoke(userGuess: String, correctAnswer: String): Boolean {
        return userGuess.equals(correctAnswer, ignoreCase = true)
    }
}

class GetNextQuestionUseCase {
    operator fun invoke(currentIndex: Int, totalQuestions: Int): Int {
        return (currentIndex + 1) % totalQuestions
    }
}
