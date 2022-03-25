package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
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
        currentDiceEquation = DiceEquation(Dice(6), 2, 0)
        // Grab views/widgets
        val rollButton: Button = findViewById(R.id.button)
//        val diceImage: ImageView = findViewById(R.id.imageView)
        rollDice()
        // Bind Actions -----------------------
        rollButton.setOnClickListener { rollDice() }
    }

    /**
     * Rolls the dice and updates app state based on the result.
     */
    private fun rollDice() {
        // Previous steps, no longer needed
//            Toast.makeText(this, "Rolled!", Toast.LENGTH_SHORT).show()
//            diceImage.text = currentDiceEquation.roll().toString()

        // Grab image view
        val diceImage1: ImageView = findViewById(R.id.diceImage1)
        val diceImage2: ImageView = findViewById(R.id.diceImage2)
        val resultText: TextView = findViewById(R.id.resultText)

        // Get dice roll value
        val roll = currentDiceEquation.roll()

        // Translate dice roll into image
        diceImage1.setImageResource(when (currentDiceEquation.lastRolls[0]) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        })
        diceImage2.setImageResource(when (currentDiceEquation.lastRolls[1]) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        })

        // Sum up
        resultText.text = roll.toString()

        // Accessibility
        diceImage1.contentDescription = currentDiceEquation.lastRolls[0].toString()
        diceImage2.contentDescription = currentDiceEquation.lastRolls[1].toString()
    }
}

/**
 * Dice class for wrapping random functionality.
 */
class Dice(private val numSides: Int) {
    /**
     * Rolls the die and returns the result.
     */
    fun roll(): Int {
        return (1..numSides).random()
    }
}

/**
 * Dice Equation class for simulating more complex dice rolls (for tabletop games such as D&D).
 * Equation is <times>d<dice> + <modifier>.
 */
class DiceEquation(private val dice: Dice, private val times: Int, private val modifier: Int) {
    /**
     * Stores the results of the last roll in case you want the individual values or forgot to
     * cache the total result.
     */
    var lastRolls: Array<Int> = Array(times) { -1 }
        private set

    /**
     * Rolls the dice and sums it up. Caches the result.
     */
    fun roll(): Int {
        lastRolls = Array(times) { dice.roll() }
        return lastRolls.sum() + modifier
    }

    /**
     * Recalls the last dice roll.
     */
    fun lastRoll(): Int = lastRolls.sum() + modifier
}
