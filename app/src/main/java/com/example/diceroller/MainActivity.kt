package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * The driver code for the dice roller app.
 */
class MainActivity : AppCompatActivity() {
    lateinit var currentDiceEquation: DiceEquation

    override fun onCreate(savedInstanceState: Bundle?) {
        // Boilerplate ------------------------
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize app state ---------------
        // Default dice equation
        currentDiceEquation = DiceEquation(Dice(6), 1, 0)
        // Grab views/widgets
        val rollButton: Button = findViewById(R.id.button)
        val resultText: TextView = findViewById(R.id.textView)
        // Bind Actions -----------------------
        rollButton.setOnClickListener {
            // Previous step, no longer needed
//            Toast.makeText(this, "Rolled!", Toast.LENGTH_SHORT).show()
            resultText.text = currentDiceEquation.roll().toString()
        }
    }
}

/**
 * Dice class for wrapping random functionality.
 */
class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}

/**
 * Dice Equation class for simulating more complex dice rolls (for tabletop games such as D&D).
 */
class DiceEquation(private val dice: Dice, private val times: Int, private val modifier: Int) {
    fun roll(): Int {
        return Array(times) { dice.roll() }.sum() + modifier
    }
}
