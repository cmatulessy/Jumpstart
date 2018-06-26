package com.carlomatulessy.jumpstart.Views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.carlomatulessy.jumpstart.R
import com.carlomatulessy.jumpstart.Stopwatch
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var timer : CountDownTimer
    private var isTimerRunning = false
    private lateinit var stopwatch: Stopwatch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopwatch = Stopwatch();

        timer = object : CountDownTimer(4500, 1000) {
            override fun onFinish() {
                createNewRandomLightsOutTimer().start()
            }

            override fun onTick(millisUntilFinished: Long) {
                var secondsRemaining = millisUntilFinished / 1000
                when (secondsRemaining.toInt()) {
                    1 -> lightImageView.setImageResource(R.drawable.lights_four)
                    2 -> lightImageView.setImageResource(R.drawable.lights_three)
                    3 -> lightImageView.setImageResource(R.drawable.lights_two)
                    4 -> lightImageView.setImageResource(R.drawable.lights_one)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        lightImageView.setImageResource(R.drawable.lights)
        lightImageView.setOnClickListener { v ->
            if(isTimerRunning) {
                stopTimerAndShowTime()
                isTimerRunning = false
            } else {
                timer.start()
                isTimerRunning = true
            }
        }

        timerTextView.setOnClickListener{
            timerTextView.visibility = View.INVISIBLE
            lightImageView.visibility = View.VISIBLE
        }
    }

    private fun createNewRandomLightsOutTimer() : CountDownTimer{
         return object : CountDownTimer(Math.random().toLong(), 1000) {
            override fun onFinish() {
                lightImageView.setImageResource(R.drawable.lights)
                stopwatch.start()
            }

            override fun onTick(millisUntilFinished: Long) {
                // just wait
            }
        }
    }

    private fun stopTimerAndShowTime() {
        stopwatch.stop()
        timerTextView.setText(StringBuilder().append(stopwatch.elapsedTime))
        timerTextView.visibility = View.VISIBLE
        lightImageView.visibility = View.INVISIBLE
    }

}
