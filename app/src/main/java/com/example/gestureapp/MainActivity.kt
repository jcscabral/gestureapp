package com.example.gestureapp

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.gestureapp.data.DataSource
import com.example.gestureapp.model.BankService
import com.example.gestureapp.ui.theme.GestureAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            GestureAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}


@Composable
fun ProductItem(
    nameId: Int,
    imageVector: ImageVector,
    modifier: Modifier
){
    Row(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth()
    ){

        var numberOfTouches by remember { mutableIntStateOf(0) }
        var touches = numberOfTouches.toString()

        Column {
            Button(
                onClick = {
                    //Toast.makeText(context, "This is the way, bro!", Toast.LENGTH_LONG)
                    numberOfTouches++
                    Log.i("ONCLICK", numberOfTouches.toString())
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Icon(
                    imageVector,
                    modifier = Modifier.size(64.dp),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = nameId),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = touches,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductsList(
    listOfServices: List<BankService>,
    modifier: Modifier = Modifier
){

    var actionType by remember { mutableStateOf("") }
    var pressureEvent by remember { mutableFloatStateOf(.0f) }
    var pressure = pressureEvent.toString()
    var pointerSizeEvent by remember { mutableFloatStateOf(.0f) }
    var pointerSize = pointerSizeEvent.toString()

    var posXEvent by remember { mutableFloatStateOf(.0f) }
    var posX = posXEvent.toString()
    var posYEvent by remember { mutableFloatStateOf(.0f) }
    var posY = posYEvent.toString()

    var tsTimeEvent by remember {mutableStateOf(0L)}
    var tsTime = tsTimeEvent.toString()


    var _actionType by remember { mutableStateOf("") }
    var _pressureEvent by remember { mutableFloatStateOf(.0f) }
    var _pressure = _pressureEvent.toString()
    var _pointerSizeEvent by remember { mutableFloatStateOf(.0f) }
    var _pointerSize = _pointerSizeEvent.toString()

    var _posXEvent by remember { mutableFloatStateOf(.0f) }
    var _posX = _posXEvent.toString()
    var _posYEvent by remember { mutableFloatStateOf(.0f) }
    var _posY = _posYEvent.toString()


    var _tsTimeEvent by remember {mutableStateOf(0L)}
    var _tsTime = _tsTimeEvent.toString()

    val filter: PointerEventType? = null


    Column(
        modifier = Modifier
            .pointerInteropFilter() {

                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        actionType = "DOWN"
                    }
                    MotionEvent.ACTION_UP -> {
                        actionType = "UP"
                    }
                    MotionEvent.ACTION_MOVE -> {
                        actionType = "MOVE"
                    }
                }
                Log.i("COLUMN", "ActionMove: ${MotionEvent.ACTION_MOVE}")
                pressureEvent = it.pressure
                pointerSizeEvent = it.size
                posXEvent = it.x
                posYEvent =  it.y
                tsTimeEvent = it.eventTime
                false

            }

    ) {
        Row(){
            Text(text = "Action: $actionType | $_actionType")
        }
        Spacer(modifier = Modifier.size(2.dp))
        Row(){
            Text(text = "Pressure: $pressure | $_pressure")

        }
        Spacer(modifier = Modifier.size(2.dp))
        Row(){
            Text(text = "PosX: $posX | $_posX")

        }
        Spacer(modifier = Modifier.size(2.dp))
        Row(){
            Text(text = "PosY: $posY  | $_posY")

        }
        Spacer(modifier = Modifier.size(2.dp))
        Row(){
            //Text(text = "Size: $pointerSize | dragX: $dragAmountX | dragY: $dragAmountY _Size: $_pointerSize | _dragX: $_dragAmountX | _dragY: $_dragAmountY" )
            Text(text = "Size: $pointerSize | $_pointerSize")
        }
        Spacer(modifier = Modifier.size(2.dp))
        Row(){
            //Text(text = "Size: $pointerSize | dragX: $dragAmountX | dragY: $dragAmountY _Size: $_pointerSize | _dragX: $_dragAmountX | _dragY: $_dragAmountY" )
            Text(text = "Time: $tsTime | $_tsTime")
        }

        LazyRow(
            modifier = Modifier
                .padding(8.dp)
                .pointerInput(filter) {

                    awaitPointerEventScope {
                        while (true) {
                            _pointerSizeEvent = size.toSize().height * size.toSize().width // Wrong!
                            val event = awaitPointerEvent()
                            // handle pointer event
                            //if (filter == null || event.type == filter) {

                            // TODO axisXY and Size of touch (from MotionEvent)

                            //val type = event.type
                            //val changes: List<PointerInputChange> = event.changes
                            val position = event
                                .component1()
                                .last().position
                            _actionType = event.type.toString()
                            _pressureEvent = event.changes.first().pressure
                            _posXEvent = position.x
                            _posYEvent = position.y
                            _tsTimeEvent = event.changes.first().uptimeMillis

                            //Log.i("FIRED","${event.type}, ${event.changes.first().position}")

//                            for (change in event.changes){
//                                Log.i("FIRED", "${change.position} | ${change.pressure}")
//                            }
                            //}
                        }
                    }
                }

        ){
            items(listOfServices){
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    ProductItem(
                        nameId = it.nameId,
                        imageVector = it.imageIcon ,
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Composable
fun SwipeServices(modifier: Modifier = Modifier) {
    ProductsList(
        listOfServices = DataSource().load()
    )
}


@Composable
fun PIXTransfer(modifier: Modifier =  Modifier){

    var pixKey by remember {mutableStateOf("")}
    val filter: PointerEventType? = null

    // events variables
    var actionType by remember { mutableStateOf("") }
    var pressureEvent by remember { mutableFloatStateOf(.0f) }
    var pressure = pressureEvent.toString()
    var pointerSizeEvent by remember { mutableFloatStateOf(.0f) }
    var pointerSize = pointerSizeEvent.toString()
    var tsTimeEvent by remember {mutableStateOf(0L)}
    var tsTime = tsTimeEvent.toString()

    //var eventKey by remember { mutableStateOf(0L) }
    //var keyspressed = eventKey.toString()
    var keyspressed by remember { mutableStateOf("") }


    Column(

    ){
        Spacer(modifier = Modifier.size(2.dp))
        Text(text = "ActionType: $actionType")
        Spacer(modifier = Modifier.size(2.dp))
        Text(text = "Pressure: $pressure")
        Spacer(modifier = Modifier.size(2.dp))
        Text(text = "Keys pressed: $keyspressed")
        Spacer(modifier = Modifier.size(2.dp))
        Text(text = stringResource(R.string.insert_pix_key),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        TextField(
            value = pixKey,
            label = {Text(stringResource(R.string.key_pix_label))},
            onValueChange = {pixKey = it} ,
            modifier = Modifier
                .pointerInput(filter) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            for (change in event.changes){
                                actionType = change.type.toString()
                                pressureEvent = change.pressure
                                tsTimeEvent = change.uptimeMillis
                            }
                        }
                    }
                }
                .onKeyEvent {
                    keyspressed = it.key.keyCode.toString()
                    false
                }
            ,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // can that affect the results?
        )
        Text(
            text = stringResource(R.string.recipient, pixKey) ,
        )
        Text(text = "TsTime: $tsTime")

    }

}

@Composable
fun MainApp() {
    Column {
        SwipeServices()
        Spacer(modifier = Modifier.height(16.dp))
        PIXTransfer()
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    GestureAppTheme {
        MainApp()
    }
}