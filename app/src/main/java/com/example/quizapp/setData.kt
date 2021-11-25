package com.example.quizapp

object setData {

    const val name:String="name"
    const val score:String="score"

   fun getQuestion():ArrayList<QuestionData>{
       var que:ArrayList<QuestionData> = arrayListOf()

       var question1 = QuestionData(
           1,
           "How Many version of Android  ?",

           "10",
           "13",
           "9",
           "12",
           4
       )
       var question2 = QuestionData(
           2,
           "Android is.. ?",

           "an operating system",
           "a web browser",
           "a search engine",
           "a web server",
           1
       )
       var question3 = QuestionData(
           3,
           "Android is based on which language ?",

           "c++",
           "java",
           "python",
           "None of the above",
           2
       )
       var question4 = QuestionData(
           4,
           "APK stand for... ?",

           "Application phone kit",
           "Application package key",
           "Application package kit",
           "None of the above",
           3
       )

       var question5 = QuestionData(
           5,
           "Which of the following converts Java byte code into Dalvik byte code? ?",

           "Dalvik converter",
           "Both A & D",
           "Nothing",
           "Dex compiler",
           4
       )

       que.add(question1)
       que.add(question2)
       que.add(question3)
       que.add(question4)
       que.add(question5)
       return que
   }
}