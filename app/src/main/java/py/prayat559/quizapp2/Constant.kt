package py.prayat559.quizapp2

import com.example.quizapp2.R

object Constants{

    const val USER_NAME:String = "user_name"
    const val TOTAL_QUESTION:String = "total_question"
    const val CORRECT_ANSWER:String = "correct_answer"


    fun getAllQuestions():ArrayList<Question>{
        val questionList = ArrayList<Question>()
        val que1 = Question(1,"what is it in image?",
                "Argentina",
                "car",
                "milk",
                "table",
                R.drawable.car,2)
        questionList.add(que1)

        val que2 = Question(1,"what is it in image?",
                "fruit",
                "medicine",
                "cat",
                "dog",
                R.drawable.cat,3)
        questionList.add(que2)


        val que3 = Question(1,"what is it in image?",
                "cat",
                "dog",
                "table",
                "milk",
                R.drawable.dog,2)
        questionList.add(que3)

        val que4 = Question(1,"what is it in image?",
                "tree",
                "india",
                "Amenia",
                "America",
                R.drawable.tree,1)
        questionList.add(que4)

        val que5 = Question(1,"what is it in image?",
            "tree",
            "india",
            "road",
            "cat",
            R.drawable.road,3)
        questionList.add(que5)

        return questionList
    }
}