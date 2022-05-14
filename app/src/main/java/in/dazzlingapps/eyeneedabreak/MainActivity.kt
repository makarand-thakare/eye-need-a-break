package `in`.dazzlingapps.eyeneedabreak

import `in`.dazzlingapps.eyeneedabreak.utils.AppConstants
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var tvWorkTime: TextView
    private lateinit var tvBreakTime: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvWorkTime = findViewById(R.id.tvWorkTime)
        tvBreakTime = findViewById(R.id.tvBreakTime)
        tvWorkTime.setOnClickListener {

            openTimePickerDialog(AppConstants.WORK_TIME)
        }
        tvBreakTime.setOnClickListener {
            openTimePickerDialog(AppConstants.BREAK_TIME)

        }
    }

    private fun openTimePickerDialog(setTimeFor: Int) {


        val timePickerDialogListener: TimePickerDialog.OnTimeSetListener = object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                Toast.makeText(this@MainActivity, "$hourOfDay $minute", Toast.LENGTH_SHORT).show()
                if (setTimeFor == AppConstants.WORK_TIME){
                    tvWorkTime.text = "${hourOfDay}h ${minute}m"
                }else   if (setTimeFor == AppConstants.BREAK_TIME){
                    tvBreakTime.text = "${hourOfDay}h ${minute}m"
                }
            }
        }

        val timePicker: TimePickerDialog = TimePickerDialog(
            this, timePickerDialogListener, 12, 10, true)

        // then after building the timepicker
        // dialog show the dialog to user
        timePicker.show()
    }


}