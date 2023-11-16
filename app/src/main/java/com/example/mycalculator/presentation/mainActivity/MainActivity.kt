package com.example.mycalculator.presentation.mainActivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycalculator.R
import com.example.mycalculator.data.ActionsRepositoryImpl
import com.example.mycalculator.databinding.ActivityMainBinding
import com.example.mycalculator.domain.ActionsRepository
import com.example.mycalculator.domain.FromTaskActivityToMainActivity
import com.example.mycalculator.domain.IdRepository
import com.example.mycalculator.domain.helpers.models.Action
import com.example.mycalculator.domain.helpers.operationsEnum.ArithmeticOperationsEnum
import com.example.mycalculator.domain.helpers.operationsEnum.basedClasses.Buttons
import com.example.mycalculator.domain.helpers.operationsEnum.basedClasses.ButtonsWithString
import com.example.mycalculator.domain.helpers.operationsEnum.NumbersEnum
import com.example.mycalculator.domain.helpers.operationsEnum.OperationsEnum
import com.example.mycalculator.presentation.mainActivity.fragments.MiniButtonsFragment
import org.mariuszgromada.math.mxparser.Expression
import java.math.BigDecimal
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), FromTaskActivityToMainActivity {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var _viewModel : MainViewModel? = null
    private val viewModel get() = _viewModel!!
    private var _viewModelFactory: MainViewModelFactory? = null

    private var _actionsAdapter: ActionsAdapter? = null
    private val actionsAdapter get() =  _actionsAdapter!!


    private var resultText : BigDecimal = BigDecimal(0)
    private var mainText = ""

    companion object {
        const val TAG = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        _viewModelFactory = MainViewModelFactory(IdRepository(), ActionsRepositoryImpl())
        _viewModel = ViewModelProvider(this, _viewModelFactory!!)[MainViewModel::class.java]

        setContentView(binding.root)

        setObservers()
        setListeners()
        openFrag(R.id.frameLayout_activity_main, MiniButtonsFragment())
    }


    private fun setObservers() {

        setActionsAdapter(viewModel.actions.value)

        binding.recyclerView2MainActivity.apply {
            val gridLayoutManager =
                GridLayoutManager(applicationContext, 1, LinearLayoutManager.VERTICAL, true)
            layoutManager = gridLayoutManager
            adapter = actionsAdapter
        }

        binding.textViewResultMainActivity.text = "0"
        binding.textView1MainActivity.text = "0.0"

        viewModel.mainText.observe(this) {
            viewModel.filterItems("None")//usual
        }


        viewModel.showMainText.observe(this
        ) { list ->

            mainText = list.toString()
            binding.textViewResultMainActivity.text = mainText

            val resultInner = showResult(list.toString())
            Log.d(TAG, "resultInner = $resultInner")
            resultText = BigDecimal(resultInner)
            binding.textView1MainActivity.text = resultText.toEngineeringString()

            Log.d(TAG, "string = $mainText")
            Log.d(TAG, "result = $resultText")
        }


        viewModel.showMainText.observe(this) {list ->
            if(list == "") {
                binding.textViewResultMainActivity.text = "0"
            }
        }

        viewModel.actions.observe(this
        ) { list ->
            if (list != null) {
                actionsAdapter.setData(list)
                actionsAdapter.notifyDataSetChanged()
                Log.d(TAG, "ActionsAdapter : $list")
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
                viewModel.deleteHistory()
            }

            OperationsEnum.Equality -> {
                if(mainText != "0" && mainText != "") {
                    val value = resultText
                    viewModel.addAction(mainText, resultText.toEngineeringString())
                    viewModel.deleteAll()
                    viewModel.setMainText(value.toString())
                }
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
            val result = Expression(string).calculate().toDouble()
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
        return mainResult.replace(Regex(","), ".")
    }


    private fun setActionsAdapter(itemList: List<Action>?) {
        _actionsAdapter = ActionsAdapter(this)
        _actionsAdapter!!.setData(listOf())
        actionsAdapter.onClickListener = object : ActionsAdapter.OnClickListener {

            override fun onClick(itemData: Action) {
                null
            }
        }
    }


    private fun openFrag( container : Int, fragment : Fragment){
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}




