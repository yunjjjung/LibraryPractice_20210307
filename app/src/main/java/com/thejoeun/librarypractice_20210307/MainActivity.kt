package com.thejoeun.librarypractice_20210307

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        이미지 뷰 클릭시 => 사진 보기 화면 이동.
        profileImg.setOnClickListener {
            val myIntent = Intent(mContext,ViewPhotoActivity::class.java)              //mContext 에서 출발해서 ViewPhotoActivity 로 갈꺼야
            startActivity(myIntent)
        }

//        전화걸기 버튼 눌리면 => 권한 있는지 확인하고 => 실제 전화 걸기
        callBtn.setOnClickListener {

//            1. 권한이 있는지 / 거절 되었는지에 따른 행동 방침(Interface) 변수 생성
//            권한이 있으면 => Uri /Action_call 등 실행
//            권한 없으면 => 토스트로 연결 불가 안내

            val pl = object : PermissionListener {
                override fun onPermissionGranted() {
                    val myUri= Uri.parse("tel: 010-2222-3333")
                    val myIntent = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(mContext,"전화 권한 없음", Toast.LENGTH_SHORT).show()
                }
            }

//            2. 1에서 만든 행동 방침을 가지고 => 실제 권한 확인

            TedPermission.with(mContext)
                .setPermissionListener(pl)
                .setDeniedMessage("권한 거절시 사용 불가. 설정 > 권한에서 세팅 필요")
                .setPermissions(android.Manifest.permission.CALL_PHONE)
                .check()

        }
    }

    override fun setValues() {
    }


}