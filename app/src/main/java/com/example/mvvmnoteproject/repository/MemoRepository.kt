package com.example.mvvmnoteproject.repository

import com.example.mvvmnoteproject.model.Memo
import com.example.mvvmnoteproject.data.MemoDao
import kotlinx.coroutines.flow.Flow

// 앱에서 사용하는 데이터와 그 데이터 통신을 하는 역할
class MemoRepository(private val memoDao: MemoDao) {
    val readAllData : Flow<List<Memo>> = memoDao.readAllData()
    val readDoneData : Flow<List<Memo>> = memoDao.readDoneData()

    fun addMemo(memo: Memo){
        memoDao.addMemo(memo)
    }

    fun updateMemo(memo: Memo){
        memoDao.updateMemo(memo)
    }

    fun deleteMemo(memo: Memo){
        memoDao.deleteMemo(memo)
    }

    fun readDateData(year : Int, month : Int, day : Int): List<Memo> {
        return memoDao.readDateData(year, month, day)
    }

//    fun searchDatabase(searchQuery: String): Flow<List<Memo>> {
//        return memoDao.searchDatabase(searchQuery)
//    }
}