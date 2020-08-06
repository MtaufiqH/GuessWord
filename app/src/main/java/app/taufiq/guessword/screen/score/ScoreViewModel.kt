package app.taufiq.guessword.screen.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created By Taufiq on 8/5/2020.
 *
 */
class ScoreViewModel(finalScore: Int) : ViewModel() {

    // score value cannot to access direct
    private val score = MutableLiveData<Int>()
    // backing score Just Read not write
    val _score: LiveData<Int>
    get() = score

    private val eventPlayAgain = MutableLiveData<Boolean>()
    val _eventPlayAgain: LiveData<Boolean>
    get() = eventPlayAgain




    init {
        score.value = finalScore

    }


    //  methods to set and reset the event
    fun onPlayAgain() {
        eventPlayAgain.value = true
    }
    fun onPlayAgainComplete() {
        eventPlayAgain.value = false
    }


}