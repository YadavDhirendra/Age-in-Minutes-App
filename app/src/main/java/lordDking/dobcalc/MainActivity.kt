package lordDking.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvMinutes = findViewById(R.id.tvminutes)
        val btnDatepicker : Button = findViewById(R.id.btnDate)
        btnDatepicker.setOnClickListener {
            clickDatePicker()
        }
    }

    fun clickDatePicker(){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd =  DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,selectedyear,selectedmonth,selecteddayOfMonth ->


            val selectedDate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"

            tvSelectedDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateInMinutes  = currentDate.time / 60000

            val diffInMinutes = currentDateInMinutes - selectedDateInMinutes

            tvMinutes?.text = diffInMinutes.toString()


        },

            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}

