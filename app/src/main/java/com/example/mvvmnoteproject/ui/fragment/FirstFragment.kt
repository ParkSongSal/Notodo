package com.example.mvvmnoteproject.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmnoteproject.adapter.TodoAdapter
import com.example.mvvmnoteproject.databinding.FragmentFirstBinding
import com.example.mvvmnoteproject.model.Memo
import com.example.mvvmnoteproject.ui.dialog.MyCustomDialog
import com.example.mvvmnoteproject.ui.dialog.MyCustomDialogInterface
import com.example.mvvmnoteproject.viewmodel.MemoViewModel
import java.util.*


class FirstFragment : Fragment(), MyCustomDialogInterface {

    private var binding : FragmentFirstBinding? = null
    private val memoViewModel: MemoViewModel by viewModels() // 뷰모델 연결
    private val adapter : TodoAdapter by lazy { TodoAdapter(memoViewModel, requireContext()) } // 어댑터 선언

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰바인딩
        binding = FragmentFirstBinding.inflate(inflater,container,false)

        // 아이템에 아이디를 설정해줌 (깜빡이는 현상방지)
        adapter.setHasStableIds(true)

        // 아이템을 가로로 하나씩 보여주고 어댑터 연결
        binding!!.todoRecyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        binding!!.todoRecyclerView.adapter = adapter

        // 리스트 관찰하여 변경시 어댑터에 전달해줌
        memoViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        // Fab 클릭시 다이얼로그 띄움
        binding!!.dialogButton.setOnClickListener {
            onFabClicked()
        }


        return binding!!.root
    }

    // Fab 클릭시 사용되는 함수
    private fun onFabClicked(){
        val myCustomDialog = MyCustomDialog(requireContext(),this)
        myCustomDialog.show()
    }

    // 프래그먼트는 뷰보다 오래 지속 . 프래그먼트의 onDestroyView() 메서드에서 결합 클래스 인스턴스 참조를 정리
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onOkButtonClicked(content: String) {
        // 현재의 날짜를 불러옴
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH) + 1
        val day = cal.get(Calendar.DATE)

        // 현재의 날짜로 메모를 추가해줌
        val memo = Memo(0,false,content, year, month, day)
        memoViewModel.addMemo(memo)
        Toast.makeText(activity,"추가", Toast.LENGTH_SHORT).show()
    }

}