package app.taufiq.guessword.screen.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import app.taufiq.guessword.R
import app.taufiq.guessword.databinding.FragmentGameBinding


class GameFragment : Fragment() {


    // get view
    private lateinit var binding: FragmentGameBinding

    // instance GameViewModel
    private lateinit var viewModel: GameViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        // Initialize viewModel
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        binding.buttonSkip.setOnClickListener { onSkip() }
        binding.buttonGotIt.setOnClickListener { onCorrect() }
        binding.buttonEndGame.setOnClickListener { onEndGame() }


        /** Setting up LiveData observation relationship **/
        //update score text
        viewModel._score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })

        // update data word
        viewModel._word.observe(viewLifecycleOwner, Observer { newWord ->
            binding.wordText.text = newWord

        })

        // observer for the game Finish
        viewModel._eventGameFinished.observe(viewLifecycleOwner, Observer<Boolean> {hasFinished ->
            if (hasFinished) onGameFinished()
        })



        return binding.root
    }


    // method for buttons preset
    private fun onSkip() {
        viewModel.onSkip()
    }

    private fun onCorrect() {
        viewModel.onCorrect()
    }


    private fun onEndGame() {
        onGameFinished()
    }


    private fun onGameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val action =
            GameFragmentDirections.actionGameFragmentToScoreFragment()
        action.score = viewModel._score.value ?: 0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onGameFinishComplete()
    }


 /*   private fun updateScore() {
        binding.scoreText.text = viewModel.score.value.toString()
    }

    private fun updateWord() {
        binding.wordText.text = viewModel.word.value
    }*/


}