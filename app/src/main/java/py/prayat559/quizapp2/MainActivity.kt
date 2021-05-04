package py.prayat559.quizapp2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.quizapp2.R


class MainActivity : AppCompatActivity() {
    lateinit var start_btn:Button
    lateinit var et_name:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        start_btn = findViewById(R.id.startBtn)
        et_name = findViewById(R.id.et_name)

        start_btn.setOnClickListener {

            if(et_name.text.toString().isEmpty()){
                Toast.makeText(this,"Please Enter Your Name!!.",Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME,et_name.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}