package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class QuestionActivity : AppCompatActivity() {

    private var Name:String?=null
    private var score:Int=0

    private var currentPosition:Int=1
    private var questionList:ArrayList<QuestionData> ? = null
    private var selecedOption:Int=0

     private lateinit var opt1:TextView
     private lateinit var opt2:TextView
     private lateinit var opt3:TextView
     private lateinit var opt4:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setData.getQuestion()

        opt1 = findViewById(R.id.opt_1)
        opt2 = findViewById(R.id.opt_2)
        opt3 = findViewById(R.id.opt_3)
        opt4 = findViewById(R.id.opt_4)


        Name=intent.getStringExtra(setData.name)

        questionList=setData.getQuestion()

        setQuestion()


        opt1.setOnClickListener{

            selectedOptionStyle(opt1,1)
        }
        opt2.setOnClickListener{

            selectedOptionStyle(opt2,2)
        }
        opt3.setOnClickListener{

            selectedOptionStyle(opt3,3)
        }
        opt4.setOnClickListener{

            selectedOptionStyle(opt4,4)
        }

        var submit=findViewById<Button>(R.id.submit)
        submit.setOnClickListener {
            if(selecedOption!=0)
            {
                val question=questionList!![currentPosition-1]
                if(selecedOption!=question.correct_ans)
                {
                   setColor(selecedOption,R.drawable.wrong_question_option)
                }else{
                    score++;
                }
                setColor(question.correct_ans,R.drawable.correct_question_option)
                if(currentPosition==questionList!!.size)
                    submit.text="FINISH"
                else
                    submit.text="Go to Next"
            }else{
                currentPosition++
                when{
                    currentPosition<=questionList!!.size->{
                        setQuestion()
                    }
                    else->{
                       var intent= Intent(this,Result::class.java)
                        intent.putExtra(setData.name,Name.toString())
                        intent.putExtra(setData.score,score.toString())
                        intent.putExtra("total size",questionList!!.size.toString())

                        startActivity(intent)
                        finish()
                    }
                }
            }
            selecedOption=0
        }

    }

    fun setColor(opt:Int,color:Int){
        when(opt){
            1->{
                opt1.background=ContextCompat.getDrawable(this,color)
            }
            2->{
                opt2.background=ContextCompat.getDrawable(this,color)
            }
            3->{
                opt3.background=ContextCompat.getDrawable(this,color)
            }
            4->{
                opt4.background=ContextCompat.getDrawable(this,color)
            }
        }
    }


    fun setQuestion(){

        val question = questionList!![currentPosition-1]
        setOptionStyle()

        var progress_bar=findViewById<ProgressBar>(R.id.progress_bar)
        var progress_text=findViewById<TextView>(R.id.progress_text)
        var question_text=findViewById<TextView>(R.id.question_text)

        progress_bar.progress=currentPosition
        progress_bar.max=questionList!!.size
        progress_text.text="${currentPosition}"+"/"+"${questionList!!.size}"
        question_text.text=question.question
        opt1.text=question.option_one
        opt2.text=question.option_tw0
        opt3.text=question.option_three
        opt4.text=question.option_four

    }

    fun setOptionStyle(){
        var optionList:ArrayList<TextView> = arrayListOf()
        optionList.add(0,opt1)
        optionList.add(1,opt2)
        optionList.add(2,opt3)
        optionList.add(3,opt4)

        for(op in optionList)
        {
            op.setTextColor(Color.parseColor("#555151"))
            op.background=ContextCompat.getDrawable(this,R.drawable.question_option)
            op.typeface= Typeface.DEFAULT
        }
    }

    fun selectedOptionStyle(view:TextView,opt:Int){

        setOptionStyle()
        selecedOption=opt
        view.background=ContextCompat.getDrawable(this,R.drawable.selected_question_option)
        view.typeface= Typeface.DEFAULT_BOLD
        view.setTextColor(Color.parseColor("#000000"))

    }
}