package com.malkinfo.scientifccalcultor

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {

    private lateinit var previousCal: TextView
    private lateinit var display: EditText

    var isPortrait = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // XML에서 정의한 뷰들을 가져옴
        previousCal = findViewById(R.id.previCal)
        display = findViewById(R.id.display)
        display.showSoftInputOnFocus = false
    }

    // 텍스트 업데이트 메서드
    private fun updateText(strToAdd: String) {
        val oldStr = display.text.toString()
        val cursorPs = display.selectionStart
        val leftStr = oldStr.substring(0, cursorPs)
        val rightStr = oldStr.substring(cursorPs)
        display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr))
        display.setSelection(cursorPs + strToAdd.length)
    }

    // 숫자 및 기본 연산 버튼들에 대한 메서드
    // ...

    // 삼각함수 및 다른 수학 함수들에 대한 메서드
    // ...

    // "=" 버튼 클릭 시 호출되는 메서드
    fun equalsBtn(v: View) {
        var userExp = display.text.toString()
        previousCal.text = userExp
        userExp = userExp.replace(resources.getString(R.string.divideText).toRegex(), "/")
        userExp = userExp.replace(resources.getString(R.string.multiplyText).toRegex(), "*")
        val exp = Expression(userExp)
        val result = exp.calculate().toString()
        display.setText(result)
        display.setSelection(result.length)
    }

    // "C" 버튼 클릭 시 호출되는 메서드
    fun clearBtn(v: View) {
        display.setText("")
        previousCal.text = ""
    }

    // 화면 회전 버튼 클릭 시 호출되는 메서드
    fun rotateBtn(v: View) {
        requestedOrientation = if (isPortrait) {
            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
        }
        isPortrait = !isPortrait
    }
}
