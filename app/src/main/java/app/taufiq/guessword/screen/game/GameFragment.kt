package app.taufiq.guessword.screen.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import app.taufiq.guessword.R
import app.taufiq.guessword.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    // current word
    private var word = " "

    // current score
    private var score = 0

    // the list of word - the front of the list is the next word  to guess
    private lateinit var listOfWord: MutableList<String>

    // get view
    private lateinit var binding: FragmentGameBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        resetLists()
        nextWord()

        binding.buttonSkip.setOnClickListener { onSkip() }
        binding.buttonGotIt.setOnClickListener { onCorrect() }

        updateScore()
        updateWord()

        return binding.root
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


    // method for buttons preset
    private fun onSkip() {
        score--
        nextWord()

    }

    private fun onCorrect() {
        score++
        nextWord()
    }


    /*
    * Method for the next word
    * */
    private fun nextWord() {
        if (!listOfWord.isEmpty()) {
            // select and remove a word from the list
            word = listOfWord.removeAt(0)
        }

        // give a new word
        updateWord()
        updateScore()
    }

    private fun updateScore() {
        binding.scoreText.text = score.toString()
    }

    private fun updateWord() {
        binding.wordText.text = word
    }


}