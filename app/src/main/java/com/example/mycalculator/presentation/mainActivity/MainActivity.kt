package com.example.mycalculator.presentation.mainActivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.mycalculator.R
import com.example.mycalculator.databinding.ActivityMainBinding
import com.example.mycalculator.domain.FromTaskActivityToMainActivity
import com.example.mycalculator.domain.helpers.ArithmeticOperationsEnum
import com.example.mycalculator.domain.helpers.Buttons
import com.example.mycalculator.domain.helpers.ButtonsWithString
import com.example.mycalculator.domain.helpers.NumbersEnum
import com.example.mycalculator.domain.helpers.OperationsEnum
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), FromTaskActivityToMainActivity {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var _viewModel : MainViewModel? = null
    private val viewModel get() = _viewModel!!

    private var resultText = ""
    private var mainText = ""

    companion object {
        const val TAG = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        _viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContentView(binding.root)

        setObservers()
        setListeners()
        openFrag(R.id.frameLayout_activity_main, MiniButtonsFragment())
    }


    private fun setObservers() {
        binding.textViewResultMainActivity.text = "0"
        binding.textView1MainActivity.text = "0.0"

        viewModel.mainText.observe(this) {
            viewModel.filterItems("None")//usual
        }


        viewModel.showMainText.observe(this
        ) { list ->

            mainText = list.toString()
            binding.textViewResultMainActivity.text = mainText

            resultText = showResult(list.toString())
            binding.textView1MainActivity.text = resultText

            Log.d(TAG, "string = $mainText")
            Log.d(TAG, "result = $resultText")
        }

        viewModel.showMainText.observe(this) {list ->
            if(list == "") {
                binding.textViewResultMainActivity.text = "0"
            }
        }
    }

    private fun setListeners() {
        binding.toolBarActivityMain.view1Toolbar1.setOnClickListener {
            TODO()
        }

        binding.toolBarActivityMain.view2Toolbar1.setOnClickListener {
            TODO()
        }
    }




    override fun communicate(button : Buttons) {
        when (button) {
            is OperationsEnum ->  {
                operationsUI(button)
            }
            is NumbersEnum -> {
                viewModel.insert(button.codeString())
            }
            is ArithmeticOperationsEnum -> {
                viewModel.insert(button.codeString())
            }
            is ButtonsWithString ->  {
                TODO()
            }
        }
    }

    private fun operationsUI(operation : OperationsEnum){
        when(operation){
            OperationsEnum.CloseHistory -> {
                Toast.makeText(this,"Close History",Toast.LENGTH_SHORT).show()
            }

            OperationsEnum.Equality -> {
                val value = resultText
                viewModel.deleteAll()
                viewModel.setMainText(value)
            }

            OperationsEnum.RemoveLast -> {
                viewModel.deleteLast()
            }

            OperationsEnum.DeleteAll -> {
                viewModel.deleteAll()
            }

        }
    }





    private fun showResult(string: String) : String{
        var mainResult = ""
        mainResult = try {
            val expression = getInputExpression(string)
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Show Error Message
                "0.0"
            } else {
                // Show Result
                DecimalFormat("0.######").format(result).toString()
            }
        } catch (e: Exception) {
            // Show Error Message
            Log.d(TAG, "string = Error")
            "Error"
        }
        return getOutputExpression(mainResult)
    }

    private fun getInputExpression(string : String): String {
        var expression = string.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun getOutputExpression(string : String): String {
        return string.replace(Regex(","), ".")
    }


    private fun openFrag( container : Int, fragment : Fragment){
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}




