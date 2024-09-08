package com.aiinty.nmethods

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal


private const val TAG = "OperationsFragment"

class OperationsFragment : Fragment(), NumberClickDeleteInterface {

    private lateinit var numbersListViewModel: NumbersListViewModel
    private var adapter: NumbersRecyclerAdapter? = null
    private lateinit var numbersRecyclerView: RecyclerView
    private lateinit var addNumber: FloatingActionButton

    private lateinit var resultsText: TextView
    private lateinit var sumNumbers: Button
    private lateinit var subNumbers: Button
    private lateinit var mulNumbers: Button
    private lateinit var divNumbers: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        numbersListViewModel = ViewModelProvider(this).get(NumbersListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_operations, container, false)

        numbersRecyclerView = view.findViewById(R.id.numbers_list)
        numbersRecyclerView.layoutManager = LinearLayoutManager(context)

        addNumber = view.findViewById(R.id.add_number)
        addNumber.setOnClickListener { addButtonListener() }

        resultsText = view.findViewById(R.id.results_text)

        sumNumbers = view.findViewById(R.id.operation_sum)
        sumNumbers.setOnClickListener { sumButtonListener() }

        subNumbers = view.findViewById(R.id.operation_sub)
        subNumbers.setOnClickListener { subButtonListener() }

        mulNumbers = view.findViewById(R.id.operation_mul)
        mulNumbers.setOnClickListener { mulButtonListener() }

        divNumbers = view.findViewById(R.id.operation_div)
        divNumbers.setOnClickListener { divButtonListener() }

        updateUI()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun updateUI() {
        val numbers = numbersListViewModel.numbers
        adapter = NumbersRecyclerAdapter(numbers, this)
        numbersRecyclerView.adapter = adapter
    }

    private fun sumButtonListener() {
        if (adapter!!.itemCount > 1){
            val result = numbersListViewModel.sumNumbers()
            var text = "S = ${result.output} ≈ ${result.value}"
            resultsText.text = text
        }
    }

    private fun subButtonListener() {
        if (adapter!!.itemCount > 1){
            val result = numbersListViewModel.subNumbers()
            var text = "S = ${result.output} ≈ ${result.value}"
            resultsText.text = text
        }
    }

    private fun mulButtonListener() {
        if (adapter!!.itemCount > 1){
            val result = numbersListViewModel.mulNumbers()
            var text = "P = ${result.output} ≈ ${result.value}"
            resultsText.text = text
        }
    }

    private fun divButtonListener() {
        if (adapter!!.itemCount > 1){
            val result = numbersListViewModel.divNumbers()
            var text = "P = ${result.output} ≈ ${result.value}"
            resultsText.text = text
        }
    }

    private fun addButtonListener() {
        val layoutInflater = LayoutInflater.from(context)
        val promptUserView: View = layoutInflater.inflate(R.layout.dialog_input, null)

        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)

        alertDialogBuilder.setView(promptUserView)

        val userAnswer = promptUserView.findViewById<View>(R.id.number_input) as EditText

        alertDialogBuilder.setTitle("Введите число")

        alertDialogBuilder.setPositiveButton("Готово") { _, _ ->
            if (!userAnswer.text.toString().isNullOrEmpty()) {
                numbersListViewModel.addNumber(BigDecimal(userAnswer.text.toString().trim()))
                updateUI()
            }
        }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onDeleteIconClick(idx: Int) {
        numbersListViewModel.deleteNumber(idx)
        updateUI()
    }

    companion object {
        fun newInstance(): OperationsFragment {
            return OperationsFragment()
        }
    }
}