package com.example.mycalculator.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycalculator.R
import com.example.mycalculator.databinding.ActivityMainBinding
import com.example.mycalculator.domain.helpers.ArithmeticOperationsEnum
import com.example.mycalculator.domain.helpers.Buttons
import com.example.mycalculator.domain.helpers.ButtonsWithString
import com.example.mycalculator.domain.helpers.Item
import com.example.mycalculator.domain.helpers.NumbersEnum
import com.example.mycalculator.domain.helpers.OperationsEnum
import java.util.LinkedList
import java.util.Stack
import kotlin.math.pow
import kotlin.math.sign

class MainActivity : AppCompatActivity(),FromTaskActivityToMainActivity,ItemAdapter.ItemClickListener {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var _viewModel : MainViewModel? = null
    private val viewModel get() = _viewModel!!
    private var _itemAdapter : ItemAdapter? = null
    private val itemAdapter get() = _itemAdapter!!

    private var resultText = ""
    private var result = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        _viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        _itemAdapter = ItemAdapter(this)

        setContentView(binding.root)

        init()
        initUI()

        openFrag(R.id.frameLayout_activity_main,MiniButtonsFragment())
    }


    private fun init() {
        valueZero(listOf())
        binding.recyclerView1MainActivity.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.recyclerView1MainActivity.adapter = itemAdapter

        viewModel.getList().observe(this
        ) { list ->
            valueZero(list)
            itemAdapter.setList(list.toList())
            makeListString(list)
            binding.textView1MainActivity.text = getResult(makeListString(list)).toString()
            Log.d("Log_App", "showing list now = $list.toString()")
        }
    }


    private fun initUI() {
        binding.toolBarActivityMain.view1Toolbar1.setOnClickListener {
            TODO()
        }

        binding.toolBarActivityMain.view2Toolbar1.setOnClickListener {
            TODO()
        }
    }


    private fun openFrag( container : Int, fragment : Fragment){
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun communicate(b : Buttons) {
        when (b) {
            is OperationsEnum ->  {
                operationsUI(b)
            }
            is ButtonsWithString ->  {
                viewModel.insert(Item(action = b))
            }
        }
    }

    override fun onItemClick(id: Long, itemView: View) {
        val item : Item = viewModel.getItemById(id)
        viewModel.changeIsPressed(item, (!item.isPressed))
    }


    private fun operationsUI(operation : OperationsEnum){
        when(operation){
            OperationsEnum.CloseHistory -> {
            }

            OperationsEnum.Equality -> {
                val result = getResult(makeListString(viewModel.getList().value!!)).toString()
                viewModel.deleteAll()
                for(i in result.toCharArray()){
                    when(i){
                        '0' -> viewModel.insert(Item(action = NumbersEnum.Zero))
                        '1' -> viewModel.insert(Item(action = NumbersEnum.One))
                        '2' -> viewModel.insert(Item(action = NumbersEnum.Two))
                        '3' -> viewModel.insert(Item(action = NumbersEnum.Three))
                        '4' -> viewModel.insert(Item(action = NumbersEnum.Four))
                        '5' -> viewModel.insert(Item(action = NumbersEnum.Five))
                        '6' -> viewModel.insert(Item(action = NumbersEnum.Six))
                        '7' -> viewModel.insert(Item(action = NumbersEnum.Seven))
                        '8' -> viewModel.insert(Item(action = NumbersEnum.Eight))
                        '9' -> viewModel.insert(Item(action = NumbersEnum.Six))
                        '-' -> viewModel.insert(Item(action = ArithmeticOperationsEnum.Minus))
                        '.' -> viewModel.insert(Item(action = ArithmeticOperationsEnum.Dote))
                    }
                }
            }

            OperationsEnum.Remove -> {
                viewModel.deleteLast()
            }

            OperationsEnum.DeleteAll -> {
                viewModel.deleteAll()
            }

        }
    }
    private fun makeListString(list : List<Item>) : String
    {
        val listReversed = list.reversed()
        var s = ""
        for (item in listReversed) {
            s += item.action.codeString()
        }
        return s
    }

    private fun getResult(s : String) : Double {

            val stackNumber = Stack<Double>()
            val chars = s.toCharArray()
            var sign: Char? = null
            var i = -1
            var num = 0.0
            var isDote = false
            while (++i < chars.size) {
                val letter = chars[i]
                if (letter.isDigit()) {
                    var mult = 10.0
                    num = Character.getNumericValue(letter).toDouble()
                        while ((i + 1) < s.length && (chars[i + 1].isDigit() || chars[i + 1] == '.')) {
                            i++
                            mult *= 0.1
                            if(chars[i] == '.'){
                                isDote = true
                                continue
                            }
                            if(isDote){
                                num += Character.getNumericValue(chars[i]) * mult
                            }else {
                                num = num * 10 + Character.getNumericValue(chars[i])
                            }
                        }
                        isDote = false

                    when (sign) {
                        null -> stackNumber.push(num.toDouble())
                        '+' -> {stackNumber.push(num.toDouble()); sign = null}
                        '-' -> {stackNumber.push((-1 * num).toDouble()); sign = null}
                        '*' -> {stackNumber.push(num * stackNumber.pop()); sign = null}
                        '/' -> {stackNumber.push(stackNumber.pop() / num); sign = null}
                    }
                } else {
                    if (letter == '+' || letter == '-' || letter == '*' || letter == '/')
                        sign = letter
                }
            }

        return if (stackNumber.isEmpty()){
            0.toDouble()
        } else{
            String.format("%.6f", stackNumber.sum()).toDouble()
        }
    }


    private fun valueZero(list : List<Item>){
        if(list.isEmpty()){
            viewModel.insert(Item(action = NumbersEnum.Zero))
        }else{
            if(list.size > 1 && list[list.size-1].action == NumbersEnum.Zero){
                viewModel.deleteFirst()
            }
        }
    }

}




