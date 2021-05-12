package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var del: Button
    private lateinit var clear:Button
    private var operand: Double = 0.0
    private var secOperand: Double = 0.0
    private var operation: String = ""
    var NewOp = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.resultTextView = findViewById(R.id.resultTextView)
        del = findViewById(R.id.del)
        del.setOnLongClickListener {
            resultTextView.text =""

            true
        }
        clear = findViewById(R.id.clear)
        clear.setOnClickListener{
            view-> val value = 0
            operand = 0.0
            secOperand = 0.0

            NewOp = false
            if(value == 0 ){
                resultTextView.text =""
            }
        }
    }

    fun numberClick(clickedView: View) {
        if (NewOp) {
            this.resultTextView.text = ""
            NewOp = false
        }

        if (clickedView is Button) {
            val num = clickedView.text.toString()
            var res = this.resultTextView.text.toString()
            if (res == "0") {
                res = ""
            }
            resultTextView.text = "$res$num"
        }
    }

    fun operationClick(view: View) {
        NewOp = true

        if (view is Button) {
            if (resultTextView.text.isNotEmpty()) {
                this.operand = resultTextView.text.toString().toDouble()
            }
            this.operation = view.text.toString()

        }
    }

    fun equalsClick(view: View) {
        val secOperandtext: String = this.resultTextView.text.toString()

        if (secOperandtext.isNotEmpty()) {

            secOperand = secOperandtext.toDouble()
        }

        this.resultTextView.text = operationfun(secOperand,this.operation)
        NewOp = false
    }




    fun operationfun(secOperand1: Double,operation1:String):String{
        var operationResult = 0.0
        when(operation1){
            "+" -> operationResult = this.operand + secOperand1
            "-" -> operationResult = this.operand - secOperand1
            "*" -> operationResult = this.operand * secOperand1
            "/" -> operationResult = this.operand / secOperand1
        }
        if (operationResult%1.0==0.0)
            return (operationResult.toInt()).toString()
        else return operationResult.toString()
    }

    fun backspace(view: View) {
        if (!resultTextView.text.toString().equals("")) {
            var str = resultTextView.text.toString()
            if (str.length > 0) {
                str = str.substring(0, str.length - 1)
                resultTextView.text = str
            }
        }

    }

   fun dot(view:View){
       if(!resultTextView.text.toString().contains(".")) {
           var txtView = resultTextView.text.toString()
           if (resultTextView.text.toString().equals("")) {
               resultTextView.text = "0."

           } else {
               resultTextView.text = "$txtView."
           }

      }
   }

}

