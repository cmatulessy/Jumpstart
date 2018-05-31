package com.carlomatulessy.jumpstart

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var timer : CountDownTimer
    private var isTimerRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        chronometer.setOnClickListener { v ->
            //chronometer.visibility = View.INVISIBLE
            //lightImageView.visibility = View.VISIBLE
        }

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
    }

    private fun createNewRandomLightsOutTimer() : CountDownTimer{
         return object : CountDownTimer(Math.random().toLong(), 1000) {
            override fun onFinish() {
                lightImageView.setImageResource(R.drawable.lights)
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.start()
            }

            override fun onTick(millisUntilFinished: Long) {
                // just wait
            }
        }
    }

    private fun stopTimerAndShowTime() {
        chronometer.stop()
        //chronometer.visibility = View.VISIBLE
        //lightImageView.visibility = View.INVISIBLE
    }

}
