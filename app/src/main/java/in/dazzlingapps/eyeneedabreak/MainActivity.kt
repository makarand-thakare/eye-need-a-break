package `in`.dazzlingapps.eyeneedabreak

import `in`.dazzlingapps.eyeneedabreak.utils.AppConstants
import android.app.TimePickerDialog
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var tvWorkTime: TextView
    private lateinit var tvBreakTime: TextView
    private lateinit var btnStartTimer: Button

    private val TAG = "MainActivityTe"

    // todo create mutable live data for it
    private var noHoursToWork: Int = 1
    private var noMinutesToWork: Int = 0

    private var noHoursForBreak: Int = 0
    private var noMinutesForBreak: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvWorkTime = findViewById(R.id.tvWorkTime)
        tvBreakTime = findViewById(R.id.tvBreakTime)
        btnStartTimer = findViewById(R.id.btnStartTimer)
        tvWorkTime.setOnClickListener {

            openTimePickerDialog(AppConstants.WORK_TIME)
        }
        tvBreakTime.setOnClickListener {
            openTimePickerDialog(AppConstants.BREAK_TIME)

        }
        btnStartTimer.setOnClickListener {
            startTimer(noHoursToWork, noMinutesToWork)
        }
    }

    private fun startTimer(hour: Int, minutes: Int) {

//        val hoursToMilliSec: Long = (hour * 60 * 60 * 1000).toLong()
//        val minutesToMilliSec: Long = (minutes * 60 * 1000).toLong()
//        val totalTimeInMilliSec: Long = hoursToMilliSec + minutesToMilliSec

        val totalTimeInMilliSec: Long = 4 * 1000
        val countDownInterval: Long = 250
        var contdownTimer = object : CountDownTimer(totalTimeInMilliSec, countDownInterval) {
            override fun onTick(timeUntilFinish: Long) {
                Log.d(TAG, "onTick: " + timeUntilFinish)
            }

            override fun onFinish() {
                playSound()
            }

        }
        contdownTimer.start()
    }

    private fun playSound() {
        val mp = MediaPlayer.create(this@MainActivity, R.raw.alarmmorning)
        mp.start()
    }

    private fun openTimePickerDialog(setTimeFor: Int) {


        val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
            object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

                    if (setTimeFor == AppConstants.WORK_TIME) {
                        noHoursToWork = hourOfDay
                        noMinutesToWork = minute

                        tvWorkTime.text = "${hourOfDay}h ${minute}m"
                    } else if (setTimeFor == AppConstants.BREAK_TIME) {
                        noHoursForBreak = hourOfDay
                        noMinutesForBreak = minute

                        tvBreakTime.text = "${hourOfDay}h ${minute}m"
                    }
                }
            }

        val timePicker: TimePickerDialog = TimePickerDialog(
            this, timePickerDialogListener, 12, 10, true
        )

        // then after building the timepicker
        // dialog show the dialog to user
        timePicker.show()
    }


}