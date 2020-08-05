package app.taufiq.guessword.screen.game

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * Created By Taufiq on 8/5/2020.
 *
 */
class GameViewModel : ViewModel() {

    // current word
    var word = ""

    // current score
    var score = 0

    // the list of word - the front of the list is the next word  to guess
    lateinit var listOfWord: MutableList<String>

    init {
        resetLists()
        nextWord()

    }


    // method for buttons preset
    fun onSkip() {
        score--
        nextWord()

    }

    fun onCorrect() {
        score++
        nextWord()
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }


    // list of the words
    private fun resetLists() {
        listOfWord = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        listOfWord.shuffle()
    }

    /*
    * Method for the next word
    * */
    private fun nextWord() {
        if (!listOfWord.isEmpty()) {
            // select and remove a word from the list
            word = listOfWord.removeAt(0)
        }
    }
}