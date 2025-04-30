package com.example.victorina_compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.victorina_compose.data.Question
import com.example.victorina_compose.data.Repository
import com.example.victorina_compose.ui.theme.Victorina_ComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    val question = mutableStateOf("AAAAAAAAA")

    var answers = mutableStateListOf<Answers>()



    lateinit var answerOk: Answers
    val backgroundColor = mutableStateOf(Color.LightGray)
    var flag = mutableStateOf(false)
    var selectable = mutableStateOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repository = Repository()
        val scope = CoroutineScope(Dispatchers.IO)

          var questionData:Question? = null

        fun getQuestion() { scope.launch {

            try { questionData = repository.getQuestion() } catch (e:Exception) { question.value = e.message.toString()}

            questionData?.let {
                answers.clear()
                question.value = it.data[0].question
                for (i in 0..3) { answers += Answers ( it.data[0].answers[i], Color.LightGray) }

                answerOk = answers[0]; answers[0].apply { color = Color.Green }
                val s = Random.nextInt(0,3); val a = answers[s]; answers[s] = answers[0]; answers[0] = a
            }


        }

        }

        getQuestion()


        setContent {
            Victorina_ComposeTheme {}


           Column(modifier = Modifier.fillMaxSize().padding(top = 200.dp), horizontalAlignment = Alignment.CenterHorizontally){

               Text(text = question.value, fontSize = 32.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(32.dp).fillMaxHeight(0.4f), textAlign = TextAlign.Center)


               LazyHorizontalGrid(rows = GridCells.Fixed(2), userScrollEnabled = false, modifier = Modifier.padding(top = 80.dp)
                   .fillMaxWidth().fillMaxHeight(0.4f), horizontalArrangement = Arrangement.Absolute.SpaceAround) {
                   items(answers) {

                      Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(12.dp)
                          .background( color = if (flag.value) it.color else Color.LightGray, shape = RoundedCornerShape(8.dp))
                          .border(width = 4.dp, shape = RoundedCornerShape(8.dp), color = Color.Blue)
                          .widthIn(160.dp)

                          .clickable(onClick = { if (it != answerOk)  it.color = Color.Red

                              flag.value = true;
                            //  it.answer = "AAAAAA"
                       //  for (i in answers.indices) { if(answers[i].answer == it.answer) { answers[i] = answers[i].copy(color = Color.Red)} }

                        //  Toast.makeText(this@MainActivity,"${it.answer}",Toast.LENGTH_LONG).show()
                          

                          })

                        //  .clickable(onClick = { flag.value = true; if ((it.answer != answerOk.answer)) it.color = Color.Red  //; selectable.value = it.answer;

                         // answers.forEach { t ->  if ((it.answer != answerOk.answer)) it.color = Color.Red  }



                        //  })

                      )


                      {  Text(text = it.answer, fontSize = 24.sp, modifier = Modifier.padding(12.dp)) }

                   }


               }

               Text(text = "NEXT", modifier = Modifier.clickable { flag.value = false; getQuestion() })

//               Row(modifier = Modifier.fillMaxWidth().padding(top = 200.dp), horizontalArrangement = Arrangement.SpaceAround){
//                   Text(text = answers[0], fontSize = 24.sp)
//                   Text(text = answers[1], fontSize = 24.sp)
//               }
//
//               Row(modifier = Modifier.fillMaxWidth().padding(top = 52.dp), horizontalArrangement = Arrangement.SpaceAround){
//                   Text(text = answers[2], fontSize = 24.sp)
//                   Text(text = answers[3], fontSize = 24.sp)
//
//
//               }

           }













        }}}








