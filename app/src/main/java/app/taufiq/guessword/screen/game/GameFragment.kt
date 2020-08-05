package app.taufiq.guessword.screen.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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

        updateScore()
        updateWord()

        return binding.root
    }


    // method for buttons preset
    private fun onSkip() {
        viewModel.onSkip()
        updateWord()
        updateScore()
    }

    private fun onCorrect() {
        viewModel.onCorrect()
        updateScore()
        updateWord()
    }


    private fun onEndGame(){
        onGameFinished()
    }


    private fun onGameFinished(){
        Toast.makeText(activity,"Game has just finished", Toast.LENGTH_SHORT).show()
        val action =
            GameFragmentDirections.actionGameFragmentToScoreFragment()
         action.score = viewModel.score
        NavHostFragment.findNavController(this).navigate(action)
    }


private fun updateScore() {
    binding.scoreText.text = viewModel.score.toString()
}

private fun updateWord() {
    binding.wordText.text = viewModel.word
}


}