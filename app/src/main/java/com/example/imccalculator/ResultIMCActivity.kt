package com.example.imccalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updatePadding
import com.example.imccalculator.ImcCaculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView;
    private lateinit var tvDescription: TextView;
    private lateinit var tvIMC: TextView;
    private lateinit var btnRecalculate: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()

        findViewById<View>(R.id.main).apply {
            setOnApplyWindowInsetsListener { view, insets ->
                // Aplicar padding para respetar el área de la barra de estado y la barra de navegación
                view.updatePadding(top = insets.systemWindowInsetTop, bottom = insets.systemWindowInsetBottom)
                insets.consumeSystemWindowInsets()
                }
            }
        }

    private fun initListeners() {
        btnRecalculate.setOnClickListener {onBackPressed()}
        }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when(result){
            in 0.00..18.50 -> {
                tvResult.text = getString(R.string.title_bajo_peso);
                tvResult.setTextColor(getColor(R.color.peso_bajo));
                tvDescription.text = getString(R.string.description_bajo_peso);

            }
            in 18.51..24.99 -> {
                tvResult.text = getString(R.string.title_peso_normal);
                tvResult.setTextColor(getColor(R.color.peso_normal));
                tvDescription.text = getString(R.string.description_peso_normal);

            }
            in 25.00..29.99 -> {
                tvResult.text = getString(R.string.title_sobrepeso);
                tvResult.setTextColor(getColor(R.color.sobrepeso));
                tvDescription.text = getString(R.string.description_sobrepeso);

            }
            in 30.00..99.00 -> {
                tvResult.text = getString(R.string.title_obesidad);
                tvResult.setTextColor(getColor(R.color.obesidad));
                tvDescription.text = getString(R.string.description_obesidad);

            }
            else -> {
                tvIMC.text = getString(R.string.error);
                tvDescription.text = getString(R.string.error);
                tvResult.text = getString(R.string.error);
        }
    }
    }
    private fun initComponents() {
        tvIMC = findViewById(R.id.tvIMC);
        tvResult = findViewById(R.id.tvResult);
        tvDescription = findViewById(R.id.tvDescription);
        btnRecalculate = findViewById(R.id.btnRecalculate);
    }
}