package com.example.imccalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Range
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.updatePadding
import com.example.imccalculator.R.id.viewMale
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcCaculatorActivity : AppCompatActivity() {
    private var isMaleSelected: Boolean = true;
    private var isFemaleSelected: Boolean = false;
    private var currentWeight: Int = 60;
    private var currentAge: Int = 18;
    private var currentHeight: Int = 120;

    private lateinit var viewMale: CardView;
    private lateinit var viewFemale: CardView;
    private lateinit var tvHeight: TextView;
    private lateinit var rsHeight: RangeSlider;
    private lateinit var btnSubtractWeight: FloatingActionButton;
    private lateinit var btnPlusWeight: FloatingActionButton;
    private lateinit var tvWeight: TextView;
    private lateinit var btnSubtractAge: FloatingActionButton;
    private lateinit var btnPlusAge: FloatingActionButton;
    private lateinit var tvAge: TextView;
    private lateinit var btnCalculate: Button;

    companion object {
        const val IMC_KEY = "IMC_RESULT";
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc_caculator);
        initComponent();
        initListeners();
        initUI();
        findViewById<View>(R.id.main).apply {
            setOnApplyWindowInsetsListener { view, insets ->
                // Aplicar padding para respetar el área de la barra de estado y la barra de navegación
                view.updatePadding(top = insets.systemWindowInsetTop, bottom = insets.systemWindowInsetBottom)
                insets.consumeSystemWindowInsets()
            }
        }
    }


    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale);
        viewFemale = findViewById(R.id.viewFemale);
        tvHeight = findViewById(R.id.tvHeight);
        rsHeight = findViewById(R.id.rsHeight);
        btnSubtractWeight = findViewById(R.id.btnSubtracWeight);
        btnPlusWeight = findViewById(R.id.btnPlusWeight);
        btnSubtractAge = findViewById(R.id.btnSubtracAge);
        btnPlusAge = findViewById(R.id.btnPlusAge);
        tvWeight = findViewById(R.id.tvWeight);
        tvAge = findViewById(R.id.tvAge);
        btnCalculate = findViewById(R.id.btnCalculate);


    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender(isMale = true)
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender(isMale = false)
            setGenderColor()
        }

        rsHeight.addOnChangeListener { _, value, _ ->

            val df = DecimalFormat("#.##");
            currentHeight = df.format(value).toInt();
            tvHeight.text = "$currentHeight cm";


        }

        btnPlusWeight.setOnClickListener {
            currentWeight += 1;
            setWeight()
        }
        btnSubtractWeight.setOnClickListener {
            currentWeight -= 1;
            setWeight()
        }
        btnPlusAge.setOnClickListener {
            currentAge += 1;
            setAge()
        }
        btnSubtractAge.setOnClickListener {
            currentAge -= 1;
            setAge()
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java);
        intent.putExtra(IMC_KEY, result);
        startActivity(intent);
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##");
        val imc = currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100);
        return df.format(imc).toDouble();
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun changeGender(isMale: Boolean) {
        if (isMale) {
            isMaleSelected = true
            isFemaleSelected = false
        } else {
            isMaleSelected = false
            isFemaleSelected = true
        }
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
        setWeight();
        setAge();

    }

}


