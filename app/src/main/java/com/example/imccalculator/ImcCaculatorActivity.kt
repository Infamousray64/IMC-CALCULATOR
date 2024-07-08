package com.example.imccalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Range
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.slider.RangeSlider

class ImcCaculatorActivity : AppCompatActivity() {
    private var isMaleSelected: Boolean = true;
    private var isFemaleSelected: Boolean = false;

    private lateinit var viewMale: CardView;
    private lateinit var viewFemale: CardView;
    private lateinit var tvHeight: TextView;
    private lateinit var rsHeight: RangeSlider;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc_caculator);
        initComponent();
        initListeners();
        initUI();
    }

    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale);
        viewFemale = findViewById(R.id.viewFemale);
        tvHeight = findViewById(R.id.tvHeight);
        rsHeight = findViewById(R.id.rsHeight);

    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender();
            setGenderColor();
        }
        viewFemale.setOnClickListener {
            changeGender();
            setGenderColor();

        }

        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##");
            val result = df.format(value);
            tvHeight.text = "$result cm";

        }

    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected;
        isFemaleSelected = !isFemaleSelected;

    }

    private fun setGenderColor() {

        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected));
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected));


    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {

        val colorReference = if (isSelectedComponent) {
            R.color.backgorund_component_selected;
        } else {
            R.color.background_component;
        }
        return ContextCompat.getColor(this, colorReference);
    }

    private fun initUI() {
        setGenderColor();

    }

}


