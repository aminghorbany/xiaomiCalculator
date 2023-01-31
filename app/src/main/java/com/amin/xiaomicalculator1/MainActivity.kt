package com.amin.xiaomicalculator1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.amin.xiaomicalculator1.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumberClicked()
        onOperatorClicked()

    }
    private fun onNumberClicked() {
        binding.btn0.setOnClickListener {
            if (binding.tvCalculate.text.isNotEmpty()){
                append("0")
            }
        }
        binding.btn1.setOnClickListener {
            append("1")
        }
        binding.btn2.setOnClickListener {
            append("2")
        }
        binding.btn3.setOnClickListener {
            append("3")
        }
        binding.btn4.setOnClickListener {
            append("4")
        }
        binding.btn5.setOnClickListener {
            append("5")
        }
        binding.btn6.setOnClickListener {
            append("6")
        }
        binding.btn7.setOnClickListener {
            append("7")
        }
        binding.btn8.setOnClickListener {
            append("8")
        }
        binding.btn9.setOnClickListener {
            append("9")
        }
        binding.btnDat.setOnClickListener {
            if (binding.tvCalculate.text.isEmpty()){
                append("0.")
            } else if (!binding.tvCalculate.text.contains(".")){
                append(".")
            }
        }


    }

    private fun onOperatorClicked() {
        binding.btnAC.setOnClickListener {
            binding.tvCalculate.text = ""
            binding.tvFinalResult.text = ""
        }
        binding.btnSum.setOnClickListener {
            if (binding.tvCalculate.text.isNotEmpty()){
                val lastChar = binding.tvCalculate.text.last()
                if (lastChar != '+' && lastChar != '-' && lastChar != '*' && lastChar != '/') {
                    append("+")
                }
            }
        }
        binding.btnMinos.setOnClickListener {
            if (binding.tvCalculate.text.isNotEmpty()) {
                val lastChar = binding.tvCalculate.text.last()
                if (lastChar != '+' && lastChar != '-' && lastChar != '*' && lastChar != '/') {
                    append("-")
                }
            }
        }
        binding.btnMultiply.setOnClickListener {
            if (binding.tvCalculate.text.isNotEmpty()) {
                val lastChar = binding.tvCalculate.text.last()
                if (lastChar != '+' && lastChar != '-' && lastChar != '*' && lastChar != '/') {
                    append("*")
                }
            }
        }
        binding.btnDivide.setOnClickListener {
            if (binding.tvCalculate.text.isNotEmpty()) {
                val lastChar = binding.tvCalculate.text.last()
                if (lastChar != '+' && lastChar != '-' && lastChar != '*' && lastChar != '/') {
                    append("/")
                }
            }
        }
        binding.btnOpenParenthesis.setOnClickListener {
            append("(")
        }
        binding.btnCloseParenthesis.setOnClickListener {
            append(")")
        }
        binding.btnDelete.setOnClickListener {
            var oldText = binding.tvCalculate.text.toString()
            if (oldText.isNotEmpty()){
                binding.tvCalculate.text = oldText.substring(0 , oldText.length - 1)
            }
        }
        binding.btnShowResult.setOnClickListener {
            try {
                var expression = ExpressionBuilder(binding.tvCalculate.text.toString()).build()
                val result = expression.evaluate()

                val longResult = result.toLong()

                // 35.0 == 35
                if (result == longResult.toDouble()){
                    binding.tvFinalResult.text = result.toLong().toString()
                }else{
                    //ashari
                    binding.tvFinalResult.text = result.toString()
                }
                binding.tvCalculate.text = ""
            }catch (e:Exception){
                binding.tvFinalResult.text = ""
                binding.tvCalculate.text = ""
                Toast.makeText(this, "خطا!", Toast.LENGTH_SHORT).show()
            }
            

        }

    }

    private fun append(text:String){
        binding.tvCalculate.append(text)

        /**
         * move to last character
         */
        val viewTree :ViewTreeObserver = binding.hsvCalculate.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                binding.hsvCalculate.viewTreeObserver.removeOnGlobalLayoutListener(this)
                binding.hsvCalculate.scrollTo(binding.tvCalculate.width , 0)
            }

        })
    }

}