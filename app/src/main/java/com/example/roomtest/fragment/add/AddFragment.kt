package com.example.roomtest.fragment.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomtest.R
import com.example.roomtest.model.User
import com.example.roomtest.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //view는 fragment_add라는 이름을 가진 레이아웃을 가리킴
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        //???
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //view(fragment_add)의 add_btn(id)를 클릭했을 때
        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        //입력된 값을 가져옴
        val firstName = addFirstName_et.text.toString()
        val lastName = addLastName_et.text.toString()
        val age = addAge_et.text

        //입력됐는지 체크
        if(inputCheck(firstName, lastName, age)){
            //User오브젝트 만들기
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            //데이터베이스에 데이터 넣기
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            //돌아가기
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        //TextUtils.isEmpty() : null체크
        return !(TextUtils.isEmpty(firstName)) && !(TextUtils.isEmpty(lastName)) && !(age.isEmpty())
    }
}