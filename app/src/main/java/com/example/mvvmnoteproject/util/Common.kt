package com.example.mvvmnoteproject.util

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mvvmnoteproject.model.Memo
import com.example.mvvmnoteproject.viewmodel.MemoViewModel

object Common {

    fun delAlertDialog(context : Context, memo : Memo, memoViewModel: MemoViewModel){
        // 다이얼로그를 생성하기 위해 Builder 클래스 생성자를 이용해 줍니다.
        val builder = AlertDialog.Builder(context)
        builder.setTitle("삭제 알림")
            .setMessage("해당 할일을 삭제 하시겠습니까?")
            .setPositiveButton("예"
            ) { dialog, id ->
                memoViewModel.deleteMemo(memo)
                Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("아니오"
            ) { dialog, id ->
                dialog.dismiss()
            }
        // 다이얼로그를 띄워주기
        builder.show()

    }

}