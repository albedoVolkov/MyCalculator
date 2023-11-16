package com.example.mycalculator.presentation.mainActivity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycalculator.databinding.MiniButtonsFragmentBinding
import com.example.mycalculator.domain.FromTaskActivityToMainActivity
import com.example.mycalculator.domain.helpers.operationsEnum.ArithmeticOperationsEnum
import com.example.mycalculator.domain.helpers.operationsEnum.NumbersEnum
import com.example.mycalculator.domain.helpers.operationsEnum.OperationsEnum


class MiniButtonsFragment : Fragment() {

    private var _binding: MiniButtonsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MiniButtonsFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //OperationsEnum
        binding.view11.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    OperationsEnum.CloseHistory
                )
        }

        binding.view12.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    OperationsEnum.DeleteAll
                )
        }

        binding.view13.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    OperationsEnum.RemoveLast
                )
        }

        binding.view54.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    OperationsEnum.Equality
                )
        }



        //ArithmeticOperationsEnum
        binding.view51.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    ArithmeticOperationsEnum.Percentages
                )
        }

        binding.view14.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    ArithmeticOperationsEnum.Divide
                )
        }

        binding.view24.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    ArithmeticOperationsEnum.Multiplication
                )
        }

        binding.view34.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    ArithmeticOperationsEnum.Minus
                )
        }

        binding.view44.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    ArithmeticOperationsEnum.Plus
                )
        }

        binding.view53.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    ArithmeticOperationsEnum.Dote
                )
        }



        //NumbersEnum
        binding.view21.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    NumbersEnum.Seven
                )
        }

        binding.view22.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    NumbersEnum.Eight
                )
        }

        binding.view23.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    NumbersEnum.Nine
                )
        }

        binding.view31.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    NumbersEnum.Four
                )
        }

        binding.view32.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    NumbersEnum.Five
                )
        }

        binding.view33.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    NumbersEnum.Six
                )
        }

        binding.view41.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    NumbersEnum.One
                )
        }

        binding.view42.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    NumbersEnum.Two
                )
        }

        binding.view43.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    NumbersEnum.Three
                )
        }

        binding.view52.setOnClickListener {
            (activity as FromTaskActivityToMainActivity)
                .communicate(
                    NumbersEnum.Zero
                )
        }

    }


    private fun closeFragment() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
            ?: throw Exception("transaction in TaskFragment is null")
        transaction.remove(this)
        transaction.commit()
    }
}
