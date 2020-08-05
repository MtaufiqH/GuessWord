package app.taufiq.guessword.screen.score

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * Created By Taufiq on 8/5/2020.
 *
 */
class ScoreViewModel(finalScore: Int) : ViewModel() {

    val score = finalScore

    init {
        Log.i("ScoreViewModel", "Final score is $finalScore")
    }
}