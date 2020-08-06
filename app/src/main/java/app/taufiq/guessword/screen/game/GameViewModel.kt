package app.taufiq.guessword.screen.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created By Taufiq on 8/5/2020.
 *
 */
class GameViewModel : ViewModel() {

    // current word
    val word = MutableLiveData<String>()

    // current score
    val score = MutableLiveData<Int>()

    // the list of word - the front of the list is the next word  to guess
    lateinit var listOfWord: MutableList<String>

    init {
        resetLists()
        nextWord()

        word.value = ""
        score.value = 0

    }


    // method for buttons preset
    fun onSkip() {
        score.value = (score.value)?.minus(1)
        nextWord()

    }

    fun onCorrect() {
        score.value = (score.value)?.plus(1)
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
            word.value = listOfWord.removeAt(0)
        }
    }
}