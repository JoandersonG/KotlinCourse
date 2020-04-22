package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        val viewModel: BigDecimalViewModel by viewModels()
        viewModel.stringResult.observe(this, Observer<String> { stringResult -> result.setText(stringResult)})
        viewModel.stringNewNumber.observe(this, Observer<String> { stringNumber -> newNumber.setText(stringNumber)})
        viewModel.stringOperation.observe(this, Observer<String> { stringOperation -> operation.text = stringOperation})

        val listener = View.OnClickListener { v ->
            viewModel.digitPressed((v as Button).text.toString())
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        buttonNeg.setOnClickListener {
            viewModel.negPressed()
        }

        val opListener = View.OnClickListener { v ->
            viewModel.operandPressed((v as Button).text.toString())
        }

        buttonEquals.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)
    }
}
