package com.example.victorina_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.victorina_compose.data.Repository
import com.example.victorina_compose.ui.theme.Victorina_ComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    val question = mutableStateOf("AAAAAAAAA")
    val answers = mutableStateListOf<String>("aaaaa","bbbbb","cccc","dddd")
    var answerOk = ""
    val backgroundColor = mutableStateOf(Color.LightGray)
    var flag = mutableStateOf(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repository = Repository()
        val scope = CoroutineScope(Dispatchers.IO)



        fun getQuestion() { scope.launch { val questionData = repository.getQuestion(); question.value = questionData.data[0].question;
         for (i in 0..3) { answers[i] = questionData.data[0].answers[i]}
            answerOk = answers[0]
            val s = Random.nextInt(3); val a = answers[s]; answers[s] = answers[0]; answers[s] = a
        }}

        setContent {
            Victorina_ComposeTheme {}
        getQuestion()

           Column(modifier = Modifier.fillMaxSize().padding(top = 200.dp), horizontalAlignment = Alignment.CenterHorizontally){

               Text(text = question.value, fontSize = 32.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(32.dp), textAlign = TextAlign.Center)


               LazyHorizontalGrid(rows = GridCells.Fixed(2), modifier = Modifier.padding(top = 200.dp)
                   .fillMaxWidth().fillMaxHeight(0.3f), horizontalArrangement = Arrangement.Absolute.SpaceAround) {
                   items(answers){

                      Row (modifier = Modifier.padding(12.dp)
                          .background(color =  if (!flag.value) { Color.LightGray} else  { if (it == answerOk) Color.Green else Color.Red } ).widthIn(100.dp)
                          .clickable(onClick = { flag.value = true }))


                      {  Text(text = it, fontSize = 24.sp) }

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








