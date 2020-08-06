package app.taufiq.guessword.screen.game

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created By Taufiq on 8/5/2020.
 *
 */
class GameViewModel : ViewModel() {


    // current word
    private val word = MutableLiveData<String>()
    val _word: LiveData<String>
        get() = word


    // current score
    private val score = MutableLiveData<Int>()
    val _score: LiveData<Int>
        get() = score

    // game finish triggered
    private val eventGameFinished = MutableLiveData<Boolean>()
    val _eventGameFinished: LiveData<Boolean>
        get() = eventGameFinished

    // timer
    private val timer: CountDownTimer


    // countdown time
    private val currentTime = MutableLiveData<Long>()
    val _currentTime: LiveData<Long>
        get() = currentTime



    // the list of word - the front of the list is the next word  to guess
    lateinit var listOfWord: MutableList<String>

    init {
        resetLists()
        nextWord()

        word.value = ""
        score.value = 0


        // Creates a timer which triggers the end of the game when it finishes
        timer = object: CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onFinish() {
                currentTime.value = DONE
                onGameFinish()

            }

            override fun onTick(millisUntilFinished: Long) {
                currentTime.value = millisUntilFinished/ ONE_SECOND
            }
        }

        timer.start()

    }


    // method for buttons preset
    fun onSkip() {
        score.value = (_score.value)?.minus(1)
        nextWord()

    }

    fun onCorrect() {
        score.value = (_score.value)?.plus(1)
        nextWord()
    }


    override fun onCleared() {
        super.onCleared()

        timer.cancel()

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
        if (listOfWord.isNotEmpty()) {
            // select and remove a word from the list
            word.value = listOfWord.removeAt(0)
        } else {
            // shuffle the word list.
            resetLists()
        }
    }


    // method for game finished.
    fun onGameFinish() {
        eventGameFinished.value = true
    }

    /** Method for the game completed event **/

    fun onGameFinishComplete() {
        eventGameFinished.value = false
    }


    // logic for timer
    companion object {
        // time when the game is over
        private const val DONE = 0L

        // countdown time interval
        private const val ONE_SECOND = 1_000L // 1 second

        // total timer of the game
        private const val COUNTDOWN_TIME = 60_000L // 60 Second


    }


}
