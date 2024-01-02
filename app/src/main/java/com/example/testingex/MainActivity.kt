package com.example.testingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.testingex.databinding.ActivityMainBinding
import com.example.testingex.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

     private val viewMode: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Используем основную привязку активности и передаем наш инфлятор макета.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // настроить рукоять кинжала чтобы мы могли фактически вводить зависимости, используя его

        val balance: TextView = findViewById(R.id.balance)


        binding.btn0.setOnClickListener{setTextField("0")}
        binding.btn1.setOnClickListener{setTextField("1")}
        binding.btn2.setOnClickListener{setTextField("2")}
        binding.btn3.setOnClickListener{setTextField("3")}
        binding.btn4.setOnClickListener{setTextField("4")}
        binding.btn5.setOnClickListener{setTextField("5")}
        binding.btn6.setOnClickListener{setTextField("6")}
        binding.btn7.setOnClickListener{setTextField("7")}
        binding.btn8.setOnClickListener{setTextField("8")}
        binding.btn9.setOnClickListener{setTextField("9")}

        binding.btnClear.setOnClickListener{
            val str = binding.etFrom.text.toString()
            if(str.isNotEmpty()){
                binding.etFrom.text = str.substring(0, str.length - 1)
            }
        }

        binding.submitBtn.setOnClickListener {
            viewMode.convert(
                binding.balance.text.toString(),
                binding.etFrom.text.toString(),
                binding.spSellCurrency.selectedItem.toString(),
                binding.spReceiveCurrency.selectedItem.toString()

            )
        }
        // Create a new coroutine since repeatOnLifecycle is a suspend function
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewMode.conversion.collect { event ->
                    when(event){
                        is MainViewModel.CurrencyEvent.Success -> {
                                binding.etTo.text = event.resultText
                        }
                        is MainViewModel.CurrencyEvent.Failure -> {
                            binding.etTo.text = event.errorText
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun setTextField (str: String){
        binding.etFrom.append(str)
    }
}