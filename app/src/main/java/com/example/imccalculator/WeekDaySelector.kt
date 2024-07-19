package com.example.imccalculator

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.imccalculator.R
import java.util.Calendar

class WeekDaySelector @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var selectedDays = mutableListOf<Int>()
    private val daysOfWeek = context.resources.getStringArray(R.array.days_of_week_short)
    private val today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        setupDays()
    }

    private fun setupDays() {
        daysOfWeek.forEachIndexed { index, day ->
            val textView = TextView(context).apply {
                text = day
                tag = index
                setOnClickListener { toggleDaySelection(it.tag as Int) }
                // Customize your TextView appearance here
                if (index + 1 == today) { // Calendar.DAY_OF_WEEK starts from 1 (Sunday) to 7 (Saturday)
                    setBackgroundColor(ContextCompat.getColor(context, R.color.backgorund_component_selected))
                }
            }
            addView(textView)
        }
    }

    private fun toggleDaySelection(dayIndex: Int) {
        if (selectedDays.contains(dayIndex)) {
            selectedDays.remove(dayIndex)
            getChildAt(dayIndex)?.isSelected = false
        } else {
            selectedDays.add(dayIndex)
            getChildAt(dayIndex)?.isSelected = true
        }
        // Notify selection change if needed
    }
}