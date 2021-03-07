package com.thejoeun.librarypractice_20210307

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

    abstract  fun setupEvents()    // 버튼 클릭. 리스트 뷰 아이템 클릭 등 모든 이벤트 처리 모아두는 함수
    abstract fun setValues() // 화면에 데이터를 보여주는 관련 코드를 모아두는 함수
}