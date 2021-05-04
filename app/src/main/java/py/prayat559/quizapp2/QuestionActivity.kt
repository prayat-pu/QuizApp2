package py.prayat559.quizapp2

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.quizapp2.R
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPosition:Int = 1
    lateinit private var mQuestionList:ArrayList<Question>
    lateinit private var mUserName:String
    private var mSelectedOptionPosition:Int = 0
    private var mCorrectAnswer:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        mQuestionList = Constants.getAllQuestions()
        mUserName = intent.getStringExtra(Constants.USER_NAME)!!
        setQuestion()

        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)
        submitBtn.setOnClickListener(this)

    }

    private fun setQuestion(){

        val question: Question? = mQuestionList[mCurrentPosition - 1]

        defaultOptionView()

        if(mCurrentPosition == mQuestionList!!.size){
            submitBtn.text = "FINISH"
        }else{
            submitBtn.text = "SUBMIT"
        }

        tv_question.text = question!!.question
        iv_question.setImageResource(question.image)
        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition"+"/"+progressBar.max
        option1.text = question.option1
        option2.text = question.option2
        option3.text = question.option3
        option4.text = question.option4
    }

    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        options.add(0,option1)
        options.add(1,option2)
        options.add(2,option3)
        options.add(3,option4)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.option1 ->{ selectedOptionView(option1,1) }
            R.id.option2 ->{ selectedOptionView(option2,2) }
            R.id.option3 ->{ selectedOptionView(option3,3) }
            R.id.option4 ->{ selectedOptionView(option4,4) }
            R.id.submitBtn ->{
                if(mSelectedOptionPosition==0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition<=mQuestionList!!.size->{setQuestion()
                        }else->{
                            val intent = Intent(this, resultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWER,mCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTION,mQuestionList.size)
                            startActivity(intent)
                        }
                    }
                }else{
                    val question = mQuestionList.get(mCurrentPosition-1)

                    if(mSelectedOptionPosition != question!!.correctAnswer){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border)
                    }else{mCorrectAnswer++}
                    answerView(question.correctAnswer, R.drawable.correct_option_border)

                    if(mCurrentPosition == mQuestionList!!.size){
                        submitBtn.text = "FINISH"
                    }else{
                        submitBtn.text = "Next Question"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer:Int,drawableView: Int){
        when(answer){
            1->{option1.background = ContextCompat.getDrawable(this,drawableView)}
            2->{option2.background = ContextCompat.getDrawable(this,drawableView)}
            3->{option3.background = ContextCompat.getDrawable(this,drawableView)}
            4->{option4.background = ContextCompat.getDrawable(this,drawableView)}
        }
    }

    private fun selectedOptionView(tv:TextView,selectedOptionNum:Int){
        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border)
    }


}